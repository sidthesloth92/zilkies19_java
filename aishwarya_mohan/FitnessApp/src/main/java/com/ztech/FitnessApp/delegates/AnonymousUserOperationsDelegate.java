package com.ztech.FitnessApp.delegates;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ztech.FitnessApp.beans.FoodItem;
import com.ztech.FitnessApp.dao.FoodTrackerDao;

public class AnonymousUserOperationsDelegate {
	private static Logger logger;

	public AnonymousUserOperationsDelegate() {
		logger = Logger.getLogger(AnonymousUserOperationsDelegate.class.getName());
	}

	// get food suggestions from DB
	public ArrayList<FoodItem> getFoodList(String foodname) {
		logger.info("enter getFoodList @ AnonymousUserOperationsDelegate");

		ArrayList<FoodItem> foodList = new ArrayList<>();
		try {
			foodList = new FoodTrackerDao().getFoodList(foodname);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("exit getFoodList @ AnonymousUserOperationsDelegate");
		return foodList;
	}

}
