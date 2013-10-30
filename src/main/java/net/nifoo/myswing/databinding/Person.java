package net.nifoo.myswing.databinding;

import java.util.Date;

import org.jdesktop.beans.AbstractBean;

public class Person extends AbstractBean{


	private String name;
	private Date birthday;
	private String phone;
	private String email;
	private String address;

	public Person(String name, Date birthday, String phone, String email, String address) {
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
        firePropertyChange("name", oldName, name);
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
