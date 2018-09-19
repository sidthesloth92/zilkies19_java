package io.ztech.cricalertbe.controllers;

import io.ztech.cricalertfe.beans.User;
import io.ztech.cricalertfe.constants.Regex;
import io.ztech.cricalertfe.constants.UserMessages;
import io.ztech.cricalertfe.delegates.UserDelegate;

public class UserController {
	UserDelegate userDelegate;
	Validator validator;

	public UserController() {
		userDelegate = new UserDelegate();
		validator = new Validator();
	}

/*	public boolean checkUser(User user) {
		return userDelegate.checkUser(user);
	}
*/
	public User verifyUser(User user) {
		return userDelegate.verifyUser(user);
	}

	public boolean createUser(User newUser) {
		if (validator.validateInput(Regex.nameRegex, newUser.getName(), UserMessages.INVALID_FIRST_NAME)
				&& validator.validateInput(Regex.emailRegex, newUser.getEmail(), UserMessages.INVALID_EMAIL)) {
			return userDelegate.createUser(newUser);
		} else {
			return false;
		}
	}
}
