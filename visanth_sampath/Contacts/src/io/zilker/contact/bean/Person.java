package io.zilker.contact.bean;

import java.util.ArrayList;

public class Person {
	private static String firstName;
	private static String lastName;
	private ArrayList<String> mobileNumber;
	private ArrayList<String> countryCode;
	private static ArrayList <String> officeNumber , extension;
	private ArrayList<String> homeNumber;
	private ArrayList<String> homeCountryCode;
	private ArrayList<String> areaCode;
	private ArrayList<String> email = new ArrayList<String>();
	
	//setters for Names
	public void setNames(String fname,String lname) {
		firstName = fname;
		lastName = lname;
	}
	
	public void setMobile(ArrayList<String> mobileNumber , ArrayList<String> countryCode) {
		for(int i=0;i<mobileNumber.size();i++) {
			this.mobileNumber.add(mobileNumber.get(i));
		}
		for(int i=0;i<countryCode.size();i++) {
			this.countryCode.add(countryCode.get(i));
		}
	}
	//setters for mobile number
	public void setOffice(ArrayList <String> officeNumber,ArrayList <String> extension) {
		for(int i=0;i<officeNumber.size();i++) {
			officeNumber.add(officeNumber.get(i));
		}
		for(int i=0;i<extension.size();i++) {
			extension.add(extension.get(i));
		}
	}
	//setters for Home number
		public void setHome(ArrayList<String> homeNumber,ArrayList<String> countryCode , ArrayList<String> areaCode) {
			for(int i=0;i<homeNumber.size();i++) {
				this.homeNumber.add(homeNumber.get(i));
			}
			for(int i=0;i<countryCode.size();i++) {
				this.homeCountryCode.add(countryCode.get(i));
			}
			for(int i=0;i<areaCode.size();i++) {
				this.areaCode.add(areaCode.get(i));
			}
		}
	//setters
	public void setEmail(ArrayList<String> mail) {
		for(int i=0;i<mail.size();i++) {
			
			email.add(mail.get(i));
		}
		
	}
	
	//getters
	public String getFirstName() {
		return firstName; 
	}
	public static String getLastName() {
		return lastName;
	}
	public ArrayList<String> getMobileNumber(){
		return mobileNumber;
	}
	public ArrayList<String> getMobileCountryCode() {
		return countryCode;
	}
	public ArrayList<String> getOfficeNumber() {
		return officeNumber;
	}
	public ArrayList<String> getExtension() {
		return extension;
	}
	//getters
	public ArrayList<String> getHomeNumber() {
		return homeNumber;
	}
	public ArrayList<String> getHomeCountryCode() {
		return countryCode;
	}
	public ArrayList<String> getAreaCode() {
		return areaCode;
	}
	//getters
	public ArrayList<String> getEmail() {
		return email;
	}

}
