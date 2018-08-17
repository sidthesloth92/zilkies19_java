package com.zilkeesaro.service;

import java.util.Scanner;
import java.util.logging.Logger;

import com.zilkeesaro.beans.Details;
import com.zilkeesaro.constants.RegexContstants;
import com.zilkeesaro.constants.Strings;
import com.zilkeesaro.util.ValidationHelper;

public class ScanInputs {

	public static String first_name, last_name, e_mail, home, office, mobile_number;

	public static final Logger logger = Logger.getLogger(ScanInputs.class.getName());
	
	ValidationHelper helper=new ValidationHelper();

	Scanner in = new Scanner(System.in);
	
	public ScanInputs() {
		//System.out.println("position");
	}

	public String getHome() {
		logger.info(Strings.HOME_NUMBER_DISPLAY);
		home = in.nextLine();
		
		if(home.equals("-1")) {
			return " ";
		}
		else if(helper.getHome(home)) {
			return home;
		}else {
			logger.info(Strings.PHONE_ERROR);
			return getHome();
		}
	}

	public String getOffice() {
		logger.info(Strings.OFFICE_NUMBER_DISPLAY);
		office = in.nextLine();
		
		if(office.equals("-1")) {
			return " ";
		}
		else if(helper.getOffice(office)) {
			return office;
		}else {
			logger.info(Strings.PHONE_ERROR);
			return getOffice();
		}

	}

	public String getE_mail() {
		logger.info(Strings.EMAIL_ID_DISPLAY);
		e_mail = in.nextLine();
		
		if(e_mail.equals("-1")) {
			return " ";
		}
		else if(helper.getE_mail(e_mail)) {
			return e_mail;
		}
		else {
			logger.info(Strings.EMAIL_ID_ERROR);
			return getE_mail();
		}

	}

	public String getMobile_number() {
		logger.info(Strings.MOBILE_NUMBER_DISPLAY);
		mobile_number = in.nextLine();
		
		if(mobile_number.equals("-1")) {
			return " ";
		}
		else if(helper.getMobile_number(mobile_number)) {
			return mobile_number;
		}else {
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
