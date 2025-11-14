package br.edu.ibmec.ibuni.strategy;

import br.edu.ibmec.ibuni.entity.Inscricao;

public class MediaAritmeticaStrategy implements CalculoMediaStrategy {
    @Override
    public float calcular(Inscricao inscricao) {
        return (inscricao.getAvaliacao1() + inscricao.getAvaliacao2()) / 2;
    }
}
