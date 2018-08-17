package io.ztech.fitnessapplication.ui;

import java.util.HashMap;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserProfile;
import io.ztech.fitnessapplication.beans.UserStats;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;
import io.ztech.fitnessapplication.service.AccountService;
import io.ztech.fitnessapplication.service.FoodService;
import io.ztech.fitnessapplication.service.StatsService;

public class DisplayUI {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());

	public void displayProfile(UserAccount account) {
		UserProfile userProfile = new AccountService().getProfile(account);

		if (userProfile.getUserName() == null) {
			logger.info(DisplayStringConstants.ERROR);
		} else {
			logger.info(DisplayStringConstants.FIRST_NAME + userProfile.getFirstName());
			logger.info(DisplayStringConstants.LAST_NAME + userProfile.getLastName());
			logger.info(DisplayStringConstants.USER_NAME + userProfile.getUserName());
			logger.info(DisplayStringConstants.MAIL + userProfile.getEmailID());
			logger.info(DisplayStringConstants.PHONE + userProfile.getPhoneNo());
		}

	}

	public void displayStats(UserAccount account) {
		UserStats stats = new StatsService().getStats(account);

		if (stats == null) {
			logger.info(DisplayStringConstants.ERROR);
		} else {
			logger.info(DisplayStringConstants.USER_NAME + account.getUserName());
			logger.info(DisplayStringConstants.HEIGHT + stats.getHeight());
			logger.info(DisplayStringConstants.WEIGHT + stats.getWeight());
			logger.info(DisplayStringConstants.AGE + stats.getAge());
			logger.info(DisplayStringConstants.GENDER + stats.getGender());
			logger.info(DisplayStringConstants.LIFESTYLE_MENU + stats.getActivityLevel());
			logger.info(DisplayStringConstants.BMI + stats.getBmi());
			if (stats.getBmi() < 18.5) {
				logger.info(DisplayStringConstants.BMI_UNDER);
			} else if (stats.getBmi() < 25) {
				logger.info(DisplayStringConstants.BMI_NORM);
			} else {
				logger.info(DisplayStringConstants.BMI_OVER);
			}
			logger.info(DisplayStringConstants.BMR + stats.getBmr());

		}
	}

	public void displayFood() {
		HashMap<Integer, String> foodMap = new FoodService().displayFood();
		foodMap.forEach((K, V) -> logger.info(K + " " + V));
	}

}
