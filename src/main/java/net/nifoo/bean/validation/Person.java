package net.nifoo.bean.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface Person {
	@NotNull(message = "The name of employee can not be null")
	@Size(min = 1, max = 10, message = "The size of employee's name must between 1 and 10")
	String getName();
}
