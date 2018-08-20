package io.zilker.appstore.ui;

import java.util.regex.*;

public class UIValidator {
	
	public boolean isValidated(String text, String regex, String errorMessage) {
		if(Pattern.matches(regex, text))
			return true;
		System.out.println(errorMessage);
		return false;
	}
	
}
