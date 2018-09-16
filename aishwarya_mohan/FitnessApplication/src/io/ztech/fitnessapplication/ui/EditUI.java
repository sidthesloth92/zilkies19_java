package io.ztech.fitnessapplication.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserAccountDetails;
import io.ztech.fitnessapplication.beans.UserPhysicalDetails;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;
import io.ztech.fitnessapplication.service.AccountService;
import io.ztech.fitnessapplication.service.UserPhysicalDetailsService;

public class EditUI {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	public int askTarget(UserPhysicalDetails physicalProfile) {
		logger.info(DisplayStringConstants.OPT1 + physicalProfile.getBmr());
		logger.info(DisplayStringConstants.OPT2 + (physicalProfile.getBmr() - 500));
		logger.info(DisplayStringConstants.OPT3 + (physicalProfile.getBmr() - 1000));
		logger.info(DisplayStringConstants.OPT4 + (physicalProfile.getBmr() + 500));
		logger.info(DisplayStringConstants.OPT5 + (physicalProfile.getBmr() + 1000));
		logger.info(DisplayStringConstants.TARGET_CHOICE);
		return sc.nextInt();
	}

	public void setTarget(UserPhysicalDetails physicalProfile) {

		try {
			int target = askTarget(physicalProfile);
			sc.nextLine();
			if (target < 1 || target > 5) {
				logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
			} else {

				physicalProfile.setPlan(target);
				// System.out.println(physicalProfile.getDailyTarget());
				boolean isSet = new UserPhysicalDetailsService().setTarget(physicalProfile);
				if (isSet) {
					logger.info(DisplayStringConstants.ACHIEVE_TARGET);
				} else {
					logger.info(DisplayStringConstants.TARGET_NOT_SET);
					setTarget(physicalProfile);
				}
			}
		} catch (InputMismatchException e) {
			logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
		} finally {
			

		}
	}

	public boolean editphysicalProfile(UserPhysicalDetails physicalProfile) {
		int choice = 0;
		try {
			do {
				logger.info(DisplayStringConstants.PROFILE_MENU);
				choice = sc.nextInt();
				switch (choice) {
				case 1:// height
					logger.info(DisplayStringConstants.HEIGHT);
					physicalProfile.setHeight(sc.nextFloat());
					break;
				case 2:// weight
					logger.info(DisplayStringConstants.WEIGHT);
					physicalProfile.setWeight(sc.nextFloat());
					break;
				case 3:// age
					logger.info(DisplayStringConstants.AGE);
					physicalProfile.setAge(sc.nextInt());
					break;
				case 4:// gender
					logger.info(DisplayStringConstants.GENDER);
					physicalProfile.setGender(sc.next());
					break;
				case 5:// lifestyle
					logger.info(DisplayStringConstants.LIFESTYLE_MENU);
					physicalProfile.setActivty(sc.nextInt());
					break;
				default:
					logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
				}

				logger.info(DisplayStringConstants.CONTINUE_CHOICE);
			} while (sc.next().charAt(0) == 'y');

		} catch (Exception e) {
			logger.info(e + "");
			return editphysicalProfile(physicalProfile);
		}
		return new UserPhysicalDetailsService().updatephysicalProfile(physicalProfile);
	}

	public boolean editAccount(UserAccount account) {
		UserAccountDetails oldAccount = new AccountService().getProfile(account);
		int choice = 0;
		try {
			do {
				logger.info(DisplayStringConstants.DETAILS_MENU);
				choice = sc.nextInt();
				switch (choice) {
				case 1:// firstname
					logger.info(DisplayStringConstants.FIRST_NAME);
					oldAccount.setFirstName(sc.next());
					break;
				case 2:// lastname
					logger.info(DisplayStringConstants.LAST_NAME);
					oldAccount.setLastName(sc.next());
					break;
				case 4:// email
					logger.info(DisplayStringConstants.MAIL);
					oldAccount.setEmail(sc.next());
					break;
				case 5:// phone
					logger.info(DisplayStringConstants.PHONE);
					oldAccount.setPhone(sc.next());
					break;
				default:
					logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
				}

				logger.info(DisplayStringConstants.CONTINUE_CHOICE);
			} while (sc.next().charAt(0) == 'y');

		} catch (Exception e) {
			logger.info(e + "");
			return editAccount(account);
		}
		return new AccountService().updateAccount(oldAccount);
	}

}
