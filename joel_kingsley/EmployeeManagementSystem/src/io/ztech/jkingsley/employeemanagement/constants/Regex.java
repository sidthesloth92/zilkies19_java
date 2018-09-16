package io.ztech.jkingsley.employeemanagement.constants;

public class Regex {
	public static final String NAME_REGEX = "^[A-Za-z]+";
	public static final String PASSWORD_REGEX = "[0-9A-Za-z]{1,64}";
	public static final String PAN_REGEX = "^[0-9]{12}$";
	public static final String AADHAR_REGEX = "^[0-9]{12}$";
	public static final String UAN_REGEX = "^[0-9]{12}$";
	public static final String ADDRESS_REGEX = "[0-9A-Za-z\\s\\,\\.] {1,100}";
	public static final String PHONE_REGEX = "^[0-9]{10}$";
	public static final String MAIL_REGEX = "^[A-Za-z0-9]+@[a-z]+\\.[a-z]{2,6}$";
	public static final String INTEGER_REGEX = "^[0-9]+$";
}
