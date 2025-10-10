package br.edu.ibmec.ibuni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ibmec.ibuni.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
