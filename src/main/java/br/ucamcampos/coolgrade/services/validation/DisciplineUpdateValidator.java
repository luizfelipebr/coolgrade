package br.ucamcampos.coolgrade.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.ucamcampos.coolgrade.dto.DisciplineDTO;
import br.ucamcampos.coolgrade.resources.exception.FieldMessage;

public class DisciplineUpdateValidator implements ConstraintValidator<DisciplineUpdate, DisciplineDTO> {

	@Override
	public void initialize(DisciplineUpdate ann) {
	}

	@Override
	public boolean isValid(DisciplineDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
