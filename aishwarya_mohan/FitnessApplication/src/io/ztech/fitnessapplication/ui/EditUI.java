package io.ztech.fitnessapplication.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.beans.UserStats;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;
import io.ztech.fitnessapplication.service.StatsService;

public class EditUI {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	public int askTarget(UserStats stats) {
		logger.info(DisplayStringConstants.OPT1 + stats.getBmr());
		logger.info(DisplayStringConstants.OPT2 + (stats.getBmr() - 500));
		logger.info(DisplayStringConstants.OPT3 + (stats.getBmr() - 1000));
		logger.info(DisplayStringConstants.OPT4 + (stats.getBmr() + 500));
		logger.info(DisplayStringConstants.OPT5 + (stats.getBmr() + 1000));
		logger.info(DisplayStringConstants.TARGET_CHOICE);
		return sc.nextInt();
	}

	public void setTarget(UserStats stats) {

		try {
			int target = askTarget(stats);
			if (target < 1 || target > 5) {
				logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
			} else {

				stats.setDailyTarget(target);
				// System.out.println(stats.getDailyTarget());
				boolean isSet = new StatsService().setTarget(stats);
				if (isSet) {
					logger.info(DisplayStringConstants.ACHIEVE_TARGET);
				} else {
					logger.info(DisplayStringConstants.TARGET_NOT_SET);
					setTarget(stats);
				}
			}
		} catch (InputMismatchException e) {
			logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
		} finally {
			// sc.close();

		}
	}

	public boolean editStats(UserStats stats) {
		int choice = 0;
		try {
			do {
				logger.info(DisplayStringConstants.STATS_MENU);
				choice = sc.nextInt();
				switch (choice) {
				case 1:// height
					logger.info(DisplayStringConstants.HEIGHT);
					stats.setHeight(sc.nextFloat());
					break;
				case 2:// weight
					logger.info(DisplayStringConstants.WEIGHT);
					stats.setWeight(sc.nextFloat());
					break;
				case 3:// age
					logger.info(DisplayStringConstants.AGE);
					stats.setAge(sc.nextInt());
					break;
				case 4:// gender
					logger.info(DisplayStringConstants.GENDER);
					stats.setGender(sc.next().charAt(0));
					break;
				case 5:// lifestyle
					logger.info(DisplayStringConstants.LIFESTYLE_MENU);
					stats.setActivityLevel(sc.nextInt());
					break;
				default:
					logger.info(DisplayStringConstants.INVALID_INPUT_WARNING);
				}

				logger.info(DisplayStringConstants.CONTINUE_CHOICE);
			} while (sc.next().charAt(0) == 'y');

		} catch (Exception e) {
			logger.info(e + "");
			return editStats(stats);
		}
		return new StatsService().updateStats(stats);
	}

}
