package com.oracle.handson;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.Base;

public class Address implements PortableObject {

	public Address() {
    }
	
	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public Address(String street1, String street2, String city, String state,
			String zip, String country) {
		super();
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void readExternal(PofReader reader) throws IOException {
		setStreet1(reader.readString(0));
		setStreet2(reader.readString(1));
		setCity(reader.readString(2));
		setState(reader.readString(3));
		setZip(reader.readString(4));
		setCountry(reader.readString(5));
	}

	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeString(0, getStreet1());
		writer.writeString(1, getStreet2());
		writer.writeString(2, getCity());
		writer.writeString(3, getState());
		writer.writeString(4, getZip());
		writer.writeString(5, getCountry());
	}

	public boolean equals(Object oThat) {
		if (this == oThat) {
			return true;
		}
		if (oThat == null) {
			return false;
		}

		Address that = (Address) oThat;
		return Base.equals(getStreet1(), that.getStreet1())
				&& Base.equals(getStreet2(), that.getStreet2())
				&& Base.equals(getCity(), that.getCity())
				&& Base.equals(getState(), that.getState())
				&& Base.equals(getZip(), that.getZip())
				&& Base.equals(getCountry(), that.getCountry());
	}

	public String toString() {
		return getStreet1() + "\n" + getStreet2() + "\n" + getCity() + ", "
				+ getState() + " " + getZip() + "\n" + getCountry();
	}

	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zip;
	private String country;
}
