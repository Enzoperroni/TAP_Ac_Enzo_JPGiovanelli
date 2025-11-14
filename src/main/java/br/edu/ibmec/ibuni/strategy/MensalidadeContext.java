package br.edu.ibmec.ibuni.strategy;

public class MensalidadeContext {
    private DescontoStrategy strategy;

    public void setStrategy(DescontoStrategy strategy) {
        this.strategy = strategy;
    }

    public double calcular(double mensalidade, double desconto) {
        return strategy.calcular(mensalidade, desconto);
    }
}
