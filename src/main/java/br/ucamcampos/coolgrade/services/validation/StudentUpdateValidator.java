package br.ucamcampos.coolgrade.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.ucamcampos.coolgrade.domain.Student;
import br.ucamcampos.coolgrade.dto.StudentDTO;
import br.ucamcampos.coolgrade.repositories.StudentRepository;
import br.ucamcampos.coolgrade.resources.exception.FieldMessage;

public class StudentUpdateValidator implements ConstraintValidator<StudentUpdate, StudentDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private StudentRepository repo;
	
	@Override
	public void initialize(StudentUpdate ann) {
	}

	@Override
	public boolean isValid(StudentDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Student aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
