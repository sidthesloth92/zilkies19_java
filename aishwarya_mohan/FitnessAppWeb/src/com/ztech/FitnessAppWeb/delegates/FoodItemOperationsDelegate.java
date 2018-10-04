package com.ztech.FitnessAppWeb.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class FoodItemOperationsDelegate {
	Logger logger;

	public FoodItemOperationsDelegate() {
		logger = Logger.getLogger(FoodItemOperationsDelegate.class.getName());
	}

	public String getFoodsList(String foodname) {
		logger.info("enter getFoodsList(foodname) @ FoodItemOperationsDelegate");

		String url = "http://localhost:8051/get-food-list?foodname=" + foodname;

		Gson gson = new Gson();

		String foodList = "";

		try {
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			foodList = response.toString();
			// System.out.println("response in delegate"+response);
			// System.out.println("foodList in delegate"+foodList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("exit getFoodsList(foodname) @ FoodItemOperationsDelegate");
		return foodList;
	}

}
