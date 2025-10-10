package br.edu.ibmec.ibuni.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.ibuni.dto.DisciplinaDTO;
import br.edu.ibmec.ibuni.entity.Curso;
import br.edu.ibmec.ibuni.entity.Disciplina;
import br.edu.ibmec.ibuni.repository.CursoRepository;
import br.edu.ibmec.ibuni.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<DisciplinaDTO> findAll() {
        return this.disciplinaRepository.findAll().stream().map(disciplina -> {
            DisciplinaDTO dto = new DisciplinaDTO();
            dto.setCodigo(disciplina.getCodigo());
            dto.setNome(disciplina.getNome());
            if (disciplina.getCurso() != null) {
                dto.setCurso(disciplina.getCurso().getCodigo());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<DisciplinaDTO> findById(int codigo) {
        return this.disciplinaRepository.findById(codigo).map(disciplina -> {
            DisciplinaDTO dto = new DisciplinaDTO();
            dto.setCodigo(disciplina.getCodigo());
            dto.setNome(disciplina.getNome());
            if (disciplina.getCurso() != null) {
                dto.setCurso(disciplina.getCurso().getCodigo());
            }
            return dto;
        });
    }

    public DisciplinaDTO save(DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaDTO.getNome());

        if (disciplinaDTO.getCurso() != 0) {
            Optional<Curso> curso = this.cursoRepository.findById(disciplinaDTO.getCurso());
            curso.ifPresent(disciplina::setCurso);
        }

        Disciplina savedDisciplina = this.disciplinaRepository.save(disciplina);
        disciplinaDTO.setCodigo(savedDisciplina.getCodigo());
        return disciplinaDTO;
    }

    public DisciplinaDTO update(int codigo, DisciplinaDTO disciplinaDTO) {
        return this.disciplinaRepository.findById(codigo).map(disciplina -> {
            disciplina.setNome(disciplinaDTO.getNome());

            if (disciplinaDTO.getCurso() != 0) {
                Optional<Curso> curso = this.cursoRepository.findById(disciplinaDTO.getCurso());
                curso.ifPresent(disciplina::setCurso);
            }

            Disciplina updatedDisciplina = this.disciplinaRepository.save(disciplina);
            disciplinaDTO.setCodigo(updatedDisciplina.getCodigo());
            return disciplinaDTO;
        }).orElseThrow(() -> new RuntimeException("Disciplina não encontrada com o código: " + codigo));
    }

    public void delete(int codigo) {
        this.disciplinaRepository.deleteById(codigo);
    }
}
