package br.edu.ibmec.ibuni.strategy;

public class DescontoPercentual implements DescontoStrategy {
    @Override
    public double calcular(double mensalidade, double desconto) {
        return mensalidade - (mensalidade * (desconto / 100));
    }
}
