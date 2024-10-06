import java.io.BufferedWriter;
import java.io.IOException;

public class ArvoreBinariaDePesquisa {

    private Nodo raiz;
    private Rotacoes rotacoes = new Rotacoes();


    public ArvoreBinariaDePesquisa() {
        this.raiz = null;
    }

    public ArvoreBinariaDePesquisa inserir(String palavra, int linha) {
        this.raiz = inserirRecursivo(this.raiz, palavra, linha);
        return this;
    }

    private Nodo inserirRecursivo(Nodo atual, String palavra, int linha) {
        if (atual == null) {
            return new Nodo(palavra, linha); 
        }


        if (palavra.compareTo(atual.getPalavra()) < 0) {
            atual.setEsquerda(inserirRecursivo(atual.getEsquerda(), palavra, linha));
        } else if (palavra.compareTo(atual.getPalavra()) > 0) {
            atual.setDireita(inserirRecursivo(atual.getDireita(), palavra, linha));
        } else {
            atual.adicionarLinha(linha); 
        }

        atual.calculaBal(); 


        return balancearArvore(atual, palavra);
    }


    private Nodo balancearArvore(Nodo atual, String palavra) {

        if (atual.getBal() > 1) {
            if (palavra.compareTo(atual.getDireita().getPalavra()) > 0) { // Caso RR
                return rotacoes.rotacaoSimplesEsquerda(atual);
            } else { // Caso RL
                atual.setDireita(rotacoes.rotacaoSimplesDireita(atual.getDireita()));
                return rotacoes.rotacaoSimplesEsquerda(atual);
            }
        }

        if (atual.getBal() < -1) {
            if (palavra.compareTo(atual.getEsquerda().getPalavra()) < 0) { // Caso LL
                return rotacoes.rotacaoSimplesDireita(atual);
            } else { // Caso LR
                atual.setEsquerda(rotacoes.rotacaoSimplesEsquerda(atual.getEsquerda()));
                return rotacoes.rotacaoSimplesDireita(atual);
            }
        }

        return atual; 
    }


    public void emOrdem(BufferedWriter escritor) throws IOException {
        emOrdemRecursivo(this.raiz, escritor);
    }

    private void emOrdemRecursivo(Nodo atual, BufferedWriter escritor) throws IOException {
        if (atual != null) {
            emOrdemRecursivo(atual.getEsquerda(), escritor);
            

            escritor.write(atual.getPalavra() + " " + atual.getLinhas());
            escritor.newLine();

            atual.exibirInformacoes(); 

            emOrdemRecursivo(atual.getDireita(), escritor);
        }
    }


    public void limpar() {
        raiz = null; 
    }

    private void imprimirArvore(Nodo atual) {
        if (atual != null) {
            atual.exibirInformacoes();
            imprimirArvore(atual.getEsquerda());
            imprimirArvore(atual.getDireita());
        }
    }

    public void inserirEImprimir(String palavra, int linha) {
        raiz = inserirRecursivo(raiz, palavra, linha);
        imprimirArvore(raiz);
    }
}
