package io.zilker.application.constants;

public class ValidationConstants {
	public static final String NAME_VALIDATION = "[a-zA-Z]+";
	public static final String DATE_VALIDATION = "\\\\d{2}-\\\\d{2}-\\\\d{4}";
	public static final String EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\\\[[0-9]{1,3}\\\\.[0-9]{1,3}\\\\.[0-9]{1,3}\\\\.[0-9]{1,3}\\\\])|(([a-zA-Z\\\\-0-9]+\\\\.)+[a-zA-Z]{2,}))$";
	public static final String NO_OF_CLIENT = "[0-9]+";
}
