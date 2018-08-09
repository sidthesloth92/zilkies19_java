package io.ztech.contact_app.bean;

import java.util.ArrayList;

public class ContactDetail {
	private String firstName, lastName;
	ArrayList<String> homeNumber = new ArrayList<>();
	ArrayList<String> mobileNumber = new ArrayList<>();
	ArrayList<String> officeNumber = new ArrayList<>();
	ArrayList<String> email = new ArrayList<>();

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public ArrayList<String> getMobileNumber() {
		return mobileNumber;
	}

	public ArrayList<String> getHomeNumber() {
		return homeNumber;
	}

	public ArrayList<String> getOfficeNumber() {
		return officeNumber;
	}

	public ArrayList<String> getEmail() {
		return email;
	}

//setters
	public void setFirstName(String name) {
		this.firstName = name;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public void setMobileNumber(ArrayList<String> number) {
		this.mobileNumber = number;
	}

	public void setHomeNumber(ArrayList<String> number) {
		// TODO Auto-generated method stub
		this.homeNumber = number;
	}

	public void setOfficeNumber(ArrayList<String> number) {
		this.officeNumber = number;
	}

	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}

}
