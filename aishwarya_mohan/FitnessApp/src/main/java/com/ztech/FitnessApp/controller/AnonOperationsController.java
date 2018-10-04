package com.ztech.FitnessApp.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ztech.FitnessApp.beans.FoodItem;
import com.ztech.FitnessApp.delegates.AnonymousUserOperationsDelegate;

@RestController
public class AnonOperationsController {

	private static Logger logger;

	public AnonOperationsController() {
		logger = Logger.getLogger(AnonOperationsController.class.getName());
	}

	@RequestMapping(value = "/get-food-list", method = RequestMethod.GET)
	public ArrayList<FoodItem> getFoodItems(@RequestParam("foodname") String foodname) {
		logger.info("enter AnonOperationsController/get-food-list");
		try {
			ArrayList<FoodItem> foodList = new AnonymousUserOperationsDelegate().getFoodList(foodname);
			return foodList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("exit AnonOperationsController/get-food-list");
		return null;
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(@RequestParam("name") String name) {
		logger.info("enter AnonOperationsController/get-food-list");

		logger.info("exit AnonOperationsController/get-food-list");
		return name;
	}

}
