import java.io.*;
import java.text.Normalizer;

public class LeitorDeArquivos {

    private String caminho;
    private ArvoreBinariaDePesquisa arvore;

    public LeitorDeArquivos(String caminho, ArvoreBinariaDePesquisa arvore) {
        this.caminho = caminho;
        this.arvore = arvore;
    }

    public void processarArquivos() {
        File diretorio = new File(caminho); // Diretório dos arquivos
        File[] arquivos = diretorio.listFiles(); // Lista os arquivos no diretório

        if (arquivos != null) {
            for (File file : arquivos) { // Lê cada arquivo do diretório
                System.out.println("=========================================\n");
                System.out.println("=========================================\n");
                System.out.println("Arquivo " + file.getName() + "\n");

                File arquivoResultado = new File("resultado_" + file.getName());

                processarArquivo(file, arquivoResultado);

                try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoResultado, true))) {
                    arvore.emOrdem(escritor); 
                } catch (IOException e) {
                    e.printStackTrace();
                }

                arvore.limpar();
            }
        } else {
            System.out.println("O diretório está vazio ou não existe.");
        }
    }

    private void processarArquivo(File file, File arquivoResultado) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(file));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoResultado))) {
            String linha;
            int numeroLinha = 1;

            while ((linha = leitor.readLine()) != null) {
                String[] palavras = formatarLinha(linha); 
                for (String palavra : palavras) {
                    if (palavra.length() >= 3 && palavra.length() <= 20) { 
                        arvore.inserir(palavra, numeroLinha);
                    }
                }

                numeroLinha++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] formatarLinha(String linha) {
        String lowercase = linha.toLowerCase();
        String normalized = Normalizer.normalize(lowercase, Normalizer.Form.NFD); // Normaliza
        String removeAcentos = normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""); // Remove acentos
        String removePontuacao = removeAcentos.replaceAll("[\\p{Punct}]", ""); // Remove pontuação
        return removePontuacao.split("\\s+"); // Divide em palavras
    }
}
