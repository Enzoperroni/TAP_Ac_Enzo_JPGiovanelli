package br.edu.ibmec.ibuni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ibmec.ibuni.dto.MatriculaDTO;
import br.edu.ibmec.ibuni.entity.Matricula;
import br.edu.ibmec.ibuni.service.MatriculaService;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<Matricula> create(@RequestBody MatriculaDTO matriculaDTO) {
        Matricula matricula = matriculaService.create(matriculaDTO);
        return new ResponseEntity<>(matricula, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll() {
        List<Matricula> matriculas = matriculaService.findAll();
        return new ResponseEntity<>(matriculas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable int id) {
        Matricula matricula = matriculaService.findById(id);
        if (matricula == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matricula, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update(@PathVariable int id, @RequestBody MatriculaDTO matriculaDTO) {
        Matricula matricula = matriculaService.update(id, matriculaDTO);
        if (matricula == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matricula, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        matriculaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
