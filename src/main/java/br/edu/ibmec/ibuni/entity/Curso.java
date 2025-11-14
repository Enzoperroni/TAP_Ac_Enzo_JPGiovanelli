package br.edu.ibmec.ibuni.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "curso")
    private List<Matricula> matriculas = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "curso")
    private List<Disciplina> disciplinas = new ArrayList<>();
}
