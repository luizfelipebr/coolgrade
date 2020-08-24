package br.ucamcampos.coolgrade.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ucamcampos.coolgrade.domain.Student;
import br.ucamcampos.coolgrade.dto.StudentDTO;
import br.ucamcampos.coolgrade.dto.StudentNewDTO;
import br.ucamcampos.coolgrade.services.StudentService;

@RestController
@RequestMapping(value = "/students")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Location")
public class StudentResource {

	@Autowired
	private StudentService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Student>> findAll() {
		List<Student> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> find(@PathVariable Integer id) {
		Student obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Student> insert(@Valid @RequestBody StudentNewDTO objDto) {
		Student obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Student> update(@Valid @RequestBody StudentDTO objDto, @PathVariable Integer id) {
		Student obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
