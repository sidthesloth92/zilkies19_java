package io.ztech.jkingsley.contactsapp.beans;

public class OfficeNumber extends PhoneNumber {
	public String phoneType = "office";
	
	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
}
