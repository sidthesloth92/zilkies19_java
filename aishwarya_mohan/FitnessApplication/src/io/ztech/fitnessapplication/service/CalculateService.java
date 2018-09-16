package io.ztech.fitnessapplication.service;

import io.ztech.fitnessapplication.beans.UserPhysicalDetails;

public class CalculateService {
	public static float calculateBMI(UserPhysicalDetails stats) {
		float bmiVal = 0;
		float height = stats.getHeight() / 100;
		bmiVal = stats.getWeight() / (height * height);
		return bmiVal;
	}

	public static int calculateBMR(UserPhysicalDetails stats) {
		float bmr = (int) ((stats.getHeight() * 6.25) + (stats.getWeight() * 9.99) - (stats.getAge() * 4.92));
		if (stats.getGender().equalsIgnoreCase("male")) {
			bmr += 5;
		} else

		{
			bmr -= 161;
		}
		switch (stats.getActivty()) {
		case 1: // sedentary
			bmr *= 1.2;
			break;
		case 2: // light
			bmr *= 1.375;
			break;
		case 3: // moderate
			bmr *= 1.55;
			break;
		case 4: // hard
			bmr *= 1.725;
			break;
		case 5: // very hard
			bmr *= 1.9;
			break;
		}
		// System.out.println(bmr);
		return (int) bmr;
	}

}
