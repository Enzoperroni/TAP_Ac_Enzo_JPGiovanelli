package br.edu.ibmec.ibuni.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.ibuni.dto.AlunoDTO;
import br.edu.ibmec.ibuni.entity.Aluno;
import br.edu.ibmec.ibuni.entity.Curso;
import br.edu.ibmec.ibuni.repository.AlunoRepository;
import br.edu.ibmec.ibuni.repository.CursoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<AlunoDTO> findAll() {
        return this.alunoRepository.findAll().stream().map(aluno -> {
            AlunoDTO dto = new AlunoDTO();
            dto.setMatricula(aluno.getMatricula());
            dto.setNome(aluno.getNome());
            dto.setDataNascimento(aluno.getDataNascimento());
            dto.setMatriculaAtiva(aluno.isMatriculaAtiva());
            dto.setEstadoCivil(aluno.getEstadoCivil());
            dto.setTelefones(aluno.getTelefones());
            if (aluno.getCurso() != null) {
                dto.setCurso(aluno.getCurso().getCodigo());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<AlunoDTO> findById(int matricula) {
        return this.alunoRepository.findById(matricula).map(aluno -> {
            AlunoDTO dto = new AlunoDTO();
            dto.setMatricula(aluno.getMatricula());
            dto.setNome(aluno.getNome());
            dto.setDataNascimento(aluno.getDataNascimento());
            dto.setMatriculaAtiva(aluno.isMatriculaAtiva());
            dto.setEstadoCivil(aluno.getEstadoCivil());
            dto.setTelefones(aluno.getTelefones());
            if (aluno.getCurso() != null) {
                dto.setCurso(aluno.getCurso().getCodigo());
            }
            return dto;
        });
    }

    public AlunoDTO save(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setMatricula(alunoDTO.getMatricula());
        aluno.setNome(alunoDTO.getNome());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());
        aluno.setMatriculaAtiva(alunoDTO.isMatriculaAtiva());
        aluno.setEstadoCivil(alunoDTO.getEstadoCivil());
        aluno.setTelefones(alunoDTO.getTelefones());

        if (alunoDTO.getCurso() != 0) {
            Optional<Curso> curso = this.cursoRepository.findById(alunoDTO.getCurso());
            curso.ifPresent(aluno::setCurso);
        }

        Aluno savedAluno = this.alunoRepository.save(aluno);
        alunoDTO.setMatricula(savedAluno.getMatricula());
        return alunoDTO;
    }

    public AlunoDTO update(int matricula, AlunoDTO alunoDTO) {
        return this.alunoRepository.findById(matricula).map(aluno -> {
            aluno.setNome(alunoDTO.getNome());
            aluno.setDataNascimento(alunoDTO.getDataNascimento());
            aluno.setMatriculaAtiva(alunoDTO.isMatriculaAtiva());
            aluno.setEstadoCivil(alunoDTO.getEstadoCivil());
            aluno.setTelefones(alunoDTO.getTelefones());

            if (alunoDTO.getCurso() != 0) {
                Optional<Curso> curso = this.cursoRepository.findById(alunoDTO.getCurso());
                curso.ifPresent(aluno::setCurso);
            }
            Aluno updatedAluno = this.alunoRepository.save(aluno);
            alunoDTO.setMatricula(updatedAluno.getMatricula());
            return alunoDTO;
        }).orElseThrow(() -> new RuntimeException("Aluno não encontrado com a matrícula: " + matricula));
    }

    public void delete(int matricula) {
        this.alunoRepository.deleteById(matricula);
    }
}
