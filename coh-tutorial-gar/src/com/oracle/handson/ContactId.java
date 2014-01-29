package com.oracle.handson;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

import com.tangosol.util.Base;
import com.tangosol.util.HashHelper;

import java.io.IOException;

public class ContactId implements PortableObject {
	
	public ContactId() {
	}

	public ContactId(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void readExternal(PofReader reader) throws IOException {
		firstName = reader.readString(0);
		lastName = reader.readString(1);
	}

	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeString(0, firstName);
		writer.writeString(1, lastName);
	}

	public boolean equals(Object oThat) {
		if (this == oThat) {
			return true;
		}
		if (oThat == null) {
			return false;
		}

		ContactId that = (ContactId) oThat;
		return Base.equals(getFirstName(), that.getFirstName())
				&& Base.equals(getLastName(), that.getLastName());
	}

	public int hashCode() {
		return HashHelper.hash(getFirstName(),
				HashHelper.hash(getLastName(), 0));
	}

	public String toString() {
		return getFirstName() + " " + getLastName();
	}

	private String firstName;

	private String lastName;
}