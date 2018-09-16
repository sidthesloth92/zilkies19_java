package io.ztech.fitnessapplication.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserPhysicalDetails;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;
import io.ztech.fitnessapplication.service.AccountService;
import io.ztech.fitnessapplication.service.CalculateService;
import io.ztech.fitnessapplication.service.UserPhysicalDetailsService;

public class StartPageUI {
	private Logger logger;
	private Scanner sc;

	public void startUpPage() {
		// ask if login/signup/continue
		logger.info(DisplayStringConstants.WELCOME_MESSAGE);

		UserAccount currentAccount = new UserAccount();
		boolean accountExists = true;
		int userType = 0;

		try {
			logger.info(DisplayStringConstants.STARTUP_PAGE_MENU);
			userType = sc.nextInt();
		} catch (InputMismatchException e) {
			logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
		} catch (NumberFormatException e) {
			logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
		}

		if (userType == 1) {// user login
			currentAccount = new LogInPageUI().loginUI();
			accountExists = new AccountService().loginUser(currentAccount);

			if (accountExists) {
				logger.info(DisplayStringConstants.WELCOME + currentAccount.getUserName());
			} else {
				logger.info(DisplayStringConstants.NO_ACC);
			}

		} else if (userType == 2) {// sign up
			currentAccount = new SignUpPageUI().signupUI();
			if (currentAccount != null) {
				UserPhysicalDetails accountProfile = new SignUpPageUI().customiseAccount(currentAccount);
				accountProfile.setBmr(CalculateService.calculateBMR(accountProfile));
				accountProfile.setBmi(CalculateService.calculateBMI(accountProfile));
				new EditUI().setTarget(accountProfile);

				new UserPhysicalDetailsService().addphysicalProfile(accountProfile);

				logger.info(DisplayStringConstants.WELCOME + currentAccount.getUserName());
			} else {
				logger.info(DisplayStringConstants.NO_ACC);
			}

		} else {// continue without signing in
			currentAccount = null;
			logger.info(DisplayStringConstants.ANON);
		}
		// display menu
		new MenuUI().showMenu(currentAccount);

	}
}
