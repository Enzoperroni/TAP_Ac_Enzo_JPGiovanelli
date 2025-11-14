package br.edu.ibmec.ibuni.service;

import br.edu.ibmec.ibuni.dto.AlunoDTO;
import br.edu.ibmec.ibuni.entity.Aluno;
import br.edu.ibmec.ibuni.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno getById(int id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.orElse(null);
    }

    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    public Aluno save(AlunoDTO alunoDto) {
        Assert.notNull(alunoDto.getNome(), "O nome do aluno não pode ser nulo.");
        Assert.isTrue(alunoDto.getNome().length() > 2, "O nome do aluno deve ter mais de 2 caracteres.");

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDto.getNome());
        aluno.setDataNascimento(alunoDto.getDataNascimento());
        aluno.setMatriculaAtiva(alunoDto.isMatriculaAtiva());
        aluno.setEstadoCivil(alunoDto.getEstadoCivil());
        aluno.setTelefones(alunoDto.getTelefones());
        
        return alunoRepository.save(aluno);
    }

    public Aluno update(int id, AlunoDTO alunoDto) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        Assert.isTrue(optionalAluno.isPresent(), "Aluno não encontrado.");

        Aluno aluno = optionalAluno.get();
        aluno.setNome(alunoDto.getNome());
        aluno.setDataNascimento(alunoDto.getDataNascimento());
        aluno.setMatriculaAtiva(alunoDto.isMatriculaAtiva());
        aluno.setEstadoCivil(alunoDto.getEstadoCivil());
        aluno.setTelefones(alunoDto.getTelefones());

        return alunoRepository.save(aluno);
    }

    public void delete(int id) {
        alunoRepository.deleteById(id);
    }
}
