package io.ztech.fitnessapplication.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserAccountDetails;
import io.ztech.fitnessapplication.beans.UserPhysicalDetails;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;
import io.ztech.fitnessapplication.delegate.SignUpDelegate;

public class SignUpPageUI {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	ValidationUI checkObj = new ValidationUI();

	public UserAccount signupUI() {
		UserAccountDetails newAccount = createAccount();

		boolean success = new SignUpDelegate().signup(newAccount);
		if (!success) {
			logger.info(DisplayStringConstants.ERROR);
			return null;
		}

		UserAccount currentAccount = new UserAccount();
		currentAccount.setUserName(newAccount.getUserName());
		currentAccount.setPassword(newAccount.getPassword());
		return currentAccount;
	}

	public UserAccountDetails createAccount() {
		String userName, password, firstName, lastName, mailID, phoneNumber;
		logger.info(DisplayStringConstants.SIGN_UP_PAGE);

		UserAccountDetails newAccount = new UserAccountDetails();
		logger.info(DisplayStringConstants.FIRST_NAME);
		firstName = sc.next();
		newAccount.setFirstName(firstName);

		logger.info(DisplayStringConstants.LAST_NAME);
		lastName = sc.next();
		newAccount.setLastName(lastName);

		logger.info(DisplayStringConstants.USER_NAME);
		userName = sc.next();
		if (!checkObj.isValid(userName, ValidationUI.USER_NAME_VALIDATION)) {
			logger.info(DisplayStringConstants.NO_SPACE_WARNING);
			return new UserAccountDetails();
		}
		newAccount.setUserName(userName);

		logger.info(DisplayStringConstants.PASSWORD);
		password = sc.next();
		newAccount.setPassword(password);

		logger.info(DisplayStringConstants.MAIL);
		mailID = sc.next();
		if (!checkObj.isValid(mailID, ValidationUI.EMAIL_VALIDATION)) {
			return createAccount();
		}
		newAccount.setEmail(mailID);

		logger.info(DisplayStringConstants.PHONE);
		phoneNumber = sc.next();
		if (!checkObj.isValid(phoneNumber, ValidationUI.NUMBER_VALIDATION)) {
			return createAccount();
		}
		newAccount.setPhone(phoneNumber);
		newAccount.setRole(2);

		return newAccount;
	}

	public UserPhysicalDetails customiseAccount(UserAccount account) {
		UserPhysicalDetails newAccount = new UserPhysicalDetails();

		newAccount.setUserName(account.getUserName());

		logger.info(DisplayStringConstants.CUST_MSG);

		try {
			logger.info(DisplayStringConstants.HEIGHT);
			newAccount.setHeight(sc.nextFloat());

			logger.info(DisplayStringConstants.WEIGHT);
			newAccount.setWeight(sc.nextFloat());

			logger.info(DisplayStringConstants.AGE);
			newAccount.setAge(sc.nextInt());

			logger.info(DisplayStringConstants.GENDER);
			newAccount.setGender(sc.next());

			logger.info(DisplayStringConstants.LIFESTYLE_MENU);
			newAccount.setActivty(sc.nextInt());

		} catch (InputMismatchException e) {
			logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
			return customiseAccount(account);
		}

		return newAccount;
	}

}
