package br.edu.ibmec.ibuni.strategy;

import br.edu.ibmec.ibuni.entity.Inscricao;
import br.edu.ibmec.ibuni.entity.Situacao;

public interface DefinirSituacaoStrategy {
    Situacao definir(Inscricao inscricao);
}
