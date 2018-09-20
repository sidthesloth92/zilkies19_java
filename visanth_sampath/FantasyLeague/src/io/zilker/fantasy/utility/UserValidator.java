package io.zilker.fantasy.utility;

import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.constants.DisplayConstants;
import io.zilker.fantasy.dao.AdminDAO;

public class UserValidator {
	
	User newUser = new User();
	GeneralValidators newGeneralValidators = new GeneralValidators();
	AdminDAO newCRUDOperations = new AdminDAO();

	// gets the values and passes to the validator
	public User enterLoginDetails(String userName, String password) {
		try {
			newUser = newCRUDOperations.validateLogin(userName, password);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return newUser;
	}

	// gets the values and passes to the signUp validator
	public boolean enterSignUpDetails(String userName , String email , String password) {
		boolean isValid = false;
		try {
			User newUser = new User();
			newUser.setUser(userName, email, password, 1);
			isValid = newCRUDOperations.addUser(newUser);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return isValid;
	}

}
