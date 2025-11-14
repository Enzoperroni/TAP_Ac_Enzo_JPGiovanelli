package br.edu.ibmec.ibuni.strategy;

import br.edu.ibmec.ibuni.entity.Inscricao;
import br.edu.ibmec.ibuni.entity.Situacao;

public class SituacaoPadraoStrategy implements DefinirSituacaoStrategy {
    @Override
    public Situacao definir(Inscricao inscricao) {
        if (inscricao.getMedia() >= 7.0) {
            return Situacao.APROVADO;
        } else {
            return Situacao.REPROVADO;
        }
    }
}
