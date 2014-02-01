package com.oracle.handson;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.HashHelper;

public class PhoneNumber implements PortableObject {

	private short accessCode;
	private short countryCode;
	private short areaCode;
	private int localNumber;

	public PhoneNumber() {
	}

	public PhoneNumber(short accessCode, short countryCode, short areaCode,
			int localNumber) {
		super();
		this.accessCode = accessCode;
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.localNumber = localNumber;
	}

	public void setAccessCode(short accessCode) {
		this.accessCode = accessCode;
	}

	public short getAccessCode() {
		return accessCode;
	}

	public void setCountryCode(short countryCode) {
		this.countryCode = countryCode;
	}

	public short getCountryCode() {
		return countryCode;
	}

	public void setAreaCode(short areaCode) {
		this.areaCode = areaCode;
	}

	public short getAreaCode() {
		return areaCode;
	}

	public void setLocalNumber(int LocalNumber) {
		this.localNumber = LocalNumber;
	}

	public int getLocalNumber() {
		return localNumber;
	}

	public void readExternal(PofReader reader) throws IOException {
		setAccessCode(reader.readShort(0));
		setCountryCode(reader.readShort(1));
		setAreaCode(reader.readShort(2));
		setLocalNumber(reader.readInt(3));
	}

	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeShort(0, getAccessCode());
		writer.writeShort(1, getCountryCode());
		writer.writeShort(2, getAreaCode());
		writer.writeInt(3, getLocalNumber());
	}

	public boolean equals(Object oThat) {
		if (this == oThat) {
			return true;
		}
		if (oThat == null) {
			return false;
		}

		PhoneNumber that = (PhoneNumber) oThat;
		return getAccessCode() == that.getAccessCode()
				&& getCountryCode() == that.getCountryCode()
				&& getAreaCode() == that.getAreaCode()
				&& getLocalNumber() == that.getLocalNumber();
	}

	public int hashCode() {
		return HashHelper.hash(getAreaCode(),
				HashHelper.hash(getLocalNumber(), 0));
	}

	public String toString() {
		return "+" + getAccessCode() + " " + getCountryCode() + " "
				+ getAreaCode() + " " + getLocalNumber();
	}
}