package br.ucamcampos.coolgrade.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ucamcampos.coolgrade.domain.Discipline;
import br.ucamcampos.coolgrade.domain.Student;
import br.ucamcampos.coolgrade.services.DisciplineService;
import br.ucamcampos.coolgrade.services.StudentService;

@RestController
@RequestMapping(value="/students")
public class StudentResource {
	
	@Autowired
	private StudentService service;
	
	@Autowired
	private DisciplineService disciplineService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Student>> findAll() {
		List<Student> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Student> find(@PathVariable Integer id) {
		Student obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Student obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Student obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> insertDiscipline(@Valid @RequestBody Discipline obj, @PathVariable Integer id) {
		Student std = service.find(id);
		obj.setStudent(std);
		obj = disciplineService.insert(obj);
		std.getDisciplines().add(obj);
		service.update(std);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
