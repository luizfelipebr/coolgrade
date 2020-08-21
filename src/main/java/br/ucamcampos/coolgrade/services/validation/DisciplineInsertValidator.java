package br.ucamcampos.coolgrade.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.ucamcampos.coolgrade.dto.DisciplineNewDTO;
import br.ucamcampos.coolgrade.resources.exception.FieldMessage;

public class DisciplineInsertValidator implements ConstraintValidator<DisciplineInsert, DisciplineNewDTO> {

	@Override
	public void initialize(DisciplineInsert ann) {
	}

	@Override
	public boolean isValid(DisciplineNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
