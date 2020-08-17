package br.ucamcampos.coolgrade.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.ucamcampos.coolgrade.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Transactional(readOnly=true)
	Student findByEmail(String email);
}
