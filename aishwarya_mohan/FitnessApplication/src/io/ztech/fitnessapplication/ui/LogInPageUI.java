package io.ztech.fitnessapplication.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;

public class LogInPageUI {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	ValidationUI checkObj = new ValidationUI();

	public UserAccount loginUI() {
		String userName, password;
		UserAccount userAccount = new UserAccount();

		logger.info(DisplayStringConstants.LOGIN_PAGE);

		logger.info(DisplayStringConstants.USER_NAME);
		userName = sc.next();
		if (!checkObj.isValid(userName, ValidationUI.USER_NAME_VALIDATION)) {
			logger.info(DisplayStringConstants.NO_SPACE_WARNING);
			return loginUI();
		}

		logger.info(DisplayStringConstants.PASSWORD);
		password = sc.next();

		userAccount.setUserName(userName);
		userAccount.setPassword(password);
		return userAccount;
	}
}
