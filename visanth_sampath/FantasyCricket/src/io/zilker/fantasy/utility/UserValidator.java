package io.zilker.fantasy.utility;

import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.constants.DisplayConstants;
import io.zilker.fantasy.dao.AdminDAO;
import io.zilker.fantasy.ui.AdminUI;

public class UserValidator {
	AdminUI adminUI = new AdminUI();
	User newUser = new User();
	GeneralValidators newGeneralValidators = new GeneralValidators();
	AdminDAO newCRUDOperations = new AdminDAO();

	// gets the values and passes to the validator
	public User enterLoginDetails() {

		try {
			adminUI.displayAlert(DisplayConstants.ENTER_USER_NAME);
			String userName = adminUI.getStringInputs();
			adminUI.displayAlert(DisplayConstants.ENTER_PASSWORD);
			String password = adminUI.getStringInputs();
			newUser = newCRUDOperations.validateLogin(userName, password);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return newUser;
	}

	// gets the values and passes to the signUp validator
	public boolean enterSignUpDetails() {
		boolean isValid = false, isEmail = false;
		String email = null;
		try {
			adminUI.displayAlert(DisplayConstants.ENTER_USER_NAME);
			String userName = adminUI.getStringInputs();
			while (!isEmail) {
				adminUI.displayAlert(DisplayConstants.ENTER_EMAIL);
				email = adminUI.getStringInputs();
				isEmail = newGeneralValidators.checkVaildEmail(email);
				if (!isEmail) {
					adminUI.displayAlert(DisplayConstants.MAIL_INVALID);
				}
			}
			adminUI.displayAlert(DisplayConstants.ENTER_PASSWORD);
			String password = adminUI.getStringInputs();
			User newUser = new User();
			newUser.setUser(userName, email, password, 1);
			isValid = newCRUDOperations.addUser(newUser);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return isValid;
	}

}
