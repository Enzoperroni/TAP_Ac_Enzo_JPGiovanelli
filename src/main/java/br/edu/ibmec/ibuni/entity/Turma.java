package br.edu.ibmec.ibuni.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    private int ano;

    private int semestre;

    @ManyToOne
    private Disciplina disciplina;

    @JsonIgnore
    @OneToMany(mappedBy = "turma")
    private List<Inscricao> inscricoes = new ArrayList<>();
}
