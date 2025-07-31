package repository;

import exceptions.DependenteException;
import models.Dependente;
import models.Funcionario;
import util.VerificacaoCPF;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

////Inserir dependentes no banco de dados com validação rigorosa de CPF,
/// promovendo integridade e organização das informações.

public class DependenteDao implements CPF {
    public void inserirDependente(List<Funcionario> funcionarios, Connection conn){
        try {
            String sql = "INSERT INTO dependente(fk_id_funcionario, nome, cpf, data_nascimento, parentesco) values (?, ?, ?, ?, ?)";

            for (Funcionario funcionario : funcionarios) {
                for(Dependente dependente : funcionario.getDependentes()){
                    try {
                        if(!verificaCPF(dependente.getCpf(), conn)){
                            PreparedStatement stmt = conn.prepareStatement(sql);

                            Integer id = idCPF(funcionario.getCpf(), conn);
                            stmt.setInt(1, id);
                            stmt.setString(2, dependente.getNome());
                            stmt.setString(3, dependente.getCpf());
                            stmt.setDate(4, Date.valueOf(dependente.getDataNascimento()));
                            stmt.setString(5, dependente.getParentesco().toString());
                            stmt.execute();
                            System.out.println("O dependente " + dependente.getNome() + " foi registrado com sucesso!");
                        } else {
                            List<Funcionario> cpfInvalido = new ArrayList<>();
                            cpfInvalido.add(funcionario);
                            VerificacaoCPF.inserirCPF(cpfInvalido, conn);
                            throw new SQLException("O CPF do dependente " + dependente.getNome() + " já existe no banco de dados!");
                        }
                    } catch(SQLException e){
                        System.err.println("Erro ao inserir dependente: " + dependente.getNome());
                        e.printStackTrace();
                    } catch(DependenteException e){
                        System.err.println("Erro com dependente: " + dependente.getNome());
                        System.err.println("Motivo: " + e.getMessage());
                    }
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Boolean verificaCPF(String cpf, Connection conn) {
        String sql = "SELECT 1 FROM dependente WHERE cpf = ?";
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
        String sql = "SELECT id FROM funcionario WHERE cpf = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            System.err.println("Erro em pegar a chave estrangeira do ID pelo CPF na tabela de funcionario!");
            e.printStackTrace();
        }
        return null;
    }
}

//O código apresentado é responsável por gerenciar a inserção de dependentes de funcionários no banco de dados.
// Ele assegura a integridade e unicidade dos dados por meio de validações, verificações de CPF e chave estrangeira,
// além de tratamento de erros.
// Recebe uma lista de funcionários e a conexão com o banco.
// Para cada funcionário, percorre sua lista de dependentes.
// Valida o CPF do dependente:
//Se o CPF for válido:
// Obtém o ID do funcionário.
// Insere o dependente na tabela dependente.
//Se o CPF já estiver registrado:
// Lança uma exceção e registra o CPF como inválido.
//Exibe mensagens de sucesso ou erro no console para cada dependente processado.
