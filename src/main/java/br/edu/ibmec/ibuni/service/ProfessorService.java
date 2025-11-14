package br.edu.ibmec.ibuni.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.ibuni.dto.ProfessorDTO;
import br.edu.ibmec.ibuni.entity.Professor;
import br.edu.ibmec.ibuni.repository.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<ProfessorDTO> findAll() {
        return professorRepository.findAll().stream().map(professor -> {
            ProfessorDTO dto = new ProfessorDTO();
            dto.setId(professor.getId());
            dto.setNome(professor.getNome());
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<ProfessorDTO> findById(int id) {
        return professorRepository.findById(id).map(professor -> {
            ProfessorDTO dto = new ProfessorDTO();
            dto.setId(professor.getId());
            dto.setNome(professor.getNome());
            return dto;
        });
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        Professor savedProfessor = professorRepository.save(professor);
        professorDTO.setId(savedProfessor.getId());
        return professorDTO;
    }

    public ProfessorDTO update(int id, ProfessorDTO professorDTO) {
        return professorRepository.findById(id).map(professor -> {
            professor.setNome(professorDTO.getNome());
            Professor updatedProfessor = professorRepository.save(professor);
            professorDTO.setId(updatedProfessor.getId());
            return professorDTO;
        }).orElseThrow(() -> new RuntimeException("Professor não encontrado com o id: " + id));
    }

    public void delete(int id) {
        professorRepository.deleteById(id);
    }
}
