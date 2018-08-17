package com.zilker.ui;

import java.util.regex.Pattern;

public class Validation {
	public static boolean isValidated(String input, String regex) {
		Pattern pat = Pattern.compile(regex);
		if (input != null) {
			return pat.matcher(input).matches();
		}
		return false;
	}
}