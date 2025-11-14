package br.edu.ibmec.ibuni.dto;

import br.edu.ibmec.ibuni.entity.TipoDesconto;
import lombok.Data;

@Data
public class MatriculaDTO {
    private int id;
    private int alunoId;
    private int cursoId;
    private double mensalidade;
    private double desconto;
    private TipoDesconto tipoDesconto;
}
