package com.ztech.FitnessApp.delegates;

import java.sql.SQLException;

import com.ztech.FitnessApp.beans.UserProfile;
import com.ztech.FitnessApp.dao.UserProfileDao;

public class ProfileOperationDelegator {

	private static UserProfileDao profileDaoObject;

	public ProfileOperationDelegator() {
		profileDaoObject = new UserProfileDao();
	}

	public boolean customise(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return profileDaoObject.customise(newProfile);
	}

	public boolean setAge(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return profileDaoObject.setAge(newProfile);
	}

	public boolean setHeight(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return profileDaoObject.setHeight(newProfile);
	}

	public boolean setWeight(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return profileDaoObject.setWeight(newProfile);
	}

	public boolean setLifestyle(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return profileDaoObject.setLifestyle(newProfile);
	}

	public boolean setBmi(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return profileDaoObject.setBmi(newProfile);
	}

	public boolean setBmr(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return profileDaoObject.setBmr(newProfile);
	}

	public boolean setTarget(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		return profileDaoObject.setTarget(newProfile);
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

	public UserProfile getProfile(String userName){
		return profileDaoObject.getProfile(userName);
	}

}
