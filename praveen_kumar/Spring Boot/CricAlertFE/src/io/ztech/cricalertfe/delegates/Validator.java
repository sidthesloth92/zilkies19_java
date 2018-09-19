package io.ztech.cricalertfe.delegates;

import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Validator {
	Logger logger;
	public Validator() {
		logger = Logger.getLogger(Validator.class.getName());
	}
	
	public boolean validateInput(String regex, String input, String error) {
		if (Pattern.matches(regex, input)) {
			return true;
		}
		logger.info(error);
		return false;
	}
}
