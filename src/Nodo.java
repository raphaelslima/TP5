import ListaSE.ListaSE;

public class Nodo {
    private String palavra;
    private ListaSE linhas;
    private Nodo direita;
    private Nodo esquerda;
    private int bal;

    public Nodo(String palavra, int linha) {
        this.palavra = palavra;
        this.linhas = new ListaSE();
        this.esquerda = null;
        this.direita = null;
        this.bal = 0;
    }

    public Nodo getDireita() {
        return direita;
    }

    public void setDireita(Nodo direita) {
        this.direita = direita;
    }

    public Nodo getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Nodo esquerda) {
        this.esquerda = esquerda;
    }

    public String getPalavra() {
        return palavra;
    }


    public int getBal() {
        return bal;
    }

    public void adicionarLinha(int linha){
        linhas.inserirNoInicio(linha);
    }
    public ListaSE getLinhas() {
        return linhas; 
    }

    // Cálculo de altura da árvore
    public int calcularAltura(){
        if(this.esquerda == null && this.direita == null){
            return 0;
        }
        else if(this.esquerda != null && this.direita == null){
            return 1 + this.esquerda.calcularAltura();
        }
        else if(this.esquerda == null && this.direita != null){
            return 1 + this.direita.calcularAltura();
        }
        else{
            return 1 + Math.max(this.esquerda.calcularAltura(), this.direita.calcularAltura());
        }
    }

    // Cálculo do balanceamento
    public void calculaBal(){
        if(this.esquerda == null && this.direita == null){
            this.bal = 0;
        }
        else if(this.esquerda == null && this.direita != null){
            this.bal = this.direita.calcularAltura();
        }
        else if(this.esquerda != null && this.direita == null){
            this.bal = this.esquerda.calcularAltura();
        }
        else{
            this.bal = this.direita.calcularAltura() - this.esquerda.calcularAltura();
        }

        if(this.direita != null) this.direita.calculaBal();
        if(this.esquerda != null) this.esquerda.calculaBal();
    }
}
