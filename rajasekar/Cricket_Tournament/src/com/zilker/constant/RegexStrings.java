package com.zilker.constant;

public class RegexStrings {
	public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
			+ "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	public static final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
	public static final String NAME_REGEX ="[a-zA-Z]*" ;
	public static final String AGE_REGEX ="^[1-9][0-9]?$";
	public static final String MOBILE_REGEX ="(0/91)?[7-9][0-9]{9}";
}
