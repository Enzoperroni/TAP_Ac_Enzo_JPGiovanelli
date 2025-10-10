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

import br.edu.ibmec.ibuni.dto.TurmaDTO;
import br.edu.ibmec.ibuni.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> getAll() {
        return new ResponseEntity<>(turmaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<TurmaDTO> getById(@PathVariable("codigo") int codigo) {
        return turmaService.findById(codigo)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TurmaDTO> create(@RequestBody TurmaDTO turmaDTO) {
        try {
            TurmaDTO savedTurma = turmaService.save(turmaDTO);
            return new ResponseEntity<>(savedTurma, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<TurmaDTO> update(@PathVariable("codigo") int codigo, @RequestBody TurmaDTO turmaDTO) {
        try {
            TurmaDTO updatedTurma = turmaService.update(codigo, turmaDTO);
            return new ResponseEntity<>(updatedTurma, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("codigo") int codigo) {
        try {
            turmaService.delete(codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
