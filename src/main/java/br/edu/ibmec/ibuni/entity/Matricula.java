package br.edu.ibmec.ibuni.entity;

import br.edu.ibmec.ibuni.strategy.DescontoFixo;
import br.edu.ibmec.ibuni.strategy.DescontoPercentual;
import br.edu.ibmec.ibuni.strategy.MensalidadeContext;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Curso curso;

    private double mensalidade;

    private double desconto;

    @Enumerated(EnumType.STRING)
    private TipoDesconto tipoDesconto;

    @Transient
    private double mensalidadeFinal;

    public void calcularMensalidadeFinal() {
        MensalidadeContext context = new MensalidadeContext();
        if (tipoDesconto == TipoDesconto.PERCENTUAL) {
            context.setStrategy(new DescontoPercentual());
        } else if (tipoDesconto == TipoDesconto.FIXO) {
            context.setStrategy(new DescontoFixo());
        }
        this.mensalidadeFinal = context.calcular(this.mensalidade, this.desconto);
    }
}
