package net.nifoo.bean.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeRuleValidator implements ConstraintValidator<AgeRule, Number> {

	public void initialize(AgeRule parameters) {
	}

	public boolean isValid(Number age, ConstraintValidatorContext constraintValidatorContext) {
		return age != null;
		//return StringUtils.isNotBlank(string);
	}
}
