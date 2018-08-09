package io.zilker.contacts;

import io.zilker.contacts.constants.ErrorCodes;

public class AppValidator {

	public static boolean validateOption(String option) {
		if (option.length() > 1 || option == null || option.compareTo("") == 0)
			return false;
		if (option.charAt(0) >= '1' && option.charAt(0) <= '6')
			return true;
		return false;
	}

	public static boolean validateEditOption(String option) {
		if (option == null)
			return false;
		if (option.length() == 0)
			return true;
		if (option.length() > 1 || option.charAt(0) < '1' || option.charAt(0) > '7') {
			System.out.println(ErrorCodes.invalidOption);
			return false;
		}
		if (option.charAt(0) >= '1' && option.charAt(0) <= '7')
			return true;
		return false;
	}

	public static boolean validateEditOptionUtil(String option) {
		if (option == null)
			return false;
		if (option.length() == 0)
			return true;
		if (option.length() > 1 || option.charAt(0) < '1' || option.charAt(0) > '3') {
			System.out.println(ErrorCodes.invalidOption);
			return false;
		}
		if (option.charAt(0) >= '1' && option.charAt(0) <= '3')
			return true;
		return false;
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

}
