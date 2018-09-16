package io.ztech.placementportal.ui;

import java.util.regex.Pattern;

public class ValidateInput {

	public static boolean validateDetail(String pattern, String input) {
		boolean matcher = Pattern.matches(pattern, input);
		return matcher;
	}

	public static boolean validateMark(String input) {
		if (Float.parseFloat(input) <= 100)
			return true;
		return false;
	}

	public static boolean validateCgpa(String input) {
		if (Float.parseFloat(input) <= 10)
			return true;
		else
			return false;
	}

}
