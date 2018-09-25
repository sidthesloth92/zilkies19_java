package io.ztech.placementportal.delegate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ValidateInput {
	private Logger log;

	public ValidateInput() {
		log = Logger.getLogger("ValidateInput.class");
	}

	public static boolean validateDetail(String pattern, String input) {
		boolean matcher = Pattern.matches(pattern, input);
		return matcher;
	}

	public static boolean validateMark(String input) {
		if (Float.parseFloat(input) <= 100)
			return true;
		return false;
	}

	public static boolean validateCgpa(String input) {
		if (Float.parseFloat(input) <= 10)
			return true;
		else
			return false;
	}

	public static boolean validateArrearCount(String input) {
		if (Integer.parseInt(input) <= 45)
			return true;
		else
			return false;
	}

	public boolean validateDate(String inputDate1, String inputDate2) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateObject1 = dateFormatter.parse(inputDate1);
			Date dateObject2 = dateFormatter.parse(inputDate2);

			if (dateObject1.before(dateObject2)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println("in validate");
			log.warning(e.toString());
		}
		return false;

	}

}
