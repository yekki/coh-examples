package com.yekki.coh.grid.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import oracle.eclipselink.coherence.integrated.config.CoherenceReadCustomizer;

import org.eclipse.persistence.annotations.Customizer;

@Entity
@Table(name="TLG_GR_ADDRESS")
@Customizer(CoherenceReadCustomizer.class)
public class Address implements Serializable {
	private static final long serialVersionUID = 7161601871474965898L;

	@Id
	@GeneratedValue
	private int id;
	private String city;
	
	private String country;
	private String province;
	private String postalCode;
	private String street; 
	@Version
	private Long version;

	public Address() {
	}

	public Long getVersion() {
		return version;
	}

	public int getId() {
		return this.id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String pCode) {
		this.postalCode = pCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address(" + getId() + ": " + getCity() + ", " + getCountry() + ")";
	}
}
