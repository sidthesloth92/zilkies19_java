package io.zilker.contacts.beans;

import java.util.regex.Pattern;

import io.zilker.contacts.constants.*;

public class Validator {

	public static boolean isEmpty(String number) {
		if (number.compareTo("") == 0) {
			System.out.println(ErrorCodes.fieldEmptyErr);
			return true;
		}
		return false;
	}

	public static boolean validateName(String name) {
		if (name == null)
			return false;
		name = name.trim();
		if (name.length() == 0 || name.compareTo("") == 0) {
			System.out.println(ErrorCodes.fieldEmptyErr);
			return false;
		}
		if (name.length() > 20) {
			System.out.println(ErrorCodes.longerFieldErr);
			return false;
		}
		for (int i = 0; i < name.length(); i++) {
			if (!Character.isLetter(name.charAt(i))) {
				System.out.println(ErrorCodes.wrongFormatErr);
				return false;
			}
		}
		return true;
	}

	public static boolean validateExtension(String ext) {
		if (ext == null)
			return false;
		ext.replaceAll(" ", "");
		if (ext.length() > 6) {
			System.out.println(ErrorCodes.longerFieldErr);
			return false;
		}
		return validateNum(ext);
	}

	public static boolean validateCode(String code) {
		if (code == null)
			return false;
		code.replaceAll(" ", "");
		if (code.length() == 0) {
			System.out.println(ErrorCodes.fieldEmptyErr);
			return false;
		}
		if (code.length() > 8) {
			System.out.println(ErrorCodes.longerFieldErr);
			return false;
		}
		if (code.length() > 0 && code.charAt(0) == '+')
			code = code.substring(1);
		return validateNum(code);
	}

	public static boolean validateNum(String number) {
		if (number == null)
			return false;
		if (number.compareTo("") == 0)
			return true;
		number.replaceAll(" ", "");
		if (number.length() > 14) {
			System.out.println(ErrorCodes.longerFieldErr);
			return false;
		}
		for (int i = 0; i < number.length(); i++) {
			if (!Character.isDigit(number.charAt(i))) {
				System.out.println(ErrorCodes.wrongFormatErr);
				return false;
			}
		}
		return true;
	}
	
	public static boolean validateEmail(String emailid) {
		if (emailid == null)
			return false;
		emailid = emailid.trim();
		if (emailid.compareTo("") == 0)
			return true;
		if (emailid.length() > 25) {
			System.out.println(ErrorCodes.longerFieldErr);
			return false;
		}
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		if (!Pattern.matches(emailRegex, emailid)) {
			System.out.println(ErrorCodes.wrongFormatErr);
			return false;
		}
		return true;
	}
	
	public static char validateAnotherOption(String option) {
		if (option == null)
			return 'i';
		option = option.trim();
		if (option.compareTo("") == 0 || option.length() > 1 || option.length() == 0) {
			System.out.println(ErrorCodes.invalidOption);
			return 'i';
		}
		if (option.charAt(0) == 'y' || option.charAt(0) == 'n')
			return option.charAt(0);
		else
			System.out.println(ErrorCodes.invalidOption);
		return 'i';
	}
	
	public static char officePhoneType(String type) {
		if (type == null)
			return 'i';
		type = type.trim();
		if (type.compareTo("") == 0 || type.length() > 1 || type.length() == 0) {
			System.out.println(ErrorCodes.invalidOption);
			return 'i';
		}
		if (type.charAt(0) == 'l' || type.charAt(0) == 'm' || type.charAt(0) == 'n')
			return type.charAt(0);
		System.out.println(ErrorCodes.invalidOption);
		return 'i';
	}
	
}
