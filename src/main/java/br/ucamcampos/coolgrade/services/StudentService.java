package br.ucamcampos.coolgrade.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.ucamcampos.coolgrade.domain.Discipline;
import br.ucamcampos.coolgrade.domain.Student;
import br.ucamcampos.coolgrade.dto.StudentDTO;
import br.ucamcampos.coolgrade.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repo;

	@Autowired
	private DisciplineService disciplineService;

	public Student insert(Student obj) {
		obj.setId(null);
		obj = repo.save(obj);
		for(Discipline discObj : obj.getDisciplines()) {
			disciplineService.insert(discObj);
		}
		return obj;
	}

	public Student update(Student obj) {
		Student newObj = find(obj.getId());
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

	public List<Student> findAll() {
		return repo.findAll();
	}

	public Student find(Integer id) {
		Optional<Student> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public Student fromDTO(StudentDTO objDto) {
		return new Student( objDto.getId(), objDto.getName(), objDto.getEmail(),null);
	}

	private void updateData(Student newObj, Student obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		for(Discipline discObj : obj.getDisciplines()) {
			if (discObj.getId() == null) disciplineService.insert(discObj);
			disciplineService.update(discObj);
		}
	}

}
