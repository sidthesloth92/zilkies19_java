package io.zilker.contact.bean;

public class MobileVariable {
	private static String mobileNumber,countryCode;
	
	//setters
	public void setMobile(String mnumber,String ccode) {
		mobileNumber = mnumber;
		countryCode = ccode;
	}
	
	//gettes
	public String getMobileNumber(){
		return mobileNumber;
	}
	public String getMobileCountryCode() {
		return countryCode;
	}

}
