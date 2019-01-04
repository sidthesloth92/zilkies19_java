package com.employeeapp.delegate;

import java.util.regex.Pattern;

import com.employeeapp.constants.RegexConstants;

public class Validator {

	public static boolean validateName(String name) {
		boolean matcher = Pattern.matches(RegexConstants.NAME_REGEX, name);
		return matcher;
	}

	public static boolean validateEmail(String email) {
		boolean matcher = Pattern.matches(RegexConstants.EMAIL_REGEX, email);
		return matcher;
	}

	public static boolean validPhoneNumber(long phoneNumber) {
		boolean matcher = Pattern.matches(RegexConstants.PHONE_NUMBER_REGEX, String.valueOf(phoneNumber));
		return matcher;
	}

	public static boolean validSalary(long salary) {
		boolean matcher = Pattern.matches(RegexConstants.SALARY_REGEX, String.valueOf(salary));
		return matcher;
	}

}
