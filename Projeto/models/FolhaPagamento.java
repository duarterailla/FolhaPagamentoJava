package models;

import models.Funcionario;

import java.time.LocalDate;

public class FolhaPagamento {
    private int codigo;
    private Funcionario funcionario;
    private LocalDate dataPagamento;
    private Double descontoINSS;
    private Double descontoIR;
    private Double salarioLiquido;

    // Construtor principal
    public FolhaPagamento(int codigo, Funcionario funcionario, LocalDate dataPagamento) {
        this.codigo = codigo;
        this.funcionario = funcionario;
        this.dataPagamento = dataPagamento;

        // Obtém os valores do objeto Funcionario
        this.descontoINSS = funcionario.getDescontoINSS();
        this.descontoIR = funcionario.getDescontoIR();
        this.salarioLiquido = funcionario.getSalarioLiquido();
    }

    // Getters para os atributos
    public int getCodigo() {
        return codigo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public Double getDescontoINSS() {
        return descontoINSS;
    }

    public Double getDescontoIR() {
        return descontoIR;
    }

    public Double getSalarioLiquido() {
        return salarioLiquido;
    }

    // Método toString para exibir os detalhes da folha de pagamento
    @Override
    public String toString() {
        return "FolhaPagamento [" +
                "codigo=" + codigo +
                ", funcionario=" + funcionario +
                ", dataPagamento=" + dataPagamento +
                ", descontoINSS=R$" + String.format("%.2f", descontoINSS) +
                ", descontoIR=R$" + String.format("%.2f", descontoIR) +
                ", salarioLiquido=R$" + String.format("%.2f", salarioLiquido) +
                "]";
    }
}