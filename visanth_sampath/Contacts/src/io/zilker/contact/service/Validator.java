package io.zilker.contact.service;

import java.util.regex.Pattern;

import io.zilker.contact.constants.Applicationconstants;

public class Validator {
	
	public static boolean validateEmail(String email) {
		boolean checkMail;
		String emailRegex = Applicationconstants.EMAIL_REGEX;
		checkMail = false;
		Pattern pat = Pattern.compile(emailRegex);
		checkMail = pat.matcher(email).matches();
		return checkMail;
	}
	//Validate Mobile Numbers
	public static boolean validateMobile(String mobileNumber) {
		if(mobileNumber.length() != 10 ) {
			return false;
		}
		Pattern p = Pattern.compile(Applicationconstants.MOBILE_REGEX);
		
		return p.matcher(mobileNumber).matches();
	}

}
