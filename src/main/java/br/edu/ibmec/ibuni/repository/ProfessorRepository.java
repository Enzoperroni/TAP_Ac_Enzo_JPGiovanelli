package br.edu.ibmec.ibuni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ibmec.ibuni.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
