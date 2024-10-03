import java.io.BufferedWriter;
import java.io.IOException;

public class ArvoreBinariaDePesquisa {
    // Raiz da árvore
    Nodo raiz;
    Rotacoes rotacoes = new Rotacoes();

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
            return new Nodo(palavra, linha); // Cria um novo nó se o atual for nulo
        }

        // Comparando a palavra que estamos inserindo com a palavra atual do nó
        if (palavra.compareTo(atual.getPalavra()) < 0) {
            // Inserção no lado esquerdo se a nova palavra for menor
            atual.setEsquerda(inserirRecursivo(atual.getEsquerda(), palavra, linha));
        } else if (palavra.compareTo(atual.getPalavra()) > 0) {
            // Inserção no lado direito se a nova palavra for maior
            atual.setDireita(inserirRecursivo(atual.getDireita(), palavra, linha));
        } else {
            // Se a palavra já existe, apenas adicionamos a linha onde foi encontrada
            atual.adicionarLinha(linha);
        }

        // atualiza o fator de balaceamento
        atual.calculaBal();

        // verifica a necessidade de rotação pelo balanceamento
        return rotacoes.verificaBalancemaneto(atual);
    }

    // percorre a árvore em ordem alfabética e grava no arquivo
    public void emOrdem(BufferedWriter escritor) throws IOException {
        emOrdemRecursivo(this.raiz, escritor);
    }

    // Função recursiva que percorre a árvore e grava as palavras e as linhas no arquivo
    private void emOrdemRecursivo(Nodo atual, BufferedWriter escritor) throws IOException {
        if (atual != null) {
            // inicia pela esquerda
            emOrdemRecursivo(atual.getEsquerda(), escritor);

            // escreve o nó atual no arquivo
            escritor.write(atual.getPalavra() + " " + atual.getLinhas());
            escritor.newLine();


            System.out.println(atual.getPalavra() + " " + atual.getLinhas() + " " + atual.getBal());

            // percorre a subárvore da direita
            emOrdemRecursivo(atual.getDireita(), escritor);
        }
    }

    // Método para limpar a árvore
    public void limpar() {
        raiz = null; // Anula a raiz, e o garbage collector cuida do restante
    }
}
