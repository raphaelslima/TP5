package ListaSE;  

public class ListaSE {  
    private No inicio; // Nó inicial da lista  
    private int tamanho;  

    public ListaSE() {  
        this.inicio = null;  
        this.tamanho = 0;  
    }  

    public void inserirNoInicio(int valor) {  
        No no = new No(valor);  
        no.setProx(inicio);  
        inicio = no;  
        tamanho++;  
    }  

    public int obterElemento(int index) {  
        if (index < 0 || index >= tamanho) {  
            throw new IndexOutOfBoundsException("ERRO!! Elemento não existe");  
        }  
        No atual = inicio;  
        for (int i = 0; i < index; i++) {  
            atual = atual.getProx();   
        }  
        return atual.getValor();   
    }  

    public int tamanho() {  
        return tamanho;   
    }  

    public boolean isEmpty() {  
        return tamanho == 0;   
    }  

    public boolean contemElemento(int valor) {  
        No atual = inicio;  
        while (atual != null) {  
            if (atual.getValor() == valor) {  
                return true;   
            }  
            atual = atual.getProx();   
        }  
        return false;   
    }  

    @Override  
    public String toString() {  
        StringBuilder sb = new StringBuilder();  
        No atual = inicio; // Começa no início da lista  
        while (atual != null) {  
            sb.append(atual.getValor()); // Adiciona o valor do nó atual  
            if (atual.getProx() != null) { // Verifica se não é o último nó  
                sb.append(", "); // Adiciona uma vírgula para separar os valores  
            }  
            atual = atual.getProx(); // Move para o próximo nó  
        }  
        return sb.toString(); // Retorna a string construída  
    }  
}