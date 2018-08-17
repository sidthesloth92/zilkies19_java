package com.zilker.service;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.constant.ConsoleStrings;
import com.zilker.service.Validation;

public class Fetch {
	static Scanner in = new Scanner(System.in);
	public static final Logger logger = Logger.getLogger(Fetch.class.getName());

	public static ArrayList<String> getMobileDetails(String a, String b, ArrayList<String> al) {
		do {
			logger.info(ConsoleStrings.COUNTRY_CODE);
			a = in.next();
			logger.info(ConsoleStrings.MOBILENUMBER);
			b = in.next();
		} while (Validation.validatemobile(a, b));
		al.add(a);
		al.add(b);
		return al;
	}

	public static ArrayList<String> getOfficeDetails(String exnum, String officenum, ArrayList<String> al) {
		do {
			logger.info(ConsoleStrings.OFFICEEXTENSION);
			exnum = in.next();
			logger.info(ConsoleStrings.OFFICENUMBER);
			officenum = in.next();
		} while (!Validation.validateoffice(exnum, officenum));
		al.add(exnum);
		al.add(officenum);
		return al;
	}
	public static ArrayList<String> getHomeDetails(String a, String b, String c, ArrayList<String> al) {
		do {
			logger.info(ConsoleStrings.HOMEAREA);
			a = in.next();
			logger.info(ConsoleStrings.COUNTRY_CODE);
			b = in.next();
			logger.info(ConsoleStrings.HOMENUMBER);
			c = in.next();
		} while (!Validation.validatehome(a, c));
		al.add(a);
		al.add(b);
		al.add(c);
		return al;
	}

	public static ArrayList<String> getEmailDetails(String a, ArrayList<String> al) {
		do {
			logger.info(ConsoleStrings.EMAIL);
			a = in.next();
		} while (!Validation.validateemail(a));
		al.add(a);
		return al;
	}
}
