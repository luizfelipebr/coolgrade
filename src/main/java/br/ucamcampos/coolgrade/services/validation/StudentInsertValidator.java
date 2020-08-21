package br.ucamcampos.coolgrade.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.ucamcampos.coolgrade.domain.Student;
import br.ucamcampos.coolgrade.dto.StudentNewDTO;
import br.ucamcampos.coolgrade.repositories.StudentRepository;
import br.ucamcampos.coolgrade.resources.exception.FieldMessage;

public class StudentInsertValidator implements ConstraintValidator<StudentInsert, StudentNewDTO> {

	@Autowired
	private StudentRepository repo;

	@Override
	public void initialize(StudentInsert ann) {
	}

	@Override
	public boolean isValid(StudentNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Student aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
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
