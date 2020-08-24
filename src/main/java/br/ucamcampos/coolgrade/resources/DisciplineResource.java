package br.ucamcampos.coolgrade.resources;

import java.net.URI;

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

import br.ucamcampos.coolgrade.domain.Discipline;
import br.ucamcampos.coolgrade.dto.DisciplineDTO;
import br.ucamcampos.coolgrade.dto.DisciplineNewDTO;
import br.ucamcampos.coolgrade.services.DisciplineService;

@RestController
@RequestMapping(value = "/disciplines")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Location")
public class DisciplineResource {

	@Autowired
	private DisciplineService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Discipline> find(@PathVariable Integer id) {
		Discipline obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Discipline> insertDiscipline(@Valid @RequestBody DisciplineNewDTO objDto) {
		Discipline obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Discipline> update(@Valid @RequestBody DisciplineDTO objDto, @PathVariable Integer id) {
		Discipline obj = service.fromDTO(objDto);
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
