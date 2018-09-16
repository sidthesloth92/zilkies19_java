package io.ztech.fitnessapplication.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserPhysicalDetails;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;
import io.ztech.fitnessapplication.service.AccountService;
import io.ztech.fitnessapplication.service.CalculateService;
import io.ztech.fitnessapplication.service.UserPhysicalDetailsService;

public class MenuUI {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	private static final DisplayUI dispObj = new DisplayUI();
	private static final TrackerUI trackObj = new TrackerUI();
	private static final EditUI editObj = new EditUI();

	public static void displayLoggedInUserMenu(UserAccount account) {
		int choice;
		UserPhysicalDetails physicalProfile = new UserPhysicalDetailsService().getphysicalProfile(account);
		physicalProfile.setUserName(account.getUserName());
		try {
			do {
				logger.info(DisplayStringConstants.LOGGED_IN_USER_MENU);
				choice = sc.nextInt();

				switch (choice) {
				case 1:// view physicalProfile
					dispObj.displayphysicalProfile(account);
					break;

				case 2:// view account details
					dispObj.displayProfile(account);

					break;

				case 3:// food tracker
					trackObj.foodTracker(account);
					break;

				case 4:// weight tracker
					trackObj.weightTracker(physicalProfile);

					// must add graph display
					break;

				case 5:// signout
					account = null;
					displayAnonUserMenu();
					break;

				case 6: // message admin
					break;

				case 7: // edit account details
					editObj.editAccount(account);
					break;

				case 8: // edit physical details
					editObj.editphysicalProfile(physicalProfile);
					break;

				case 9: // set target
					editObj.setTarget(physicalProfile);
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
		int choice;
		try {
			do {
				logger.info(DisplayStringConstants.ANON_USER_MENU);
				choice = sc.nextInt();

				switch (choice) {
				case 1:// signin
					UserAccount curAccount = new LogInPageUI().loginUI();
					boolean accountExists = new AccountService().loginUser(curAccount);
					if (accountExists) {
						logger.info(DisplayStringConstants.WELCOME + curAccount.getUserName());
					} else {
						logger.info(DisplayStringConstants.NO_ACC);
					}
					displayLoggedInUserMenu(curAccount);

					break;

				case 2:// sign up

					curAccount = new SignUpPageUI().signupUI();
					if (curAccount != null) {
						UserPhysicalDetails accProfile = new SignUpPageUI().customiseAccount(curAccount);

						accProfile.setBmr(CalculateService.calculateBMR(accProfile));
						accProfile.setBmi(CalculateService.calculateBMI(accProfile));
						new EditUI().setTarget(accProfile);
						new UserPhysicalDetailsService().addphysicalProfile(accProfile);
						logger.info(DisplayStringConstants.WELCOME + curAccount.getUserName());
					} else {
						logger.info(DisplayStringConstants.NO_ACC);
					}

					displayLoggedInUserMenu(curAccount);

					break;

				case 3:// BMI calculator
					UserPhysicalDetails newAccount = new UserPhysicalDetails();

					logger.info(DisplayStringConstants.HEIGHT);
					newAccount.setHeight(sc.nextFloat());

					logger.info(DisplayStringConstants.WEIGHT);
					newAccount.setWeight(sc.nextFloat());

					float bmi = CalculateService.calculateBMI(newAccount);
					logger.info(DisplayStringConstants.BMI + bmi);
					if (bmi < 18.5) {
						logger.info(DisplayStringConstants.BMI_UNDER);
					} else if (bmi < 25) {
						logger.info(DisplayStringConstants.BMI_NORM);
					} else {
						logger.info(DisplayStringConstants.BMI_OVER);
					}

					break;

				case 4:// BMR calculator
					try {
						newAccount = new UserPhysicalDetails();
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

						logger.info(DisplayStringConstants.BMR + CalculateService.calculateBMR(newAccount));
					} catch (Exception e) {
						logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
					}
					break;

				case 5:// calculate calories

					break;

				case 6: // exit app

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

	public static void displayLoggedInAdminMenu() {
		logger.info(DisplayStringConstants.LOGGED_IN_ADMIN_MENU);
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
