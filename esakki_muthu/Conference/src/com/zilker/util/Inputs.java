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

	String userName, password, confirmPassword, roleName, email, phoneNo;

	String hallName, description;

	String facilityName, result;

	int facilityId;

	int userId, roleId, size;

	public String get_User_name() {

		userName = in.nextLine();

		if (!helper.check_User_name(userName)) {

			logger.info(StringConstants.USER_NAME_ERROR);

			return get_User_name();
		}

		return userName;
	}

	public String getPassword() {

		password = in.nextLine();

		if (!helper.check_Password(password)) {

			logger.info(StringConstants.PASSWORD_ERROR);

			return getPassword();
		}

		return password;
	}

	public String getConfirmPassword() {

		confirmPassword = in.nextLine();

		if (!helper.checkMatchPassword(password, confirmPassword)) {

			logger.info(StringConstants.CONFIRM_PASSWORD_ERROR);

			return getConfirmPassword();
		}

		return confirmPassword;
	}

	public String getEmail() {

		email = in.nextLine();

		if (!helper.checkEmail(email)) {

			logger.info(StringConstants.EMAIL_ERROR);

			return getEmail();
		}

		return email;

	}

	public String getPhone() {

		phoneNo = in.nextLine();

		if (!helper.check_Phone_no(phoneNo)) {

			logger.info(StringConstants.PHONE_ERROR);

			return getPhone();
		}

		return phoneNo;

	}

	public String getRoleName() {

		roleName = in.nextLine();

		return roleName;
	}

	public String getHallName() {

		hallName = in.nextLine();

		return hallName;
	}

	public String getDescription() {

		description = in.nextLine();

		return description;
	}

	public String getFacilityName() {

		facilityName = in.nextLine();

		return facilityName;
	}

	public int getFacilityId() {

		try {

			facilityId = in.nextInt();

			return facilityId;

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
	
	public String dateInput() {
		
		String date = in.nextLine();
		
		if (!helper.checkDate(date)) {

			logger.info(StringConstants.DATE_ERROR);

			return dateInput();
		}
		
		return date;

	}

	public String checkFromDate() {
		
		String date = in.nextLine();
		
		
		
		return date;
	}

}
