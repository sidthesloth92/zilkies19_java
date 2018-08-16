package io.ztech.contactapp.beans;

//=========================================================================================================================================
//NUMBER CLASS - BASE CLASS FOR ALL TYPES OF PHONE NUMBERS
//=========================================================================================================================================
public class Number {
	int cId;
	String countryCode;
	String phoneNumber;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
