package br.ucamcampos.coolgrade.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ucamcampos.coolgrade.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Transactional
	Student findByEmail(String email);
}
