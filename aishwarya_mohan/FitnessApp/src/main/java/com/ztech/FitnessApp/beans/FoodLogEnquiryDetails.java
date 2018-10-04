package com.ztech.FitnessApp.beans;

public class FoodLogEnquiryDetails {
	private String date, userName;
	private int mealTime, foodTrackerID;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMealTime() {
		return mealTime;
	}

	public void setMealTime(int mealTime) {
		this.mealTime = mealTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getFoodTrackerID() {
		return foodTrackerID;
	}

	public void setFoodTrackerID(int foodTrackerID) {
		this.foodTrackerID = foodTrackerID;
	}

}
