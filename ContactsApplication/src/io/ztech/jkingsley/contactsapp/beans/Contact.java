package io.ztech.jkingsley.contactsapp.beans;

import java.util.ArrayList;

public class Contact {

	public User user;
	public ArrayList<Email> emails;
	public ArrayList<OfficeNumber> officeNumbers;
	public ArrayList<MobileNumber> mobileNumbers;
	public ArrayList<HomeNumber> homeNumbers;

	public Contact() {
		user = new User();
		officeNumbers = new ArrayList<>();
		homeNumbers = new ArrayList<>();
		mobileNumbers = new ArrayList<>();
		emails = new ArrayList<Email>();
	}

	public ArrayList<Email> getEmails() {
		return emails;
	}

	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}

	public ArrayList<OfficeNumber> getOfficeNumbers() {
		return officeNumbers;
	}

	public void setOfficeNumbers(ArrayList<OfficeNumber> officeNumbers) {
		this.officeNumbers = officeNumbers;
	}

	public ArrayList<MobileNumber> getMobileNumbers() {
		return mobileNumbers;
	}

	public void setMobileNumbers(ArrayList<MobileNumber> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}

	public ArrayList<HomeNumber> getHomeNumbers() {
		return homeNumbers;
	}

	public void setHomeNumbers(ArrayList<HomeNumber> homeNumbers) {
		this.homeNumbers = homeNumbers;
	}

}
