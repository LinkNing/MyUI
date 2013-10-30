package net.nifoo.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class PropertyChangeExample {

	public static void main(String[] argv) {
		PersonBean person = new PersonBean("Tom", 26);
		person.addPropertyChangeListener(new PersonChangeListener());
		person.addVetoableChangeListener("age", new AgeVetoableChangeListener());

		System.out.println(person);
		person.setName("Jim");
		person.setAge(45);
		person.setAge(168);
		System.out.println(person);
	}
}

class AgeVetoableChangeListener implements VetoableChangeListener {

	@Override
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
		if ("age".equals(evt.getPropertyName())) {
			Integer age = (Integer) evt.getNewValue();
			if (age == null || age < 0 || age > 150)
				throw new PropertyVetoException("年龄必须在[0-150]内.", evt);
		}
	}
}

class PersonChangeListener implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.printf("Change [%s] from '%s' to '%s' \n",

		evt.getPropertyName(),

		evt.getOldValue(),

		evt.getNewValue());
	}
}
