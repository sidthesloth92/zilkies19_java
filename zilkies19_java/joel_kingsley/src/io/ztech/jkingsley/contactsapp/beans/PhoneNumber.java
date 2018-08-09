package io.ztech.jkingsley.contactsapp.beans;

public class PhoneNumber {
	Long number;
	String phoneType;

	public Long getNumber() {
		return number;
	}

	public void putNumber(Long number) {
		this.number = number;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	
	public String toString() {
		return number + " (" + phoneType + ")";
	}

}