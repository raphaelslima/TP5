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
        if(atual.bal >= 2 || atual.bal <= -2){
            if(atual.bal * atual.direita.bal >= 2){
                if(atual.bal * atual.direita.bal > 0){
                    return atual.rotacaoSimplesDireita();
                } else {
                    return atual.rotacaoDuplaDireita();
                }
            }
        }
        else {
            if(atual.bal * atual.esquerda.bal > 0){
                return atual.rotacaoSimplesEsquerda();
            } else {
                return atual.rotacaoDuplaEsquerda();
            }
        }
        atual.calculaBal();
        if(atual.esquerda != null ) atual.esquerda.verificaBalancemaneto();
        if(atual.direita != null ) atual.direita.verificaBalancemaneto();
        return atual;
    }

    // ESBOÇO
    public Nodo rotacaoSimplesDireita(){
        Nodo filhoDireta;
        Nodo filhoDoFilho = null;

        filhoDireta = this.direita;
        if(this.direita != null){
            if (this.direita.esquerda != null) {
                filhoDoFilho = filhoDireta.esquerda;
            }
        }
        filhoDireta.esquerda = this;
        this.direita = filhoDoFilho;
        return filhoDireta;    
    }

    // ESBOÇO
    public Nodo rotacaoDuplaDireita(){
            Nodo nodo = this;
            Nodo filhoDireita = this.direita;
            Nodo filhoDoFilho = filhoDireita.esquerda;
            Nodo noInserido = filhoDoFilho.direita;

            filhoDireita.esquerda = noInserido;
            filhoDoFilho.direita = filhoDireita;
            this.direita = filhoDoFilho;

            Nodo novoFilhoDireita = this.direita;
            nodo.direita= null;
            novoFilhoDireita.esquerda = nodo;

            return novoFilhoDireita;
    }

    // ESBOÇO
    public Nodo rotacaoSimplesEsquerda(){
        Nodo filhoEsquerda;
        Nodo filhoDoFilho = null;

        filhoEsquerda = this.esquerda;
        if(this.esquerda != null){
            if (this.esquerda.direita != null) {
                filhoDoFilho = filhoEsquerda.direita;
            }
        }
        filhoEsquerda.direita = this;
        this.esquerda = filhoDoFilho;
        return filhoEsquerda;  
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
