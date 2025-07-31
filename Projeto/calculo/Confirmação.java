package calculo;
import java.io.File;

public class VonfirmacaoCAMINHO {


    public static void main(String[] args) {
        String caminho = "C:/Users/SERRATEC/Downloads/postgresql-42.7.5 (1).jar";
        File arquivo = new File(caminho);

        if (arquivo.exists()) {
            System.out.println("Caminho válido! Arquivo encontrado.");
        } else {
            System.out.println("Caminho inválido ou arquivo não encontrado. Verifique o diretório.");
        }
    }
}
