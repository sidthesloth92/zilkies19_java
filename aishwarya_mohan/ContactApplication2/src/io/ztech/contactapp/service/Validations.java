package io.ztech.contactapp.service;

import java.util.regex.Pattern;

import io.ztech.contactapp.constants.RegexStringConstants;

public class Validations {

	static String getPattern(int inputType) {
		switch (inputType) {
		case 1: // mobile/telephone number
			return RegexStringConstants.NUMBER_VALIDATION;
		case 2: // country code,area code
			return RegexStringConstants.CODE_VALIDATION;
		case 3: // email
			return RegexStringConstants.EMAIL_VALIDATION;
		default:
			return RegexStringConstants.NO_VALIDATION;
		}
	}

	public static boolean isValid(String input, int inputType) {
		return Pattern.matches(getPattern(inputType), input);
	}

}
