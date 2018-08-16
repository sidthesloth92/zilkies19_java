package com.zilker.service;

import java.util.regex.Pattern;

public class Validation {

	static boolean validateFname(String fname) {
		for (int i = 0; i < fname.length(); i++) {
			if (!Character.isAlphabetic(fname.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	static boolean validateLname(String lname) {
		for (int i = 0; i < lname.length(); i++) {
			if (!Character.isAlphabetic(lname.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean validatemobile(String cc, String mobile) {
		if (cc.length() >= 1 && cc.length() <= 5) {
			for (int i = 0; i < cc.length(); i++) {
				if (i == 0) {
					if (cc.charAt(i) != '+' || !Character.isDigit(cc.charAt(i))) {
						return false;
					}
				} else {
					if (!Character.isDigit(cc.charAt(i))) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		if (mobile.length() >= 1 && mobile.length() <= 16) {
			for (int i = 0; i < mobile.length(); i++) {
				if (!Character.isDigit(mobile.charAt(i))) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public static boolean validatehome(String area, String home) {
		if (area.length() >= 3 && area.length() <= 4) {
			for (int i = 0; i < area.length(); i++) {
				if (!Character.isDigit(area.charAt(i))) {
					return false;
				}
			}
		} else {
			return false;
		}
		if (home.length() == 6) {
			for (int i = 0; i < home.length(); i++) {
				if (!Character.isDigit(home.charAt(i))) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public static boolean validateemail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public static boolean validateoffice(String exten, String office) {
		if (exten.length() >= 2 && exten.length() <= 4) {
			for (int i = 0; i < exten.length(); i++) {
				if (!Character.isDigit(exten.charAt(i))) {
					return false;
				}
			}
		} else {
			return false;
		}
		if (office.length() == 6) {
			for (int i = 0; i < office.length(); i++) {
				if (!Character.isDigit(office.charAt(i))) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
}
