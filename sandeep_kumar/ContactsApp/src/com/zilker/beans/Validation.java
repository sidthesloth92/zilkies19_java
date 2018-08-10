package com.zilker.beans;

public class Validation {
	public boolean validateFirstName(String dataTovalidate) {
		if (!dataTovalidate.matches("[a-zA-Z0-9]+")) {
			System.out.println("Firstname can only have alphabets! Please re-enter!");
		}
		return dataTovalidate.matches("[a-zA-Z0-9]+");
	}

	public boolean validateLastName(String dataTovalidate) {
		if (!dataTovalidate.matches("[a-zA-Z]+")) {
			System.out.println("LastName can only have alphabets! Please re-enter!");
		}
		return dataTovalidate.matches("[a-zA-Z0-9]+");
	}

	public boolean validateOfficeNumber(String dataTovalidate) {
		if (!dataTovalidate.matches("^\\+[0-9]{2,4}-[0-9]{3,4}-[0-9]{4,7}$")) {
			System.out.println("Invalid Office No.! Please re-enter!");
		}
		return dataTovalidate.matches("^\\+[0-9]{2,4}-[0-9]{3,4}-[0-9]{4,7}$");
		//return true;
	}

	public boolean validateHomeNumber(String dataTovalidate) {
		if (!dataTovalidate.matches("^\\+[0-9]{2,4}-[0-9]{3,4}-[0-9]{4,7}$")) {
			System.out.println("Invalid Home No.! Please re-enter!");
		}
		return dataTovalidate.matches("^\\+[0-9]{2,4}-[0-9]{3,4}-[0-9]{4,7}$");
	}

	public boolean validateMobileNumber(String dataTovalidate) {
		if (!dataTovalidate.matches("^\\+[0-9]{2,4}-[0-9]{10}")) {
			System.out.println("Invalid Mobile No.! Please re-enter!");
		}
		return dataTovalidate.matches("^\\+[0-9]{2,4}-[0-9]{10}");
	}

	public boolean validateEmail(String dataTovalidate) {
		if (!dataTovalidate.matches("^[a-zA-Z0-9.-_]+@[a-zA-Z]+[.][a-zA-Z]{2,10}([.][a-zA-Z]{2,10})*")) {
			System.out.println("Invalid E-mail! Please re-enter!");
		}
		return dataTovalidate.matches("^[a-zA-Z0-9.-_]+@[a-zA-Z]+[.][a-zA-Z]{2,10}([.][a-zA-Z]{2,10})*");
	}
}
