package io.ztech.contactapp.service;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
import io.ztech.contactapp.beans.HomeNumber;
import io.ztech.contactapp.beans.MobileNumber;
import io.ztech.contactapp.beans.OfficeNumber;
import io.ztech.contactapp.constants.ApplicationStringConstants;

public class UserInput {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	public static boolean chooseToSaveChanges() {
		logger.info(ApplicationStringConstants.SAVE_CHOICE);
		if (sc.next().charAt(0) == 'y') {
			return true;
		}
		return false;
	}

	public static MobileNumber getMobileFromUser() {
		logger.info(ApplicationStringConstants.COUNTRY_CODE_INPUT);
		String countryCodeMobile = sc.next();
		logger.info(ApplicationStringConstants.MOBILE_NUMBER_INPUT);
		String mobileNumber = sc.next();
		if (!Validations.isValid(countryCodeMobile, 2) || !Validations.isValid(mobileNumber, 1)) {
			return null;
		}
		MobileNumber m = new MobileNumber();
		m.setMobileNumber(countryCodeMobile, mobileNumber);

		return m;
	}

	public static OfficeNumber getOfficeFromUser() {
		String extNum, officeNumber;
		logger.info(ApplicationStringConstants.EXT_NUM_INPUT);
		extNum = sc.next();
		logger.info(ApplicationStringConstants.OFFICE_NUM_INPUT);
		officeNumber = sc.next();
		if (!Validations.isValid(extNum, 2) || !Validations.isValid(officeNumber, 1)) {
			return null;
		}
		OfficeNumber o = new OfficeNumber();
		o.setOfficeNumber(extNum, officeNumber);
		return o;
	}

	public static HomeNumber getHomeFromUser() {
		String countryCodeHome, areaCode, homeNumber;
		logger.info(ApplicationStringConstants.COUNTRY_CODE_INPUT);
		countryCodeHome = sc.next();
		logger.info(ApplicationStringConstants.AREA_CODE_INPUT);
		areaCode = sc.next();
		logger.info(ApplicationStringConstants.HOME_NUM_INPUT);
		homeNumber = sc.next();

		if (!Validations.isValid(countryCodeHome, 2) || !Validations.isValid(areaCode, 2)
				|| !Validations.isValid(homeNumber, 1)) {
			return null;
		}

		HomeNumber h = new HomeNumber();
		h.setHomeNumber(areaCode, countryCodeHome, homeNumber);
		return h;
	}

	public static String getEmailFromUser() {
		logger.info(ApplicationStringConstants.EMAIL_INPUT);
		String mailID = sc.next();
		if (!Validations.isValid(mailID, 3)) {
			return null;

		}
		return mailID;
	}

}
