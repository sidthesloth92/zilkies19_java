package io.zilker.contact.bean;

public class PersonVariables {
	private static String firstName;
	private static String lastName;
	
	//setters
	public static void setNames(String fname,String lname) {
		firstName = fname;
		lastName = lname;
	}
	
	//getters
	public static String getFirstName() {
		return firstName; 
	}
	public static String getLastName() {
		return lastName;
	}

}
