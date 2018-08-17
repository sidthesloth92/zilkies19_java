package io.ztech.jkingsley.contactsapp.beans;

public class HomeNumber extends PhoneNumber {
	public String phoneType = "home";
	
	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
}
