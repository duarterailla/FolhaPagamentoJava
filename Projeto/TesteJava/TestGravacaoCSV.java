package TesteJava;
import models.Funcionario;
import util.GravacaoArquivo;

import util.LeituraCSV;
import java.util.List;
import java.util.Scanner;

import java.util.Scanner;
import java.util.List;

public class TestGravacaoCSV {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o caminho do diretório ou do arquivo: ");
        String diretorio = sc.nextLine();

        try {
            // Lê os dados do arquivo CSV
            List<Funcionario> funcionarios = LeituraCSV.leituraCSV(diretorio);

            // Verifica se a lista de funcionários está vazia
            if (funcionarios == null || funcionarios.isEmpty()) {
                System.out.println("Nenhum funcionário foi encontrado. Verifique o arquivo ou o caminho inserido.");
            } else {
                // Gera o arquivo CSV de gravação
                try {
                    GravacaoArquivo.gerarArquivo(funcionarios);
                    System.out.println("Arquivo processado com sucesso!");
                } catch (Exception e) {
                    System.err.println("Erro durante o processo de gravação!");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo CSV!");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}