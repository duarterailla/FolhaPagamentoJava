package util;

import exceptions.DependenteException;
import models.Funcionario;
import models.Dependente;
import models.Parentesco;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class LeituraCSV {
    public static List<Funcionario> leituraCSV(String diretorio) {
        List<Funcionario> funcionarios = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        try {
            Scanner ler = new Scanner(new FileReader(diretorio));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            List<String> bloco = new ArrayList<>();
            while (ler.hasNextLine()) {
                String linha = ler.nextLine().trim();

                if (linha.isEmpty() && !bloco.isEmpty()) {
                    Funcionario fun = processarBloco(bloco, formatter);
                    if (fun != null){
                        fun.calcularDescontos();
                        fun.calcularSalarioLiquido();
                        funcionarios.add(fun);
                    }
                    bloco.clear();
                } else {
                    bloco.add(linha);
                }
            }

            if (!bloco.isEmpty()) {
                Funcionario f = processarBloco(bloco, formatter);
                if (f != null){
                    f.calcularDescontos();
                    f.calcularSalarioLiquido();
                    funcionarios.add(f);
                }
            }

            ler.close();
        } catch (FileNotFoundException e) {
            System.err.println("Caminho especificado não encontrado!");
            e.printStackTrace();
        }
        return funcionarios;
    }

    private static Funcionario processarBloco(List<String> bloco, DateTimeFormatter formatter) {
        try {
            if (bloco.isEmpty()) return null;

            String[] dadosFuncionario = bloco.get(0).split(";");

            Funcionario fun = new Funcionario(dadosFuncionario[0], dadosFuncionario[1], LocalDate.parse(dadosFuncionario[2], formatter));
            fun.setSalarioBruto(Double.parseDouble(dadosFuncionario[3]));

            for(int i = 1; i < bloco.size(); i++) {
                String[] dadosDependentes = bloco.get(i).split(";");
                Dependente d = new Dependente(dadosDependentes[0], dadosDependentes[1], LocalDate.parse(dadosDependentes[2], formatter), Parentesco.valueOf(dadosDependentes[3].toUpperCase()));

                fun.getDependentes().add(d);
            }
            return fun;

        } catch(DependenteException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}