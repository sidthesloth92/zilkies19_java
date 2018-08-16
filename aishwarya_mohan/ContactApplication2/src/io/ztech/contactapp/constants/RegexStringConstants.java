package io.ztech.contactapp.constants;

public class RegexStringConstants {
	public static String EMAIL_VALIDATION = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
			+ "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	public static String CODE_VALIDATION = "[0-9]{2,4}";
	public static String NUMBER_VALIDATION = "[0-9]{6,10}";
	public static String NO_VALIDATION=".*";
}
