package br.ucamcampos.coolgrade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ucamcampos.coolgrade.domain.Discipline;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {

}
