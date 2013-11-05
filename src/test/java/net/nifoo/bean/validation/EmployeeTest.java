package net.nifoo.bean.validation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeTest {
	@SuppressWarnings("unchecked")
	private static final ConstraintViolation<Employee>[] EmptyConstraints = new ConstraintViolation[0];

	private static ValidatorFactory vf;
	private Validator validator;
	private Employee employee;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vf = Validation.buildDefaultValidatorFactory();
	}

	@Before
	public void setUp() throws Exception {
		validator = vf.getValidator();
		employee = new Employee(1, "Tom", 20, "MainLand_CHINA");
	}

	/**
	 * 测试验证分组
	 */
	@Test
	public final void testPersonGroupValidation() {
		employee.setName("Tom");
		Set<ConstraintViolation<Employee>> set = validator.validate(employee, Person.class);
		ConstraintViolation<Employee>[] constraints = set.toArray(EmptyConstraints);
		assertEquals("Name = 'Tom'", constraints.length, 0);

		employee.setName("");
		set = validator.validate(employee, Person.class);
		constraints = set.toArray(EmptyConstraints);
		assertEquals(constraints.length, 1);
		assertEquals("Name = ''", constraints[0].getMessage(), "The size of employee's name must between 1 and 10");

		employee.setName("Zhang Guan Nan");
		set = validator.validate(employee, Person.class);
		constraints = set.toArray(EmptyConstraints);
		assertEquals(constraints.length, 1);
		assertEquals("Name = 'Zhang Guan Nan'", constraints[0].getMessage(),
				"The size of employee's name must between 1 and 10");
	}

	/**
	 * 测试自定义验证
	 */
	@Test
	public final void testRequiredGroupValidation() {
		employee.setId(null);
		Set<ConstraintViolation<Employee>> set = validator.validate(employee, RequiredGroup.class);
		ConstraintViolation<Employee>[] constraints = set.toArray(EmptyConstraints);
		assertEquals("id = null", constraints.length, 1);
		assertEquals("id = null", constraints[0].getMessage(), "The id of employee can not be null");
	}

	/**
	 * 测试 组合约束
	 */
	@Test
	public final void testCompositeValidation() {
		employee.setAge(null);
		Set<ConstraintViolation<Employee>> set = validator.validate(employee, Default.class);
		ConstraintViolation<Employee>[] constraints = set.toArray(EmptyConstraints);
		assertEquals("age = null", constraints.length, 1);

		employee.setAge(-10);
		set = validator.validate(employee, Default.class);
		constraints = set.toArray(EmptyConstraints);
		assertEquals("age = -10", constraints.length, 1);

		employee.setAge(200);
		set = validator.validate(employee, Default.class);
		constraints = set.toArray(EmptyConstraints);
		assertEquals("age = 200", constraints.length, 1);
	}
	
	/**
	 * 测试 多值约束
	 */
	@Test
	public final void testMultipleValueValidation() {
		employee.setPlace("MainLand_CHINA");
		Set<ConstraintViolation<Employee>> set = validator.validate(employee);
		assertEquals("place = MainLand_CHINA", set.size(), 0);

		employee.setPlace("MainLand");
		set = validator.validate(employee);
		assertEquals("place = MainLand", set.size(), 1);

		employee.setPlace("CHINA");
		set = validator.validate(employee);
		assertEquals("place = CHINA", set.size(), 1);
		
		employee.setPlace("XX");
		set = validator.validate(employee);
		assertEquals("place = XX", set.size(), 2);
	}

}
