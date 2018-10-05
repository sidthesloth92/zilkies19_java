package com.ztech.FitnessAppWeb.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.ztech.FitnessAppWeb.beans.UserProfile;

public class ProfileOperationDelegate {
	Logger logger;

	public ProfileOperationDelegate() {
		logger = Logger.getLogger(ProfileOperationDelegate.class.getName());
	}

	public UserProfile getProfile(String userName) {
		logger.info("enter getProfile @ ProfileOperationsDelegate");

		String url = "http://localhost:8051/loggedin-user-get-profile/" + userName;

		Gson gson = new Gson();
		UserProfile userProfile = null;

		try {
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(false);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			userProfile = gson.fromJson(new String(response), UserProfile.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("exit getProfile @ ProfileOperationsDelegate");
		return userProfile;
	}

	public boolean customise(UserProfile newProfile) throws ClassNotFoundException, SQLException {

		return false;
	}

	public boolean setAge(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return false;
	}

	public boolean setHeight(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return false;
	}

	public boolean setWeight(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return false;
	}

	public boolean setLifestyle(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return false;
	}

	public boolean setBmi(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return false;
	}

	public boolean setBmr(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return false;
	}

	public boolean setTarget(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return false;
	}

	public static float calculateBMI(UserProfile profile) {
		float bmiVal = 0;
		float height = profile.getHeight() / 100;
		bmiVal = profile.getWeight() / (height * height);
		return bmiVal;
	}

	public static float calculateBMR(UserProfile profile) {
		float bmr = (int) ((profile.getHeight() * 6.25) + (profile.getWeight() * 9.99) - (profile.getAge() * 4.92));
		if (profile.getGender() == 0) {
			bmr += 5;
		} else

		{
			bmr -= 161;
		}
		return bmr;
	}

	public static float calculateTDEE(UserProfile profile) {
		float tdee = profile.getBmr();
		switch (profile.getLifestyle()) {
		case 1: // sedentary
			tdee *= 1.2;
			break;
		case 2: // light
			tdee *= 1.375;
			break;
		case 3: // moderate
			tdee *= 1.55;
			break;
		case 4: // hard
			tdee *= 1.725;
			break;
		case 5: // very hard
			tdee *= 1.9;
			break;
		}
		return tdee;
	}

	public static String getBmiCategory(float bmi) {
		if (bmi < 18.5) {
			return "Underweight";
		} else if (bmi < 23) {
			return "Normal";
		} else if (bmi < 27.5) {
			return "Overweight";
		} else {
			return "Obese";
		}
	}

	public static String getBmiStatus(float bmi) {
		if (bmi < 18.5) {
			return "You have a risk of developing problems such as nutritional deficiency and osteoporosis. We advise you to gain weight to achieve a healthy range";
		} else if (bmi < 23) {
			return "You are in the healthy range. You have a low risk of heart diseases and obesity. Congrats. We advise you to maintain your weight and fitness";
		} else if (bmi < 27.5) {
			return "You have a moderate risk of developing heart disease, high blood pressure, stroke, diabetes. We advise you to lose weight to achieve a healthy range";
		} else {
			return "You have a high risk of developing heart disease, high blood pressure, stroke, diabetes. We advise you to lose weight to achieve a healthy range";
		}
	}

}
