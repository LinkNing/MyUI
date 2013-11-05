package net.nifoo.bean.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Number> {
	public void initialize(NotEmpty parameters) {
	}

	public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
		return number != null;
	}
}
