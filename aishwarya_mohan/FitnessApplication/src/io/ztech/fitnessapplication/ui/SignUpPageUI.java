package io.ztech.fitnessapplication.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserProfile;
import io.ztech.fitnessapplication.beans.UserStats;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;
import io.ztech.fitnessapplication.delegate.SignUpDelegate;

public class SignUpPageUI {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	ValidationUI checkObj = new ValidationUI();

	public UserAccount signupUI() {
		UserAccount newAccount = new UserAccount();
		UserProfile newProfile = createProfile();

		boolean success = new SignUpDelegate().signup(newProfile);
		if (!success) {
			logger.info(DisplayStringConstants.ERROR);
			return null;
		}

		newAccount.setUserName(newProfile.getUserName());
		newAccount.setFirstName(newProfile.getFirstName());
		newAccount.setPassword(newProfile.getPassword());

		return newAccount;
	}

	public UserProfile createProfile() {
		String userName, password, firstName, lastName, mailID, phoneNumber;
		logger.info(DisplayStringConstants.SIGN_UP_PAGE);

		UserProfile newProfile = new UserProfile();

		logger.info(DisplayStringConstants.FIRST_NAME);
		firstName = sc.next();
		newProfile.setFirstName(firstName);

		logger.info(DisplayStringConstants.LAST_NAME);
		lastName = sc.next();
		newProfile.setLastName(lastName);

		logger.info(DisplayStringConstants.USER_NAME);
		userName = sc.next();
		if (!checkObj.isValid(userName, ValidationUI.USER_NAME_VALIDATION)) {
			logger.info(DisplayStringConstants.NO_SPACE_WARNING);
			return createProfile();
		}
		newProfile.setUserName(userName);

		logger.info(DisplayStringConstants.PASSWORD);
		password = sc.next();
		newProfile.setPassword(password);

		logger.info(DisplayStringConstants.MAIL);
		mailID = sc.next();
		if (!checkObj.isValid(mailID, ValidationUI.EMAIL_VALIDATION)) {
			return createProfile();
		}
		newProfile.setEmailID(mailID);

		logger.info(DisplayStringConstants.PHONE);
		phoneNumber = sc.next();
		if (!checkObj.isValid(phoneNumber, ValidationUI.NUMBER_VALIDATION)) {
			return createProfile();
		}
		newProfile.setPhoneNo(phoneNumber);

		return newProfile;
	}

	public UserStats customiseAccount(UserAccount account) {
		UserStats newAccStats = new UserStats();

		logger.info(DisplayStringConstants.CUST_MSG);

		newAccStats.setRegID(account.getRegID());

		logger.info(DisplayStringConstants.HEIGHT);
		newAccStats.setHeight(sc.nextFloat());

		logger.info(DisplayStringConstants.WEIGHT);
		newAccStats.setWeight(sc.nextFloat());

		logger.info(DisplayStringConstants.AGE);
		newAccStats.setAge(sc.nextInt());

		logger.info(DisplayStringConstants.GENDER);
		newAccStats.setGender(sc.next().charAt(0));

		logger.info(DisplayStringConstants.LIFESTYLE_MENU);
		newAccStats.setActivityLevel(sc.nextInt());

		return newAccStats;
	}

}
