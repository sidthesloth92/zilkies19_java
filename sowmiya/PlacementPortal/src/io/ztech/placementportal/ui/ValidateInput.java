package io.ztech.placementportal.ui;

import java.util.regex.Pattern;

public class ValidateInput {

	public static boolean validateDetail(String pattern,String input) {
		boolean matcher = Pattern.matches(pattern, input);
		return matcher;
	}

	public static boolean validateMark(float input) {
		if(input<=100)
			return true;
		return false;
	}

}
