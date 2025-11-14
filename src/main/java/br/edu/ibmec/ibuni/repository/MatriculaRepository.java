package br.edu.ibmec.ibuni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ibmec.ibuni.entity.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    
}
