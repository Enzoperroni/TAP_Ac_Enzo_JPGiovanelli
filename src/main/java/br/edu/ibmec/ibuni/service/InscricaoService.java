package br.edu.ibmec.ibuni.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.ibuni.dto.InscricaoDTO;
import br.edu.ibmec.ibuni.entity.Aluno;
import br.edu.ibmec.ibuni.entity.Inscricao;
import br.edu.ibmec.ibuni.entity.Situacao;
import br.edu.ibmec.ibuni.entity.Turma;
import br.edu.ibmec.ibuni.repository.AlunoRepository;
import br.edu.ibmec.ibuni.repository.InscricaoRepository;
import br.edu.ibmec.ibuni.repository.TurmaRepository;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public List<InscricaoDTO> findAll() {
        return inscricaoRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<InscricaoDTO> findById(Long id) {
        return inscricaoRepository.findById(id).map(this::convertToDto);
    }

    public InscricaoDTO save(InscricaoDTO inscricaoDTO) {
        Inscricao inscricao = new Inscricao();
        updateInscricaoFromDto(inscricao, inscricaoDTO);
        inscricao.setSituacao(Situacao.CURSANDO);
        Inscricao savedInscricao = inscricaoRepository.save(inscricao);
        return convertToDto(savedInscricao);
    }

    public InscricaoDTO update(Long id, InscricaoDTO inscricaoDTO) {
        return inscricaoRepository.findById(id).map(inscricao -> {
            updateInscricaoFromDto(inscricao, inscricaoDTO);
            Inscricao updatedInscricao = inscricaoRepository.save(inscricao);
            return convertToDto(updatedInscricao);
        }).orElseThrow(() -> new RuntimeException("Inscrição não encontrada com o id: " + id));
    }

    public void delete(Long id) {
        inscricaoRepository.deleteById(id);
    }

    private InscricaoDTO convertToDto(Inscricao inscricao) {
        InscricaoDTO dto = new InscricaoDTO();
        dto.setId(inscricao.getId());
        dto.setAvaliacao1(inscricao.getAvaliacao1());
        dto.setAvaliacao2(inscricao.getAvaliacao2());
        dto.setMedia(inscricao.getMedia());
        dto.setNumFaltas(inscricao.getNumFaltas());
        dto.setSituacao(inscricao.getSituacao());
        if (inscricao.getAluno() != null) {
            dto.setAluno(inscricao.getAluno().getMatricula());
        }
        if (inscricao.getTurma() != null) {
            dto.setTurma(inscricao.getTurma().getCodigo());
        }
        return dto;
    }

    private void updateInscricaoFromDto(Inscricao inscricao, InscricaoDTO inscricaoDTO) {
        inscricao.setAvaliacao1(inscricaoDTO.getAvaliacao1());
        inscricao.setAvaliacao2(inscricaoDTO.getAvaliacao2());
        inscricao.setNumFaltas(inscricaoDTO.getNumFaltas());
        
        float media = (inscricao.getAvaliacao1() + inscricao.getAvaliacao2()) / 2;
        inscricao.setMedia(media);

        if (media >= 7.0) {
            inscricao.setSituacao(Situacao.APROVADO);
        } else {
            inscricao.setSituacao(Situacao.REPROVADO);
        }

        if (inscricaoDTO.getAluno() != 0) {
            Optional<Aluno> aluno = alunoRepository.findById(inscricaoDTO.getAluno());
            aluno.ifPresent(inscricao::setAluno);
        }

        if (inscricaoDTO.getTurma() != 0) {
            Optional<Turma> turma = turmaRepository.findById(inscricaoDTO.getTurma());
            turma.ifPresent(inscricao::setTurma);
        }
    }
}
