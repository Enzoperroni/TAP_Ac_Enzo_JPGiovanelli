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

import br.edu.ibmec.ibuni.dto.DisciplinaDTO;
import br.edu.ibmec.ibuni.service.DisciplinaService;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> getAll() {
        return new ResponseEntity<>(disciplinaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DisciplinaDTO> getById(@PathVariable("codigo") int codigo) {
        return disciplinaService.findById(codigo)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DisciplinaDTO> create(@RequestBody DisciplinaDTO disciplinaDTO) {
        try {
            DisciplinaDTO savedDisciplina = disciplinaService.save(disciplinaDTO);
            return new ResponseEntity<>(savedDisciplina, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<DisciplinaDTO> update(@PathVariable("codigo") int codigo, @RequestBody DisciplinaDTO disciplinaDTO) {
        try {
            DisciplinaDTO updatedDisciplina = disciplinaService.update(codigo, disciplinaDTO);
            return new ResponseEntity<>(updatedDisciplina, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("codigo") int codigo) {
        try {
            disciplinaService.delete(codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
