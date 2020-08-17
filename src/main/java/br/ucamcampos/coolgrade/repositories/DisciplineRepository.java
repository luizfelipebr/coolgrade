package br.ucamcampos.coolgrade.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.ucamcampos.coolgrade.domain.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {

	@Transactional(readOnly=true)
	Discipline findByEmail(String email);
}
