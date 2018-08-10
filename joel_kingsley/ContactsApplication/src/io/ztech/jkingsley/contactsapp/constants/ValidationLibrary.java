package io.ztech.jkingsley.contactsapp.constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationLibrary {
	
	public static boolean isValidMobileNumber(Long mobileNumber) {
		if (mobileNumber != null) {
			if (mobileNumber != -1) {
				Pattern pattern = Pattern.compile("(91)[0-9]{10}");
				Matcher matcher = pattern.matcher(Long.toString(mobileNumber));
				return matcher.matches();
			}
		}
		return false;
	}

	public static boolean isValidHomeNumber(Long homeNumber) {
		if (homeNumber != null) {
			if (homeNumber != -1) {
				Pattern pattern = Pattern.compile("[0-9]{10}");
				Matcher matcher = pattern.matcher(Long.toString(homeNumber));
				return matcher.matches();
			}
		}
		return false;
	}

	public static boolean isValidOfficeNumber(Long officeNumber) {
		if (officeNumber != null) {
			if (officeNumber != -1) {
				Pattern pattern = Pattern.compile("[0-9]{10}");
				Matcher matcher = pattern.matcher(Long.toString(officeNumber));
				return matcher.matches();
			}
		}
		return false;
	}

	public static boolean isValidEmail(String emailStr) {

		final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);

		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
}
