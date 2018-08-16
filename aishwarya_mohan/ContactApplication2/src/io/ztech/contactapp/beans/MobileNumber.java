package io.ztech.contactapp.beans;

public class MobileNumber {
	String countryCode;
	String number;
	int mobile_id;

	public void setMobileNumber(String countryCode, String number) {
		this.countryCode = countryCode;
		this.number = number;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getNumber() {
		return number;
	}

	public void setMobile_id(int mobile_id) {
		this.mobile_id = mobile_id;
	}

	public int getMobile_id() {
		return mobile_id;
	}

}
