package io.ztech.jkingsley.contactsapp.beans;

public class User {

	Long id;
	String firstName;
	String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getId() + " : " + this.getFirstName() + " " + this.getLastName();
	}
	
	

}
