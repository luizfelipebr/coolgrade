package br.ucamcampos.coolgrade.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/Disciplines")
public class StudentResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public String showAll() {
		return "TODO";
	}

}
