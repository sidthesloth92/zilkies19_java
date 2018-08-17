package io.ztech.fitnessapplication.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserStats;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;
import io.ztech.fitnessapplication.service.AccountService;
import io.ztech.fitnessapplication.service.CalculateService;
import io.ztech.fitnessapplication.service.StatsService;

public class StartPageUI {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	public void startUpPage() {
		// ask if login/signup/continue
		logger.info(DisplayStringConstants.WELCOME_MESSAGE);

		int regID = 0;
		UserAccount userAcc = null;

		try {
			logger.info(DisplayStringConstants.STARTUP_PAGE_MENU);
			int userType = sc.nextInt();

			if (userType == 1) {// user login
				userAcc = new LogInPageUI().loginUI();
				regID = new AccountService().getLoggedInUserAccount(userAcc);

				if (regID != 0) {
					userAcc.setRegID(regID);
					new AccountService().setLogin(userAcc);
					logger.info(DisplayStringConstants.WELCOME + userAcc.getUserName());
				} else {
					logger.info(DisplayStringConstants.NO_ACC);
				}

			} else if (userType == 2) {// sign up
				userAcc = new SignUpPageUI().signupUI();
				regID = new AccountService().getLoggedInUserAccount(userAcc);
				if (regID != 0) {
					userAcc.setRegID(regID);
					new AccountService().setLogin(userAcc);
					UserStats accStats = new SignUpPageUI().customiseAccount(userAcc);
					accStats.setBmr(CalculateService.calculateBMR(accStats));
					accStats.setBmi(CalculateService.calculateBMI(accStats));
					new StatsService().addStats(accStats);
					logger.info(DisplayStringConstants.WELCOME + userAcc.getUserName());

				} else {
					logger.info(DisplayStringConstants.NO_ACC);
				}

			} else {// continue without signing in
				userAcc = null;
				logger.info(DisplayStringConstants.ANON);
			}
			// display menu
			new MenuUI().showMenu(userAcc);

		} catch (InputMismatchException e) {
			logger.info(e + "");
		} finally {
			new AccountService().resetLogin(userAcc);
			sc.close();
		}

	}
}
