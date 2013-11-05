package net.nifoo.bean.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

public class Employee implements Person {

	@NotEmpty(message = "The id of employee can not be null", groups = RequiredGroup.class)
	private Integer id;

	private String name;

	@AgeRule(groups = Default.class)
	private Integer age;

	@PatternOfString.List({ @PatternOfString(mustContainLetter = "CH", message = "It does not belong to China"),
			@PatternOfString(mustContainLetter = "MainLand", message = "It does not belong to MainLand") })
	private String place;

	public Employee() {
	}

	public Employee(Integer id, String name, Integer age, String place) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.place = place;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public static void main(String[] args) {
		Employee employee = new Employee(1, "Tom", 160, "CHINA");
		//employee.setName("Zhang Guan Nan");
		//employee.setPlace("C");
		//employee.setId(null);

		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<Employee>> set = validator.validate(employee, Default.class);

		for (ConstraintViolation<Employee> constraintViolation : set) {
			System.out.println(constraintViolation.getMessage());
		}
	}
}
