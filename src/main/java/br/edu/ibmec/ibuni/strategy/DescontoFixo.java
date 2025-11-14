package br.edu.ibmec.ibuni.strategy;

public class DescontoFixo implements DescontoStrategy {
    @Override
    public double calcular(double mensalidade, double desconto) {
        return mensalidade - desconto;
    }
}
