package calculo;

public class CalculoGeral {
    public static Double INSS(Double salarioBruto) {
        double valordoinss;
        if(salarioBruto <= INSS.BASE1.faixa){
            valordoinss = salarioBruto * INSS.BASE1.aliquota - INSS.BASE1.deducao;
        } else if(salarioBruto <= INSS.BASE2.faixa){
            valordoinss = salarioBruto * INSS.BASE2.aliquota - INSS.BASE2.deducao;
        } else if (salarioBruto <= INSS.BASE3.faixa){
            valordoinss = salarioBruto * INSS.BASE3.aliquota - INSS.BASE3.deducao;
        } else {
            valordoinss = salarioBruto * INSS.BASE4.aliquota - INSS.BASE4.deducao;
        }
        if(valordoinss >= INSS.LIMITE){
            valordoinss = INSS.LIMITE;
        }
        return valordoinss;
    }
    public static Double IR(Double salarioBruto, Double descontoINSS, int numeroDependentes) {
        double baseCalculo = salarioBruto - descontoINSS - (numeroDependentes * IR.DEPENDENTE);
        if (baseCalculo <= IR.BASE1.faixa) {
            return baseCalculo * IR.BASE1.aliquota - IR.BASE1.deducao;
        } else if (baseCalculo <= IR.BASE2.faixa) {
            return baseCalculo * IR.BASE2.aliquota - IR.BASE2.deducao;
        } else if (baseCalculo <= IR.BASE3.faixa) {
            return baseCalculo * IR.BASE3.aliquota - IR.BASE3.deducao;
        } else if (baseCalculo <= IR.BASE4.faixa) {
            return baseCalculo * IR.BASE4.aliquota - IR.BASE4.deducao;
        } else {
            return baseCalculo * IR.BASE5.aliquota - IR.BASE5.deducao;
        }
    }
    public static Double calculoDoSalarioLiquido(Double salarioBruto, Double descontoINSS, Double descontoIR) {
        return salarioBruto - descontoINSS - descontoIR;
    }
}