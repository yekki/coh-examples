package com.yekki.coh.grid.model;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import oracle.eclipselink.coherence.integrated.config.CoherenceReadCustomizer;

import org.eclipse.persistence.annotations.Customizer;

@Entity
@Table(name="TLG_GR_EMPLOYEE")
@Customizer(CoherenceReadCustomizer.class)
@NamedQueries({
	@NamedQuery(name="Employee.findByLastNameLike", query="SELECT e FROM Employee e WHERE e.lastName LIKE :lastName"),
})
public class Employee implements Serializable {
	private static final long serialVersionUID = -4158718142583269181L;

	@Id
	@GeneratedValue
	private int id;
	private String firstName;
	private String lastName;
	@OneToMany(mappedBy="owner", cascade = ALL)
	private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
	@OneToOne(cascade = ALL, fetch=FetchType.LAZY)
	private Address address;
	private Long version;

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

	public Long getVersion() {
		return version;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumberList) {
		this.phoneNumbers = phoneNumberList;
	}

	public PhoneNumber addPhoneNumber(PhoneNumber phoneNumber) {
		getPhoneNumbers().add(phoneNumber);
		phoneNumber.setOwner(this);
		return phoneNumber;
	}

	public PhoneNumber addPhoneNumber(String type, String areaCode, String number) {
		PhoneNumber phoneNumber = new PhoneNumber(type, areaCode, number);
		return addPhoneNumber(phoneNumber);
	}

	public PhoneNumber removePhoneNumber(PhoneNumber phoneNumber) {
		getPhoneNumbers().remove(phoneNumber);
		phoneNumber.setOwner(null);
		return phoneNumber;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public String toString() {
		return "Employee(" + getId() + ": " + getLastName() + ", " + getFirstName() + ")";
	}

}
