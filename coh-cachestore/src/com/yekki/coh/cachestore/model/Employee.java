package com.yekki.coh.cachestore.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CS_EX_EMPLOYEE")
public class Employee implements Serializable {
	private static final long serialVersionUID = 4943612673242238866L;
	@Id
	private int id;
	private String firstName;
	private String lastName;

	public Employee() {
	}

	public int getId() {
		return id;
	}

	public void setId(int empId) {
		this.id = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		this.firstName = fName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}

	public String toString() {
		return "Employee(" + getId() + ": " + getLastName() + ", "
				+ getFirstName() + ")";
	}

}
