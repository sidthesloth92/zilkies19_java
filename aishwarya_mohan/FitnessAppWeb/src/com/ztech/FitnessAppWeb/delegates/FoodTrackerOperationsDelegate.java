package com.ztech.FitnessAppWeb.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class FoodTrackerOperationsDelegate {
	Logger logger;

	public FoodTrackerOperationsDelegate() {
		logger = Logger.getLogger(FoodTrackerOperationsDelegate.class.getName());
	}

	public boolean addFoodLog(String foodLogList, String userName) {
		logger.info("enter addFoodLog @ FoodTrackerOperationDelegate");

		String url = "http://localhost:8051/add-food-log/" + userName;
		try {

			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");

			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(foodLogList);
			out.close();

			// System.out.println("post body"+foodLogList);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// System.out.println("response in fedel" + response);

			if (new String(response).equalsIgnoreCase("true")) {
				logger.info("exit addFoodLog @ FoodTrackerOperationDelegate with status true");
				return true;
			} else {
				logger.info("exit addFoodLog @ FoodTrackerOperationDelegate");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getFoodLog(String userName, String date, int mealtime) {
		logger.info("enter addFoodLog @ FoodTrackerOperationDelegate");
		String foodLogString = "";

		String url = "http://localhost:8051/get-food-log/" + userName + "?date=" + date + "&mealtime=" + mealtime;
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
			foodLogString = new String(response);

			System.out.println("response in fedel" + response);
			logger.info("exit addFoodLog @ FoodTrackerOperationDelegate");

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exit addFoodLog @ FoodTrackerOperationDelegate");
		}
		return foodLogString;
	}
	
	public String deleteFoodLog(String userName, String date, int mealtime) {
		logger.info("enter addFoodLog @ FoodTrackerOperationDelegate");
		String foodLogString = "";

		String url = "http://localhost:8051/delete-food-log/" + userName + "?date=" + date + "&mealtime=" + mealtime;
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
			foodLogString = new String(response);

			System.out.println("response in fedel" + response);
			logger.info("exit addFoodLog @ FoodTrackerOperationDelegate");

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exit addFoodLog @ FoodTrackerOperationDelegate");
		}
		return foodLogString;
	}
	
	
	
}
