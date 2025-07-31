package util;
import models.Funcionario;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GravacaoArquivo {
    public static void gerarArquivo(List<Funcionario> funcionarios) {
        try {
            FileWriter fw = new FileWriter("c:/Curso/processado.csv");
            PrintWriter pw = new PrintWriter(fw);

            for (Funcionario fun : funcionarios) {
                fun.calcularDescontos();

                pw.printf("%s;%s;%.2f;%.2f;%.2f%n",
                        fun.getNome(),
                        fun.getCpf(),
                        fun.getDescontoINSS(),
                        fun.getDescontoIR(),
                        fun.calcularSalarioLiquido()
                );
            }

            pw.close();
        } catch (IOException e) {
            System.err.println("Erro ao gravar o arquivo CSV!");
            e.printStackTrace();
        }
    }
}