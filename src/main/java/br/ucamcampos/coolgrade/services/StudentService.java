package br.ucamcampos.coolgrade.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.ucamcampos.coolgrade.domain.Discipline;
import br.ucamcampos.coolgrade.domain.Student;
import br.ucamcampos.coolgrade.dto.StudentDTO;
import br.ucamcampos.coolgrade.dto.StudentNewDTO;
import br.ucamcampos.coolgrade.repositories.StudentRepository;
import br.ucamcampos.coolgrade.services.exceptions.DataIntegrityException;
import br.ucamcampos.coolgrade.services.exceptions.ObjectNotFoundException;

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
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

	public List<Student> findAll() {
		return repo.findAll();
	}

	public Student find(Integer id) {
		Optional<Student> obj = repo.findById(id);
		if(obj.isEmpty()) throw new ObjectNotFoundException("Aluno não encontrado");
		return obj.orElse(null);
	}

	public Student fromDTO(StudentDTO objDto) {
		return new Student(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	public Student fromDTO(StudentNewDTO objDto) {
		return new Student(null,objDto.getName(), objDto.getEmail());
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
