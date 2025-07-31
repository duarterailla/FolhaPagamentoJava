package calculo;

public enum IR {
    BASE1(2259.0, 0.0, 0.0),
    BASE2(2826.65, 0.075, 169.44),
    BASE3(3751.05, 0.15, 381.44),
    BASE4(4664.68, 0.225, 662.77),
    BASE5(4664.69, 0.275, 896.00);

    final static Double DEPENDENTE = 189.59;
    Double faixa;
    Double aliquota;
    Double deducao;

    IR(Double faixa, Double aliquota, Double deducao) {
        this.faixa = faixa;
        this.aliquota = aliquota;
        this.deducao = deducao;
    }
}