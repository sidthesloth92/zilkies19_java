package io.ztech.fitnessapplication.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserStats;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;
import io.ztech.fitnessapplication.service.AccountService;
import io.ztech.fitnessapplication.service.StatsService;

public class MenuUI {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	private static final DisplayUI dispObj = new DisplayUI();
	private static final TrackerUI trackObj = new TrackerUI();

	public static void displayLoggedInUserMenu(UserAccount account) {
		int choice;
		UserStats stats = new StatsService().getStats(account);
		stats.setRegID(account.getRegID());
		try {
			do {
				logger.info(DisplayStringConstants.LOGGED_IN_USER_MENU);
				choice = sc.nextInt();

				switch (choice) {
				case 1:// view stats
					dispObj.displayStats(account);
					break;

				case 2:// view account details
					dispObj.displayProfile(account);

					break;

				case 3:// food tracker
					//trackObj.foodTracker(account);
					break;

				case 4:// weight tracker
					trackObj.weightTracker(stats);

					// must add graph display
					break;

				case 5:// signout
					break;

				case 6: // message admin
					break;

				case 7: // edit account details
					break;

				case 8: // edit stats
					new EditUI().editStats(stats);
					break;

				case 9: // set target
					new EditUI().setTarget(stats);
					break;

				case 10: // exit app
					return;

				default:
					logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
				}

			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e + "");
		} finally {
			sc.close();
		}
	}

	public static void displayAnonUserMenu() {
		logger.info(DisplayStringConstants.ANON_USER_MENU);
	}

	public static void displayLoggedInAdminMenu() {
		logger.info("");
	}

	public static void displayCustomOutput() {

	}

	public void showMenu(UserAccount account) {
		int userAccessLevel = new AccountService().getAccessLevel(account);
		if (userAccessLevel == 1) {
			// admin
			displayLoggedInAdminMenu();
		} else if (userAccessLevel == 2) {
			// public user
			displayLoggedInUserMenu(account);
		} else {
			// anon
			displayAnonUserMenu();
		}
	}

}
