package TesteJava;

import models.Funcionario;
import util.LeituraCSV;

import java.util.List;
import java.util.Scanner;

public class TestLeituraCSV {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o caminho do diretório ou seu arquivo: ");
        String diretorio = sc.nextLine();

        // Armazena os funcionários retornados pelo método
        List<Funcionario> funcionarios = LeituraCSV.leituraCSV(diretorio);

        // Exibe os dados dos funcionários processados
        System.out.println("Funcionários lidos: " + funcionarios.size());
        for (Funcionario func : funcionarios) {
            System.out.println("Nome: " + func.getNome());
            System.out.println("CPF: " + func.getCpf());
            System.out.println("Salário Bruto: " + func.getSalarioBruto());
            System.out.println("-------------------------------");
        }
    }
}
