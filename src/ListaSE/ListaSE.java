package ListaSE;

public class ListaSE {
    private No inicio;
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

    // Método para representar a lista de linhas como uma string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        No atual = inicio;
        while (atual != null) {
            sb.append(atual.getValor()).append(" "); // Adiciona o valor do nó
            atual = atual.getProx(); // Move para o próximo nó
        }
        return sb.toString().trim(); // Retorna a representação da lista sem espaços extras
    }
}
