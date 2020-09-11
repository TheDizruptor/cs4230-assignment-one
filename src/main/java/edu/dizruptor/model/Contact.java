package edu.dizruptor.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// java bean for Contact
public class Contact implements Serializable {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private List<Address> addresses;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Contact(String firstName, String lastName, String phoneNumber, List<Address> addresses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.addresses = addresses;
	}

	// no arg constructor required for bean
	public Contact() {
		this(null, null, null, null);
	}
	
	
	
}
