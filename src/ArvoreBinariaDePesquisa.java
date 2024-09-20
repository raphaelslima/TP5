import java.io.*;

public class ArvoreBinariaDePesquisa {
    // Raiz da árvore
    Nodo raiz;

    // Construtor
    public ArvoreBinariaDePesquisa() {
        this.raiz = null;
    }

    // Método para inserir uma nova palavra na árvore
    public ArvoreBinariaDePesquisa inserir(String palavra, int linha) {
        this.raiz = inserirRecursivo(this.raiz, palavra, linha);
        return this;
    }

    // Função inserir recursiva
    private Nodo inserirRecursivo(Nodo atual, String palavra, int linha) {
        if (atual == null) {
            return new Nodo(palavra, linha);
        }

        if (palavra.compareTo(atual.palavra) < 0) {
            atual.esquerda = inserirRecursivo(atual.esquerda, palavra, linha);
        } else if (palavra.compareTo(atual.palavra) > 0) {
            atual.direita = inserirRecursivo(atual.direita, palavra, linha);
        } else {
            atual.adicionarLinha(linha);
        }

        return atual;
    }

    // Percorre a árvore em ordem alfabética e grava no arquivo
    public void emOrdem(BufferedWriter escritor) throws IOException {
        emOrdemRecursivo(this.raiz, escritor);
    }

    // Função recursiva que percorre a árvore e grava as palavras e as linhas no arquivo
    private void emOrdemRecursivo(Nodo atual, BufferedWriter escritor) throws IOException {
        if (atual != null) {
            emOrdemRecursivo(atual.esquerda, escritor);
            escritor.write(atual.palavra +" "+ atual.linhas);
            escritor.newLine();
            System.out.println(atual.palavra + " " + atual.linhas + atual.bal);
            emOrdemRecursivo(atual.direita, escritor);
        }
    }

    public void limpar() {
        raiz = null; // anula a raiz e o garbage collector cuida do resto
    }
}
