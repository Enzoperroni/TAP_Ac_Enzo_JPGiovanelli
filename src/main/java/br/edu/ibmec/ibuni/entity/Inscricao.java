package br.edu.ibmec.ibuni.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float avaliacao1;

    private float avaliacao2;

    private float media;

    private int numFaltas;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Turma turma;
}
