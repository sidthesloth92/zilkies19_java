package io.ztech.autorate.servlets;

import java.util.regex.Pattern;

public class Validator {

	public static boolean validate(String regex,String value) {
		return Pattern.matches(regex, value);
	}
	
}
