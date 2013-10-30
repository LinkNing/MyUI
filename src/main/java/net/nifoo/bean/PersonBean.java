package net.nifoo.bean;

import java.beans.PropertyVetoException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jdesktop.beans.AbstractBean;

public class PersonBean extends AbstractBean {

	private String name;

	private Integer age;

	public PersonBean(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldValue = this.name;
		this.name = name;
		firePropertyChange("name", oldValue, name);
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		Integer oldValue = getAge();
		this.age = age;
		try {
			fireVetoableChange("age", oldValue, age);
			firePropertyChange("age", oldValue, age);
		} catch (PropertyVetoException e) {
			e.printStackTrace(System.out);
			this.age = oldValue;
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(age).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name).append("age", age).toString();
	}

}
