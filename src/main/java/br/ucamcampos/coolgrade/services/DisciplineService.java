package br.ucamcampos.coolgrade.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.ucamcampos.coolgrade.domain.Discipline;
import br.ucamcampos.coolgrade.dto.DisciplineDTO;
import br.ucamcampos.coolgrade.repositories.DisciplineRepository;

@Service
public class DisciplineService {

	@Autowired
	private DisciplineRepository repo;

	public Discipline insert(Discipline obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Discipline update(Discipline obj) {
		Discipline newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			//TODO throw new DataIntegrityException("Não é possível excluir porque há relacionados");
		}
	}

	public List<Discipline> findAll() {
		return repo.findAll();
	}

	public Discipline find(Integer id) {
		Optional<Discipline> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public Discipline fromDTO(DisciplineDTO objDto) {
		return new Discipline( objDto.getId(), objDto.getName(),objDto.getGrade1(), objDto.getGrade2(), objDto.getGrade3(),objDto.getGradePcl(), null);
	}

	private void updateData(Discipline newObj, Discipline obj) {
		newObj.setName(obj.getName());
		newObj.setGrade1(obj.getGrade1());
		newObj.setGrade2(obj.getGrade2());
		newObj.setGrade3(obj.getGrade3());
		newObj.setGradePcl(obj.getGradePcl());
	}

}
