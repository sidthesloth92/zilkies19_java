package com.ztech.FitnessApp.beans;

public class UserFoodLog {

	private String name, date;
	private float quantity;
	private int mealtime;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public int getMealtime() {
		return mealtime;
	}

	public void setMealtime(int mealtime) {
		this.mealtime = mealtime;
	}

}