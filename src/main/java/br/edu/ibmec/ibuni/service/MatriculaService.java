package br.edu.ibmec.ibuni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.ibuni.dto.MatriculaDTO;
import br.edu.ibmec.ibuni.entity.Aluno;
import br.edu.ibmec.ibuni.entity.Curso;
import br.edu.ibmec.ibuni.entity.Matricula;
import br.edu.ibmec.ibuni.repository.AlunoRepository;
import br.edu.ibmec.ibuni.repository.CursoRepository;
import br.edu.ibmec.ibuni.repository.MatriculaRepository;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Matricula create(MatriculaDTO matriculaDTO) {
        Aluno aluno = alunoRepository.findById(matriculaDTO.getAlunoId()).orElse(null);
        Curso curso = cursoRepository.findById(matriculaDTO.getCursoId()).orElse(null);

        if (aluno == null || curso == null) {
            return null;
        }

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setCurso(curso);
        matricula.setMensalidade(matriculaDTO.getMensalidade());
        matricula.setDesconto(matriculaDTO.getDesconto());
        matricula.setTipoDesconto(matriculaDTO.getTipoDesconto());
        matricula.calcularMensalidadeFinal();

        return matriculaRepository.save(matricula);
    }

    public List<Matricula> findAll() {
        List<Matricula> matriculas = matriculaRepository.findAll();
        for (Matricula matricula : matriculas) {
            matricula.calcularMensalidadeFinal();
        }
        return matriculas;
    }

    public Matricula findById(int id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        if (matricula.isPresent()) {
            matricula.get().calcularMensalidadeFinal();
            return matricula.get();
        }
        return null;
    }

    public Matricula update(int id, MatriculaDTO matriculaDTO) {
        Matricula matricula = findById(id);
        if (matricula == null) {
            return null;
        }

        Aluno aluno = alunoRepository.findById(matriculaDTO.getAlunoId()).orElse(null);
        Curso curso = cursoRepository.findById(matriculaDTO.getCursoId()).orElse(null);

        if (aluno == null || curso == null) {
            return null;
        }

        matricula.setAluno(aluno);
        matricula.setCurso(curso);
        matricula.setMensalidade(matriculaDTO.getMensalidade());
        matricula.setDesconto(matriculaDTO.getDesconto());
        matricula.setTipoDesconto(matriculaDTO.getTipoDesconto());
        matricula.calcularMensalidadeFinal();

        return matriculaRepository.save(matricula);
    }

    public void delete(int id) {
        matriculaRepository.deleteById(id);
    }
}
