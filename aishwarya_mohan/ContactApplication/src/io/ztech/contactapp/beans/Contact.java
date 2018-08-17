package io.ztech.contactapp.beans;

import java.util.ArrayList;

public class Contact {
	public int contact_id;
	private String firstName;
	private String lastName;
	private PhoneNumber phone;
	private ArrayList<String> emailAddress;

	public Contact(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = new PhoneNumber();
		this.emailAddress = new ArrayList<String>();
	}

	public void setContact_id(int con_id) {
		this.contact_id = con_id;
	}

	public int getContact_id() {
		return contact_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public PhoneNumber getPhone() {
		return phone;
	}

	public ArrayList<String> getEmailAddress() {
		return emailAddress;
	}

	public void addMobileNumber(String countryCode, String mobileNumber) {
		MobileNumber m = new MobileNumber();
		m.setMobileNumber(countryCode, mobileNumber);
		this.phone.mobileNumber.add(m);
	}

	public void addOfficeNumber(String ext, String officeNumber) {
		OfficeNumber o = new OfficeNumber();
		o.setOfficeNumber(ext, officeNumber);
		this.phone.officeNumber.add(o);
	}

	public void addHomeNumber(String countryCode, String areaCode, String homeNumber) {
		HomeNumber h = new HomeNumber();
		h.setHomeNumber(countryCode, areaCode, homeNumber);
		this.phone.homeNumber.add(h);
	}

	public void setEmailAddress(String mailID) {
		this.emailAddress.add(mailID);
	}

	

}
