package com.ztech.io.fitnessfirstprototype.delegates;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ztech.io.fitnessfirstprototype.beans.FoodItem;
import com.ztech.io.fitnessfirstprototype.beans.FoodLog;
import com.ztech.io.fitnessfirstprototype.beans.User;
import com.ztech.io.fitnessfirstprototype.beans.WeightLog;
import com.ztech.io.fitnessfirstprototype.dao.FoodTrackerDao;
import com.ztech.io.fitnessfirstprototype.dao.WeightTrackerDao;

public class TrackerOperationDelegator {
	public ArrayList<WeightLog> getWeightLog(User user) throws ClassNotFoundException, SQLException {
		return new WeightTrackerDao().getWeightLog(user);
	}

	public ArrayList<FoodItem> getFoodList(String foodname) throws ClassNotFoundException, SQLException {
		return new FoodTrackerDao().getFoodList(foodname);
	}

	public boolean updateWeight(WeightLog log) throws SQLException, ClassNotFoundException {
		return new WeightTrackerDao().addWeightLog(log) && new WeightTrackerDao().updateWeight(log);
	}

	public boolean addWeightLog(WeightLog log) throws SQLException, ClassNotFoundException {
		return new WeightTrackerDao().addWeightLog(log);
	}

	public boolean addFoodLog(ArrayList<FoodLog> foodLogList) throws SQLException, ClassNotFoundException {
		boolean added = true;

		for (FoodLog log : foodLogList) {
			int foodTrackerID = new FoodTrackerDao().addFoodTrackerEntry(log);
			log.setFoodtrackerID(foodTrackerID);

			int foodID = new FoodTrackerDao().getFoodID(log);
			log.setFoodID(foodID);

			int mealItemID = new FoodTrackerDao().addMealItemEntry(log);
			log.setMealItemID(mealItemID);

			added = new FoodTrackerDao().addFoodLog(log);
		}

		return added;
	}

}
