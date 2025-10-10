package br.edu.ibmec.ibuni.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.ibuni.dto.TurmaDTO;
import br.edu.ibmec.ibuni.entity.Disciplina;
import br.edu.ibmec.ibuni.entity.Turma;
import br.edu.ibmec.ibuni.repository.DisciplinaRepository;
import br.edu.ibmec.ibuni.repository.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<TurmaDTO> findAll() {
        return this.turmaRepository.findAll().stream().map(turma -> {
            TurmaDTO dto = new TurmaDTO();
            dto.setCodigo(turma.getCodigo());
            dto.setAno(turma.getAno());
            dto.setSemestre(turma.getSemestre());
            if (turma.getDisciplina() != null) {
                dto.setDisciplina(turma.getDisciplina().getCodigo());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<TurmaDTO> findById(int codigo) {
        return this.turmaRepository.findById(codigo).map(turma -> {
            TurmaDTO dto = new TurmaDTO();
            dto.setCodigo(turma.getCodigo());
            dto.setAno(turma.getAno());
            dto.setSemestre(turma.getSemestre());
            if (turma.getDisciplina() != null) {
                dto.setDisciplina(turma.getDisciplina().getCodigo());
            }
            return dto;
        });
    }

    public TurmaDTO save(TurmaDTO turmaDTO) {
        Turma turma = new Turma();
        turma.setAno(turmaDTO.getAno());
        turma.setSemestre(turmaDTO.getSemestre());

        if (turmaDTO.getDisciplina() != 0) {
            Optional<Disciplina> disciplina = this.disciplinaRepository.findById(turmaDTO.getDisciplina());
            disciplina.ifPresent(turma::setDisciplina);
        }

        Turma savedTurma = this.turmaRepository.save(turma);
        turmaDTO.setCodigo(savedTurma.getCodigo());
        return turmaDTO;
    }

    public TurmaDTO update(int codigo, TurmaDTO turmaDTO) {
        return this.turmaRepository.findById(codigo).map(turma -> {
            turma.setAno(turmaDTO.getAno());
            turma.setSemestre(turmaDTO.getSemestre());

            if (turmaDTO.getDisciplina() != 0) {
                Optional<Disciplina> disciplina = this.disciplinaRepository.findById(turmaDTO.getDisciplina());
                disciplina.ifPresent(turma::setDisciplina);
            }

            Turma updatedTurma = this.turmaRepository.save(turma);
            turmaDTO.setCodigo(updatedTurma.getCodigo());
            return turmaDTO;
        }).orElseThrow(() -> new RuntimeException("Turma não encontrada com o código: " + codigo));
    }

    public void delete(int codigo) {
        this.turmaRepository.deleteById(codigo);
    }
}
