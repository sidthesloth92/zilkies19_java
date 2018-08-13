package io.ztech.contactapp.beans;

import java.util.ArrayList;

//=========================================================================================================================================
// CONTACT CLASS - STORES ALL CONTACT DETAILS
// =========================================================================================================================================
public class Contact {
	int cId;
	String firstName;
	String lastName;
	ArrayList<Number> phoneNumbers = new ArrayList<Number>();
	ArrayList<EmailId> emailIds = new ArrayList<EmailId>();

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
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

	public ArrayList<Number> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(ArrayList<Number> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public ArrayList<EmailId> getEmailIds() {
		return emailIds;
	}

	public void setEmailIds(ArrayList<EmailId> emailIds) {
		this.emailIds = emailIds;
	}

}
