package io.ztech.fitnessapplication.ui;

import java.util.logging.Logger;
import java.util.regex.Pattern;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;

public class ValidationUI {
	public static String USER_NAME_VALIDATION = "\\S+";
	public static String NAME_VALIDATION = "\\S+";
	public static String EMAIL_VALIDATION = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
			+ "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	public static String NUMBER_VALIDATION = "[0-9]{10}";
	public static String NO_VALIDATION = ".*";

	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());

	public boolean isValid(String input, String pattern) {
		if (Pattern.matches(pattern, input)) {
			return true;
		} else {
			logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
			return false;
		}
	}
}
