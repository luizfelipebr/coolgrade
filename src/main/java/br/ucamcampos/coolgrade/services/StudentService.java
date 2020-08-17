package br.ucamcampos.coolgrade.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import br.ucamcampos.coolgrade.domain.Student;
import br.ucamcampos.coolgrade.dto.StudentDTO;
import br.ucamcampos.coolgrade.repositories.DisciplineRepository;
import br.ucamcampos.coolgrade.repositories.StudentRepository;

public class StudentService {

	@Autowired
	private StudentRepository repo;

	@Autowired
	private DisciplineRepository disciplineRepository;

	public Student insert(Student obj) {
		obj.setId(null);
		obj = repo.save(obj);
		disciplineRepository.saveAll(obj.getDisciplines());
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
			//throw new DataIntegrityException("Não é possível excluir porque há relacionados");
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
	}

}
