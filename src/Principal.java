
public class Principal {
    public static void main(String[] args) {
        // Inicializa Arvore
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();

        String caminho = "./src/textos"; // diretório dos arquivos de entrada
    
        LeitorDeArquivos leitor = new LeitorDeArquivos(caminho, arvore);
        leitor.processarArquivos();
    }      
}