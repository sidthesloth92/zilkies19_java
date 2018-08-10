package com.zilkeesaro.service;

import java.util.Scanner;
import java.util.logging.Logger;

import com.zilkeesaro.beans.Details;
import com.zilkeesaro.constants.Strings;

public class ScanInputs {

	public static String first_name, last_name, e_mail, home, office, mobile_number;

	public static final Logger logger = Logger.getLogger(ScanInputs.class.getName());

	Scanner in = new Scanner(System.in);

	public String getHome() {
		logger.info(Strings.HOME_NUMBER_DISPLAY);
		home = in.nextLine();
		if (home.equals("") || home == null) {
			return getHome();
		}else if(home.equals("-1")) {
			return " ";
		} 
		else {
			if (home.matches("914[0-9]{3,4}[1-9][0-9]{5,7}")) {
				return home;
			} else {
				logger.info(Strings.PHONE_ERROR);
				return getHome();
			}
		}
	}

	public String getOffice() {
		logger.info(Strings.OFFICE_NUMBER_DISPLAY);
		office = in.nextLine();
		if (office.equals("") || office == null) {
			return getOffice();
		}else if(office.equals("-1")) {
			return " ";
		}
		else {
			if (office.matches("914[0-9]{3,4}[1-9][0-9]{5,7}")) {
				return office;
			} else {				
				logger.info(Strings.PHONE_ERROR);
				return getOffice();
			}
		}

	}

	public String getE_mail() {
		logger.info(Strings.EMAIL_ID_DISPLAY);
		e_mail = in.nextLine();
		if (e_mail.equals(" ") || e_mail == null) {
			return getE_mail();
		}else if(e_mail.equals("-1")) {
			return " ";
		}
		else {
			if (e_mail.matches("[a-z A-Z][a-z A-Z 0-9 .]+@[a-z A-Z 0-9]+(\\.[a-z A-Z 0-9]{2,8})*")) {
				return e_mail;
			} else {
				logger.info(Strings.EMAIL_ID_ERROR);
				return getE_mail();
			}
		}

	}

	public String getMobile_number() {
		logger.info(Strings.MOBILE_NUMBER_DISPLAY);
		mobile_number = in.nextLine();
		if (mobile_number.equals("") || mobile_number == null) {
			return getMobile_number();
		}else if(mobile_number.equals("-1")) {
			return " ";
		}
		else if (mobile_number.matches("91[6-9][0-9]{9}")) {
			return mobile_number;
		} else {
			logger.info(Strings.MOBILE_ERROR);
			return getMobile_number();
		}
	}

	public String getFirst_name() {
		logger.info(Strings.FIRST_NAME_DISPLAY);
		first_name = in.nextLine();
		if (first_name.equals("") || first_name == null) {
			return getFirst_name();
		}
		return first_name;
	}

	public String getLast_name() {
		logger.info(Strings.LAST_NAME_DISPLAY);
		last_name = in.nextLine();
		if (last_name.equals("") || last_name == null) {
			return getLast_name();
		}
		return last_name;
	}

}
