package calculo;


public enum INSS {
    BASE1(1518.0, 0.075, 0.0),
    BASE2(2793.88, 0.09, 22.77),
    BASE3(4190.83, 0.12, 106.6),
    BASE4(4190.84, 0.14, 190.42);
    final static Double LIMITE = 951.62;
    Double faixa;
    Double aliquota;
    Double deducao;
    INSS(Double faixa, Double aliquota, Double deducao) {
        this.faixa = faixa;
        this.aliquota = aliquota;
        this.deducao = deducao;
    }
}
