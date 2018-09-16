package io.zilker.appstore.controller;

import java.util.regex.*;

import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.constants.RegularExpressions;
import io.zilker.appstore.beans.*;

public class ControllerValidator {

	private static ControllerValidator controllerValidator = new ControllerValidator();;
	
	public boolean isValidated(String text, String regex) {
		if(text == null)
			return false;
		return Pattern.matches(regex, text);
	}
	
	public void checkAppID(Applications app) throws Exception {
		if(!controllerValidator.isValidated(String.valueOf(app.getAppID()), RegularExpressions.APP_ID))
			throw new Exception(Errors.INVALID_APP_ID);
	}
	
	public void checkID(int id) throws Exception {
		if(!controllerValidator.isValidated(String.valueOf(id), RegularExpressions.ID))
			throw new Exception(Errors.INVALID_ID);
	}
	
	public void checkUserName(String name) throws Exception {
		if(!controllerValidator.isValidated(name, RegularExpressions.USER_NAME))
			throw new Exception(Errors.INVALID_USERNAME_PASSWORD);
	}
	
	public void checkPassword(String password) throws Exception {
		if(!controllerValidator.isValidated(password, RegularExpressions.PASSWORD))
			throw new Exception(Errors.INVALID_USERNAME_PASSWORD);
	}
	
}
