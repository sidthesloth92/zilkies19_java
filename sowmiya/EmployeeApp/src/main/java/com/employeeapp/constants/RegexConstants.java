package com.employeeapp.constants;

public class RegexConstants {

	public static final String NAME_REGEX = "[a-zA-Z]+";
	public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String PHONE_NUMBER_REGEX = "[0-9]{10}";
	public static final String SALARY_REGEX = "[0-9]{4,20}";
}
