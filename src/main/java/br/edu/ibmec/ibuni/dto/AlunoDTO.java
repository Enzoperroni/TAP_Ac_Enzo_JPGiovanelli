package br.edu.ibmec.ibuni.dto;

import java.time.LocalDate;
import java.util.List;

import br.edu.ibmec.ibuni.entity.EstadoCivil;
import lombok.Data;

@Data
public class AlunoDTO {

    private int matricula;
    private String nome;
    private LocalDate dataNascimento;
    private boolean matriculaAtiva;
    private EstadoCivil estadoCivil;
    private List<String> telefones;
    private int curso;
}
