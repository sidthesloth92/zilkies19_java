package io.zilker.fantasy.delegate;

import java.util.regex.Pattern;

import io.zilker.fantasy.constants.RegexConstants;

public class GeneralValidators {
	public boolean checkVaildEmail(String email) {
		String emailRegex = RegexConstants.EMAIL_VALIDATOR;
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	// checks whether a date is valid or not
	public boolean checkScheduledDate(String scheduledDate) {
		// TODO Auto-generated method stub
		try {
			if (scheduledDate.charAt(4) != '/' || scheduledDate.charAt(7) != '/' || scheduledDate.length() != 10) {
				return false;
			}
			if (!Character.isDigit(scheduledDate.charAt(0)) || !Character.isDigit(scheduledDate.charAt(1))
					|| !Character.isDigit(scheduledDate.charAt(2)) || !Character.isDigit(scheduledDate.charAt(3))
					|| !Character.isDigit(scheduledDate.charAt(5)) || !Character.isDigit(scheduledDate.charAt(6))
					|| !Character.isDigit(scheduledDate.charAt(8)) || !Character.isDigit(scheduledDate.charAt(9))) {
				return false;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return true;
	}

	// checks whether a date is valid or not
	public boolean checkScheduledTime(String scheduledTime) {
		// TODO Auto-generated method stub
		try {
			if (scheduledTime.charAt(2) != ':' || scheduledTime.charAt(5) != ':' || scheduledTime.length() != 8) {
				return false;
			}
			if (!Character.isDigit(scheduledTime.charAt(0)) || !Character.isDigit(scheduledTime.charAt(1))
					|| !Character.isDigit(scheduledTime.charAt(3)) || !Character.isDigit(scheduledTime.charAt(4))
					|| !Character.isDigit(scheduledTime.charAt(6)) || !Character.isDigit(scheduledTime.charAt(7))) {
				return false;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return true;
	}

	public boolean checkValidRole(int type) {
		// TODO Auto-generated method stub
		if (type == 1 || type == 2 || type == 3 || type == 4) {
			return true;
		}
		return false;
	}

	public boolean checkRating(int modifiedRating) {
		// TODO Auto-generated method stub
		if (modifiedRating > 10 || modifiedRating < 1) {
			return false;
		}
		return true;
	}

}
