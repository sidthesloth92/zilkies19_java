package io.ztech.contactapp.beans;

import java.util.InputMismatchException;

public class HomeNumber {
	String areaCode;
	String countryCode;
	String number;

	public void setHomeNumber(String areaCode, String countryCode, String number) throws InputMismatchException {
		this.areaCode = areaCode;
		this.countryCode = countryCode;
		this.number = number;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getNumber() {
		return number;
	}

}