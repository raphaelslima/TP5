package ListaSE;

public class No {
    private int valor; 
    private No prox;   

    
    public No() {
        this.valor = 0; 
        this.prox = null; 
    }

    public No(int valor) {
        this.valor = valor;
        this.prox = null; 
    }

    
    public int getValor() {
        return valor;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
}
