package repository;

import exceptions.FuncionarioException;
import models.Funcionario;
import util.VerificacaoCPF;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//O código apresentado é responsável por realizar a inserção de funcionários no banco de dados,
// garantindo a unicidade do CPF e a segurança dos dados, além de tratar possíveis erros no
// processo.

// garantindo unicidade do CPF e segurança dos dados.
//Ele lida com operações SQL e realiza validações para evitar duplicações de dados, seguindo boas práticas de
// programação e segurança.

public class FuncionarioDao implements CPF{
    public void inserirFuncionarios(List<Funcionario> funcionarios, Connection conn) {
        try {
            String sql = "INSERT INTO funcionario(nome, cpf, data_nascimento, salario_bruto) VALUES (?, ?, ?, ?)";

            for (Funcionario funcionario : funcionarios) {
                try {
                    if(!verificaCPF(funcionario.getCpf(), conn)){
                        PreparedStatement stmt = conn.prepareStatement(sql);

                        stmt.setString(1, funcionario.getNome());
                        stmt.setString(2, funcionario.getCpf());
                        stmt.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
                        stmt.setDouble(4, Math.round(funcionario.getSalarioBruto() * 100.0) / 100.0);
                        stmt.execute();
                        System.out.println("O funcionario(a) " + funcionario.getNome() + " foi registrado com sucesso!");
                    } else {
                        List<Funcionario> cpfInvalido = new ArrayList<>();

                        cpfInvalido.add(funcionario);
                        VerificacaoCPF.inserirCPF(cpfInvalido, conn);
                        throw new SQLException("O CPF do funcionario " + funcionario.getNome() + " já existe no banco de dados!");
                    }
                } catch (SQLException e){
                    System.err.println("Erro ao inserir funcionario: " + funcionario.getNome());
                    e.printStackTrace();
                } catch(FuncionarioException e){
                    System.err.println("Erro com funcionario: " + funcionario.getNome());
                    System.err.println("Motivo: " + e.getMessage());
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Boolean verificaCPF(String cpf, Connection conn) {
        String sql = "SELECT 1 FROM funcionario WHERE cpf = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.err.println("Erro na validação de CPF(s)");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Integer idCPF(String cpf, Connection conn) {
        return null;
    }
}


// Fluxo de Execução
// Receber uma lista de funcionários e a conexão com o banco.
// Para cada funcionário:
//O CPF é validado pelo método vericaCPF.
//Caso o CPF seja válido, os dados do funcionário são inseridos no banco.
//Caso o CPF já exista, ele é registrado em uma lista de CPFs inválidos e uma exceção é lançada.
//Mensagens de sucesso ou erro são exibidas no console para cada funcionário processado.