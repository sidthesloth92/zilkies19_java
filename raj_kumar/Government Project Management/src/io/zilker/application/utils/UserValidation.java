package io.zilker.application.utils;

public class UserValidation {
	public static boolean isValid(String value, String regex) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(value);
        return m.matches();
	}
}
