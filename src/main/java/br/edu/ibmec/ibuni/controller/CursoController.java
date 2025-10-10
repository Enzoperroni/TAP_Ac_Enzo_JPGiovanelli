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

import br.edu.ibmec.ibuni.dto.CursoDTO;
import br.edu.ibmec.ibuni.service.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAll() {
        return new ResponseEntity<>(cursoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CursoDTO> getById(@PathVariable("codigo") int codigo) {
        return cursoService.findById(codigo)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CursoDTO> create(@RequestBody CursoDTO cursoDTO) {
        try {
            CursoDTO savedCurso = cursoService.save(cursoDTO);
            return new ResponseEntity<>(savedCurso, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<CursoDTO> update(@PathVariable("codigo") int codigo, @RequestBody CursoDTO cursoDTO) {
        try {
            CursoDTO updatedCurso = cursoService.update(codigo, cursoDTO);
            return new ResponseEntity<>(updatedCurso, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("codigo") int codigo) {
        try {
            cursoService.delete(codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
