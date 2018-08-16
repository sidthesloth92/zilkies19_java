package io.ztech.carstats.delegate;

import java.util.regex.Pattern;

public class Validator {

	public static boolean validateUsername(String username) {
		return Pattern.matches("^[a-z0-9_-]{3,15}$", username);
	}

	public static boolean validatePassword(String password) {
		return Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})", password);
	}
}
