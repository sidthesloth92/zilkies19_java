package io.ztech.contactapp.beans;

import java.util.ArrayList;

public class PhoneNumber {
	ArrayList<MobileNumber> mobileNumber;
	ArrayList<OfficeNumber> officeNumber;
	ArrayList<HomeNumber> homeNumber;

	PhoneNumber() {
		this.mobileNumber = new ArrayList<MobileNumber>();
		this.officeNumber = new ArrayList<OfficeNumber>();
		this.homeNumber = new ArrayList<HomeNumber>();
	}

	public ArrayList<MobileNumber> getMobileNumber() {
		return mobileNumber;
	}

	public ArrayList<OfficeNumber> getOfficeNumber() {
		return officeNumber;
	}

	public ArrayList<HomeNumber> getHomeNumber() {
		return homeNumber;
	}

}