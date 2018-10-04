package com.ztech.FitnessApp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ztech.FitnessApp.beans.FoodItem;
import com.ztech.FitnessApp.beans.FoodLog;
import com.ztech.FitnessApp.beans.FoodLogEnquiryDetails;
import com.ztech.FitnessApp.beans.UserFoodLog;
import com.ztech.FitnessApp.delegates.TrackerOperationDelegate;

@RestController
public class TrackerOperationsController {

	@RequestMapping(value = "/add-food-log/{userName}", method = RequestMethod.POST)
	public String addFoodLog(@RequestBody ArrayList<UserFoodLog> foodLogList,
			@PathVariable("userName") String userName) {

		ArrayList<FoodLog> fullFoodLogList = new ArrayList<>();
		FoodLog log;

		for (UserFoodLog foodLog : foodLogList) {
			log = new FoodLog();
			log.setUserName(userName);
			log.setDate(foodLog.getDate());
			log.setFoodName(foodLog.getName());
			log.setQuantity(foodLog.getQuantity());
			log.setMealTime(foodLog.getMealtime());
			fullFoodLogList.add(log);
		}

		boolean added = false;
		try {
			added = new TrackerOperationDelegate().addFoodLog(fullFoodLogList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return added + "";
	}

	@RequestMapping(value = "/get-food-log/{userName}", method = RequestMethod.GET)
	public ArrayList<FoodItem> getFoodLog(@PathVariable("userName") String userName, @RequestParam("date") String date,
			@RequestParam("mealtime") int mealtime) {

		FoodLogEnquiryDetails enquiry = new FoodLogEnquiryDetails();
		enquiry.setDate(date);
		enquiry.setMealTime(mealtime);
		enquiry.setUserName(userName);

		ArrayList<FoodItem> foodsList = new TrackerOperationDelegate().getFoodLog(enquiry);

		return foodsList;
	}
	
	@RequestMapping(value = "/delete-food-log/{userName}", method = RequestMethod.GET)
	public String deleteFoodLog(@PathVariable("userName") String userName, @RequestParam("date") String date,
			@RequestParam("mealtime") int mealtime) {

		FoodLogEnquiryDetails enquiry = new FoodLogEnquiryDetails();
		enquiry.setDate(date);
		enquiry.setMealTime(mealtime);
		enquiry.setUserName(userName);

		boolean deleted= new TrackerOperationDelegate().deleteFoodLog(enquiry);

		return deleted+"";
	}

}
