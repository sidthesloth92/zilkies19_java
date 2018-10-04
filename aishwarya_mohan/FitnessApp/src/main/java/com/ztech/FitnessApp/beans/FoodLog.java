package com.ztech.FitnessApp.beans;

public class FoodLog {

	private String userName, foodName, date;
	private int mealTime, foodID, mealItemID, foodtrackerID;
	private float quantity;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

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

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public int getMealItemID() {
		return mealItemID;
	}

	public void setMealItemID(int mealItemID) {
		this.mealItemID = mealItemID;
	}

	public int getFoodtrackerID() {
		return foodtrackerID;
	}

	public void setFoodtrackerID(int foodtrackerID) {
		this.foodtrackerID = foodtrackerID;
	}

}
