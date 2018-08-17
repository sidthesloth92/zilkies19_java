package io.ztech.fitnessapplication.service;

import java.util.HashMap;

import io.ztech.fitnessapplication.beans.FoodLog;
import io.ztech.fitnessapplication.delegate.TrackerDelegate;

public class FoodService {
	public HashMap<Integer, String> displayFood() {
		return new TrackerDelegate().displayFood();
	}

	public int getLogID(FoodLog foodLog) {
		return new TrackerDelegate().getLogID(foodLog);
	}
	
	public int getMealID(FoodLog foodLog) {
		return new TrackerDelegate().getMealID(foodLog);
	}
	
	public boolean enterFoodLog(FoodLog foodLog) {
		return new TrackerDelegate().enterFoodLog(foodLog);
	}
	
	public float getCalories(FoodLog foodLog) {
		return new TrackerDelegate().getCalories(foodLog);
	}
}
