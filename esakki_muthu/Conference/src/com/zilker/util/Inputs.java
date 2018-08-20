package com.zilker.util;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.constants.StringConstants;

public class Inputs {

	Logger logger = Logger.getLogger(Inputs.class.getName());

	Scanner in = new Scanner(System.in);

	ValidationHelper helper = new ValidationHelper();

	String user_name, pass_word, confirm_password, role_name, e_mail, phone_no;

	String hall_name, description;

	String facility_name, result;

	int facility_id;

	int user_id, role_id, size;

	public String get_User_name() {

		user_name = in.nextLine();

		if (!helper.check_User_name(user_name)) {

			logger.info(StringConstants.USER_NAME_ERROR);

			return get_User_name();
		}

		return user_name;
	}

	public String getPassword() {

		pass_word = in.nextLine();

		if (!helper.check_Password(pass_word)) {

			logger.info(StringConstants.PASSWORD_ERROR);

			return getPassword();
		}

		return pass_word;
	}

	public String getConfirmPassword() {

		confirm_password = in.nextLine();

		if (!helper.check_Match_Password(pass_word, confirm_password)) {

			logger.info(StringConstants.CONFIRM_PASSWORD_ERROR);

			return getConfirmPassword();
		}

		return confirm_password;
	}

	public String getEmail() {

		e_mail = in.nextLine();

		if (!helper.checkEmail(e_mail)) {

			logger.info(StringConstants.EMAIL_ERROR);

			return getEmail();
		}

		return e_mail;

	}

	public String getPhone() {

		phone_no = in.nextLine();

		if (!helper.check_Phone_no(phone_no)) {

			logger.info(StringConstants.PHONE_ERROR);

			return getPhone();
		}

		return phone_no;

	}

	public String getRoleName() {

		role_name = in.nextLine();

		return role_name;
	}

	public String getHallName() {

		hall_name = in.nextLine();

		return hall_name;
	}

	public String getDescription() {

		description = in.nextLine();

		return description;
	}

	public String getFacilityName() {

		facility_name = in.nextLine();

		return facility_name;
	}

	public int getFacilityId() {

		try {

			facility_id = in.nextInt();

			return facility_id;

		} catch (InputMismatchException e) {

			logger.info(StringConstants.NUMBER_ERROR);

			in.nextLine();

			return getFacilityId();
		}
	}

	public int getSize() {

		try {

			size = in.nextInt();

		} catch (InputMismatchException e) {

			logger.info(StringConstants.NUMBER_ERROR);

			in.nextLine();

			return getSize();
		}

		return size;
	}

	public String promptResult() {

		result = in.nextLine();

		return result;
	}

	public int numberInput() {

		int number;

		try {

			number = in.nextInt();

		} catch (Exception e) {
			
			logger.info(StringConstants.NUMBER_ERROR);

			in.nextLine();

			return numberInput();
		}

		return number;

	}

}
