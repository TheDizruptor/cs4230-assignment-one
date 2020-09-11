package edu.dizruptor.model;
import java.io.Serializable;

// java bean for Address
public class Address implements Serializable {

	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postalCode;
	private String country;

	public String getAddressLine1() {
		return address1;
	}

	public void setAddressLine1(String address1) {
		this.address1 = address1;
	}

	public String getAddressLine2() {
		return address2;
	}

	public void setAddressLine2(String address2) {
		this.address2 = address2;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	public Address(String address1, String address2, String city, String state, String postalCode,
			String country) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}
	
	public Address(String address1, String city, String state, String postalCode, String country) {
		this(address1, null, city, state, postalCode, country);
	}

	// no arg constructor required for bean
	public Address() {
		this(null, null, null, null, null, null);
	}
	
}
