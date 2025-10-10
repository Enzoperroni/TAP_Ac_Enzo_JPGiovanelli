package br.edu.ibmec.ibuni.dto;

import br.edu.ibmec.ibuni.entity.Situacao;
import lombok.Data;

@Data
public class InscricaoDTO {

    private Long id;
    private float avaliacao1;
    private float avaliacao2;
    private float media;
    private int numFaltas;
    private Situacao situacao;
    private int aluno;
    private int turma;
}
