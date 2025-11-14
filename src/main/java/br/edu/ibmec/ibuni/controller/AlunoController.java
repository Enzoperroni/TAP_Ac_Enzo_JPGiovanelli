package br.edu.ibmec.ibuni.controller;

import br.edu.ibmec.ibuni.dto.AlunoDTO;
import br.edu.ibmec.ibuni.entity.Aluno;
import br.edu.ibmec.ibuni.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = alunoService.getAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable("id") int id) {
        Aluno aluno = alunoService.getById(id);
        if (aluno != null) {
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody AlunoDTO alunoDTO) {
        try {
            Aluno savedAluno = alunoService.save(alunoDTO);
            return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable("id") int id, @RequestBody AlunoDTO alunoDTO) {
        try {
            Aluno updatedAluno = alunoService.update(id, alunoDTO);
            return new ResponseEntity<>(updatedAluno, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        try {
            alunoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
