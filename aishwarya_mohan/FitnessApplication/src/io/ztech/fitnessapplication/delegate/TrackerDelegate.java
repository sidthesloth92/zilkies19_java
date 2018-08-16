package io.ztech.fitnessapplication.delegate;

import java.util.HashMap;

import io.ztech.fitnessapplication.beans.FoodLog;
import io.ztech.fitnessapplication.dao.FoodLogDao;

public class TrackerDelegate {
	public HashMap<Integer, String> displayFood() {
		return new FoodLogDao().displayFood();
	}

	public int getLogID(FoodLog foodLog) {
		return new FoodLogDao().getLogID(foodLog);
	}

	public int getMealID(FoodLog foodLog) {
		return new FoodLogDao().getMealID(foodLog);
	}

	public boolean enterFoodLog(FoodLog foodLog) {
		return new FoodLogDao().enterFoodLog(foodLog);
	}

	public float getCalories(FoodLog foodLog) {
		return new FoodLogDao().getCalories(foodLog);
	}
}
