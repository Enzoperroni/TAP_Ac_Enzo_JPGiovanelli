package br.edu.ibmec.ibuni.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.ibuni.dto.CursoDTO;
import br.edu.ibmec.ibuni.entity.Curso;
import br.edu.ibmec.ibuni.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoDTO> findAll() {
        return this.cursoRepository.findAll().stream().map(curso -> {
            CursoDTO dto = new CursoDTO();
            dto.setCodigo(curso.getCodigo());
            dto.setNome(curso.getNome());
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<CursoDTO> findById(int codigo) {
        return this.cursoRepository.findById(codigo).map(curso -> {
            CursoDTO dto = new CursoDTO();
            dto.setCodigo(curso.getCodigo());
            dto.setNome(curso.getNome());
            return dto;
        });
    }

    public CursoDTO save(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setNome(cursoDTO.getNome());
        Curso savedCurso = this.cursoRepository.save(curso);
        cursoDTO.setCodigo(savedCurso.getCodigo());
        return cursoDTO;
    }

    public CursoDTO update(int codigo, CursoDTO cursoDTO) {
        return this.cursoRepository.findById(codigo).map(curso -> {
            curso.setNome(cursoDTO.getNome());
            Curso updatedCurso = this.cursoRepository.save(curso);
            cursoDTO.setCodigo(updatedCurso.getCodigo());
            return cursoDTO;
        }).orElseThrow(() -> new RuntimeException("Curso não encontrado com o código: " + codigo));
    }

    public void delete(int codigo) {
        this.cursoRepository.deleteById(codigo);
    }
}
