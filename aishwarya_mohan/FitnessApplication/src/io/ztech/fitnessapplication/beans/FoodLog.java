package io.ztech.fitnessapplication.beans;

public class FoodLog {
	int mealID, foodID, mealTime, regID, logID;
	public int getLogID() {
		return logID;
	}

	public void setLogID(int logID) {
		this.logID = logID;
	}

	float quantity, calories;

	public int getRegID() {
		return regID;
	}

	public void setRegID(int regID) {
		this.regID = regID;
	}

	public int getMealTime() {
		return mealTime;
	}

	public void setMealTime(int mealTime) {
		this.mealTime = mealTime;
	}

	public int getMealID() {
		return mealID;
	}

	public void setMealID(int mealID) {
		this.mealID = mealID;
	}

	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

}
