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

import br.edu.ibmec.ibuni.dto.AlunoDTO;
import br.edu.ibmec.ibuni.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> getAll() {
        return new ResponseEntity<>(alunoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<AlunoDTO> getById(@PathVariable("matricula") int matricula) {
        return alunoService.findById(matricula)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> create(@RequestBody AlunoDTO alunoDTO) {
        try {
            AlunoDTO savedAluno = alunoService.save(alunoDTO);
            return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<AlunoDTO> update(@PathVariable("matricula") int matricula, @RequestBody AlunoDTO alunoDTO) {
        try {
            AlunoDTO updatedAluno = alunoService.update(matricula, alunoDTO);
            return new ResponseEntity<>(updatedAluno, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("matricula") int matricula) {
        try {
            alunoService.delete(matricula);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
