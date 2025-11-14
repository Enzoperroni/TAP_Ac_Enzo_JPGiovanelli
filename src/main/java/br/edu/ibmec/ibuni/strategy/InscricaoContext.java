package br.edu.ibmec.ibuni.strategy;

import br.edu.ibmec.ibuni.entity.Inscricao;
import br.edu.ibmec.ibuni.entity.Situacao;

public class InscricaoContext {
    private CalculoMediaStrategy calculoMediaStrategy;
    private DefinirSituacaoStrategy definirSituacaoStrategy;

    public InscricaoContext(CalculoMediaStrategy calculoMediaStrategy, DefinirSituacaoStrategy definirSituacaoStrategy) {
        this.calculoMediaStrategy = calculoMediaStrategy;
        this.definirSituacaoStrategy = definirSituacaoStrategy;
    }

    public void calcular(Inscricao inscricao) {
        float media = calculoMediaStrategy.calcular(inscricao);
        inscricao.setMedia(media);
        Situacao situacao = definirSituacaoStrategy.definir(inscricao);
        inscricao.setSituacao(situacao);
    }
}
