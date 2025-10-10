package br.edu.ibmec.ibuni.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matricula;

    private String nome;

    private LocalDate dataNascimento;

    private boolean matriculaAtiva;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @ElementCollection
    private List<String> telefones = new ArrayList<>();

    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "aluno")
    private List<Inscricao> inscricoes = new ArrayList<>();
}
