package io.zilker.application.utils;

import java.util.HashMap;
import java.util.logging.Logger;

public class UserValidation {
	static Logger logger = Logger.getLogger(UserValidation.class.getName());

	public static boolean isValid(String value, String regex) {
		logger.info("Entering isValid");
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
		java.util.regex.Matcher matcher = pattern.matcher(value);
		logger.info("Leaving isValid");
		return matcher.matches();
	}

	public static boolean dateCheck(String date) {
		logger.info("Entering dateCheck");
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 31);
		map.put(2, 28);
		map.put(3, 31);
		map.put(4, 30);
		map.put(5, 31);
		map.put(6, 30);
		map.put(7, 31);
		map.put(8, 30);
		map.put(9, 31);
		map.put(10, 30);
		map.put(11, 31);
		map.put(12, 30);
		boolean isValid = false;
		String[] dateArray = date.split("-", 3);
		for (int i = 0; i < dateArray.length; i++) {
			logger.info(dateArray[i]);
		}
		if (Integer.parseInt(dateArray[0]) < map.get(Integer.parseInt(dateArray[1]))
				&& Integer.parseInt(dateArray[1]) < 12 && Integer.parseInt(dateArray[2]) < 2099) {
			isValid = true;
		}
		logger.info("Inside the Method " + isValid);
		logger.info("Leaving dateCheck");
		return isValid;
	}
}
