import java.util.ArrayList;

public class Nodo {
    String palavra;
    ArrayList<Integer> linhas = new ArrayList<>(); // Linhas em que a palavra aparece
    Nodo esquerda, direita;
    int bal;

    public Nodo(String palavra, int linha) {
        this.palavra = palavra;
        this.linhas.add(linha);
        this.esquerda = null;
        this.direita = null;
        this.bal = 0;
    }

    public void adicionarLinha(int linha){
        this.linhas.add(linha);
    }
    
    // Imprime a palavra e as linhas onde ela aparece
    public void imprimePalavra() {
        System.out.print(this.palavra + " ");
        System.out.println(linhas);
    }



    //FUNCIONA
    // Calculo de altura da arvore
    public int calcularAltura(){
        // Nó sem filhos
        if(this.esquerda == null && this.direita == null){
            return 1;
        }
        // Só tem filhos na esq
        else if(this.esquerda != null && this.direita == null){
            return 1 + this.esquerda.calcularAltura();
        }
        // Só tem filhos na direita
        else if(this.esquerda == null && this.direita != null){
            return 1 + this.direita.calcularAltura();
        }
        // Calcula a maior altura do dois lados
        else{
            return 1 + Math.max(this.esquerda.calcularAltura(), this.direita.calcularAltura());
        }
    }

    //FUNCIONA
    public void calculaBal(){
        // Nó sem filhos
        if(this.esquerda == null && this.direita == null){
            this.bal = 0;
        }
        // Só tem filhos na dir
        else if(this.esquerda == null && this.direita != null){
            this.bal = this.direita.calcularAltura();
        }
        // Só tem filhos na esq
        else if(this.esquerda != null && this.direita == null){
            this.bal = this.esquerda.calcularAltura();
        }
        else{
            this.bal =  this.direita.calcularAltura() - this.esquerda.calcularAltura();
        }

        if(this.direita != null) this.direita.calculaBal();
        if(this.esquerda != null) this.esquerda.calculaBal();
    }

    // ESBOÇO
    // Verifica qual rotcao usar para realizar o balancemaneto
    public Nodo verificaBalancemaneto(){
        Nodo atual = this;
        if(atual.bal >= 2){
            if(atual.direita.bal == 1 || atual.direita.bal == 0){
                rotacaoSimplesEsquerda();
            }
            else if(atual.direita.bal == -1){
                rotacaoDuplaDireita();
            }
        }
        else if(atual.bal <= -2){
            if(atual.esquerda.bal == 1){
                rotacaoDuplaEsquerda();
            }
            else if(atual.esquerda.bal == -1 || atual.esquerda.bal == 0){
                rotacaoSimplesDireita();
            }
        }
        atual.calculaBal();
        if(atual.esquerda != null ) atual.esquerda.verificaBalancemaneto();
        if(atual.direita != null ) atual.direita.verificaBalancemaneto();
        return atual;
    }

    // ESBOÇO
    public Nodo rotacaoSimplesDireita(){
        Nodo filhoDireita = this.direita;  // nó direita se tornará a nova raiz
        if(filhoDireita != null){
            Nodo filhoDoFilho = filhoDireita.direita;  // salva o filho direito de filhoEsquerda
            filhoDireita.direita = this;  // move o nó atual para a direita
            this.esquerda = filhoDoFilho;  // atribui o filho do filho à esquerda de 'this'
        }
        return filhoDireita;  // retorna a nova raiz da subárvore
    }
    

    // ESBOÇO
    public Nodo rotacaoDuplaDireita(){
        Nodo filhoDireita = this.direita;
        rotacaoSimplesDireita();
        rotacaoSimplesEsquerda();
        return filhoDireita;
    }

    // ESBOÇO
    public Nodo rotacaoSimplesEsquerda(){
        Nodo filhoEsquerda = this.esquerda;  // O nó esquerda se tornará a nova raiz
        if(filhoEsquerda != null){
            Nodo filhoDoFilho = filhoEsquerda.direita;  // salva o filho direito de filhoEsquerda
            filhoEsquerda.direita = this;  // move o nó atual para a direita
            this.esquerda = filhoDoFilho;  // atribui o filho do filho à esquerda de 'this'
        }
        return filhoEsquerda;  // retorna a nova raiz da subárvore
    }
    

    // ESBOÇO
    public Nodo rotacaoDuplaEsquerda(){
        Nodo nodo = this;
        Nodo filhoEsquerda = this.esquerda;
        Nodo filhoDoFilho = filhoEsquerda.direita;
        Nodo noInserido = filhoDoFilho.esquerda;

        filhoEsquerda.direita = noInserido;
        filhoDoFilho.esquerda = filhoEsquerda;
        this.esquerda = filhoDoFilho;

        Nodo novoFilhoEsquerda = this.esquerda;
        nodo.esquerda= null;
        novoFilhoEsquerda.direita = nodo;
            
        return novoFilhoEsquerda;  
    }
    
}
