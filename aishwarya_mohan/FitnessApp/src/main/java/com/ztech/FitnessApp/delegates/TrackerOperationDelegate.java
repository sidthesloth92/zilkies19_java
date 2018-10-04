package com.ztech.FitnessApp.delegates;

import java.util.ArrayList;

import com.ztech.FitnessApp.beans.FoodItem;
import com.ztech.FitnessApp.beans.FoodLog;
import com.ztech.FitnessApp.beans.FoodLogEnquiryDetails;
import com.ztech.FitnessApp.dao.FoodTrackerDao;

public class TrackerOperationDelegate {

	public boolean addFoodLog(ArrayList<FoodLog> foodLogList) {
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

	public ArrayList<FoodItem> getFoodLog(FoodLogEnquiryDetails enquiry) {
		ArrayList<FoodItem> foodsList = new ArrayList<>();
		int foodTrackerID = new FoodTrackerDao().getFoodTrackerEntry(enquiry);

		if (foodTrackerID != 0) {
			enquiry.setFoodTrackerID(foodTrackerID);
			ArrayList<Integer> mealList = new FoodTrackerDao().getMealList(enquiry);
			ArrayList<FoodLog> foodItemList = new FoodTrackerDao().getFoodItemsList(mealList);
			foodsList = new FoodTrackerDao().getFoodItemsListWithDetails(foodItemList);
		}

		return foodsList;
	}
	
	public boolean deleteFoodLog(FoodLogEnquiryDetails enquiry) {
		int foodTrackerID = new FoodTrackerDao().getFoodTrackerEntry(enquiry);
		enquiry.setFoodTrackerID(foodTrackerID);
		return new FoodTrackerDao().deleteFoodLog(enquiry);
	}

}

//@service for ddel
//@repository for DAO
//@autowired
