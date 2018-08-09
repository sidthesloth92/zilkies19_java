package io.ztech.contacts.beans;

import java.util.regex.Pattern;

//base phone number class 
public class NumberDetails {
	int cId;
	String number;
	String countryCode;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	//validates the entered number
	public boolean isValidatedNumber(String number) {
	 	String numRegex = "\\d+";
		Pattern pat = Pattern.compile(numRegex);
		if (number != null) {
			return pat.matcher(number).matches();
		}
		return false;

	}

}
