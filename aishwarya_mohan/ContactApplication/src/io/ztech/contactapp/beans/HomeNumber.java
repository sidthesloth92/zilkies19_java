package io.ztech.contactapp.beans;

public class HomeNumber {
	String areaCode;
	String countryCode;
	String number;
	int home_id;

	void setHomeNumber(String areaCode, String countryCode, String number) {
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

	public int getHome_id() {
		return home_id;
	}

	public void setHome_id(int home_id) {
		this.home_id = home_id;
	}
	
	
}