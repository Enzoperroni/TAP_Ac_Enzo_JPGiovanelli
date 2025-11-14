package br.edu.ibmec.ibuni.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private LocalDate dataNascimento;

    private boolean matriculaAtiva;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @ElementCollection
    private List<String> telefones = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "aluno")
    private List<Matricula> matriculas = new ArrayList<>();

    @OneToMany(mappedBy = "aluno")
    private List<Inscricao> inscricoes = new ArrayList<>();
}
