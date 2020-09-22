package edu.dizruptor.model;
import java.io.Serializable;
import java.util.Objects;

// java bean for Address
public class Address implements Serializable {

	private String id;
	private String type;
	private String address;
	private String city;
	private String state;
	private String postalCode;
	private String country;

	private String combinedAddress;

	public String getId() { return id; }

	public void setId(String id) { this.id = id; }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getCombinedAddress() { return combinedAddress; }

	public void setCombinedAddress(String combinedAddress) { this.combinedAddress = combinedAddress; }

	public void generateCombinedAddress() {
		this.combinedAddress = type + ": " + address + ", " + city + ", " + state + ", " + postalCode + ", " + country;
	}
	
	public Address(String type, String address, String city, String state, String postalCode,
			String country) {
		super();
		this.type = type;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.combinedAddress = type + ": " + address + ", " + city + ", " + state + ", " + postalCode + ", " + country;
	}

	// no arg constructor required for bean
	public Address() {
		this(null, null, null, null, null, null);
	}

}
