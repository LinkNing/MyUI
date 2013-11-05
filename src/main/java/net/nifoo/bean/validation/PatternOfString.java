package net.nifoo.bean.validation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 多值约束：对于同一个目标元素，在进行约束注解声明时可以同时使用不同的属性达到对该目标元素进行多值验证的目的。<br/>
 * 实现多值约束只需要在定义约束注解的同时定义一个 List（@interface List{}）。
 * 使用该约束注解时，Bean Validation 将 value 数组里面的每一个元素都处理为一个普通的约束注解，并对其进行验证，所有约束条件均符合时才会验证通过。
 * @author Nifoo Ning
 *
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PatternOfStringValidator.class)
public @interface PatternOfString {
	String mustContainLetter();

	String message() default "this pattern may not be right";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		PatternOfString[] value();
	}
}
