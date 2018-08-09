package io.zilker.contact.bean;

public class HomeVariables {
	private static String homeNumber , countryCode , areaCode;
	
	//setters
	public void setHome(String hnumber,String ccode ,String acode) {
		homeNumber = hnumber;
		countryCode = ccode;
		areaCode = acode;
	}
	//getters
	public String getHomeNumber() {
		return homeNumber;
	}
	public String getHomeCountryCode() {
		return countryCode;
	}
	public String getAreaCode() {
		return areaCode;
	}

}
