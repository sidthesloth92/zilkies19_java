package io.ztech.contact_app.services;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.ztech.contact_app.constants.ApplicationConstants;

public class ValidateInput {
	// validating first_name
	static Logger log = Logger.getLogger("ValidateInput.class");

	public static boolean validateName(String name) {

		if (name.trim().length() > 20) {
			log.info(ApplicationConstants.ISVALID_LENGTH);
			log.info(ApplicationConstants.ENTER_VALID_NAME);
			return false;
		}
		if (name.trim().indexOf(' ') != -1) {
			System.out.println(ApplicationConstants.IS_SPACE);
			System.out.println(ApplicationConstants.ENTER_VALID_NAME);

			return false;
		}
		return true;
	}

	public static boolean validateMobileNumber(String number) {
		// TODO Auto-generated method stub
		String pattern = "\\+\\d{2}-\\d{10}";
		boolean matcher = Pattern.matches(pattern, number);
		log.info(String.valueOf(matcher));
		return matcher;
	}

	public static boolean validateOfficeNumber(String number) {
		// TODO Auto-generated method stub
		String pattern = "\\d{3}-\\d{7}";
		boolean matcher = Pattern.matches(pattern, number);

		return matcher;
	}

	public static boolean validateEmail(String email) {
		// TODO Auto-generated method stub
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}

	public static boolean validateId(String contact_id, ArrayList<String> id) {
		// TODO Auto-generated method stub
		if (id.contains(contact_id)) {
			return true;
		}
		return false;
	}

	public static boolean validateMobileId(String mobile_id, ArrayList<String> id) {
		// TODO Auto-generated method stub
		if (id.contains(mobile_id)) {
			return true;
		}
		return false;
	}

	public static boolean validateOfficeId(String office_id, ArrayList<String> id) {
		// TODO Auto-generated method stub
		if (id.contains(office_id)) {
			return true;
		}
		return false;
	}

	public static boolean validateHomeId(String home_id, ArrayList<String> id) {
		// TODO Auto-generated method stub
		if (id.contains(home_id)) {
			return true;
		}
		return false;
	}

	public static boolean validateEmailId(String email_id, ArrayList<String> id) {
		// TODO Auto-generated method stub
		if (id.contains(email_id)) {
			return true;
		}

		return false;
	}

}
