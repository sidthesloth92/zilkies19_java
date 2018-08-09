package com.zilker.implementation;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.constant.consolestrings;

public class GetDetails {
	static Scanner in = new Scanner(System.in);
	public static final Logger logger = Logger.getLogger(GetDetails.class.getName());

	public static ArrayList<String> getmobiledetails(String a, String b, ArrayList<String> al) {
		do {
			logger.info(consolestrings.cc);
			a = in.next();
			logger.info(consolestrings.mobilenum);
			b = in.next();
		} while (Validation.validatemobile(a, b));
		al.add(a);
		al.add(b);
		return al;
	}

	public static ArrayList<String> getofficedetails(String exnum, String officenum, ArrayList<String> al) {
		do {
			logger.info(consolestrings.officeexten);
			exnum = in.next();
			logger.info(consolestrings.officenum);
			officenum = in.next();
		} while (!Validation.validateoffice(exnum, officenum));
		al.add(exnum);
		al.add(officenum);
		return al;
	}

	public static ArrayList<String> gethomedetails(String a, String b, String c, ArrayList<String> al) {
		do {
			logger.info(consolestrings.homearea);
			a = in.next();
			logger.info(consolestrings.cc);
			b = in.next();
			logger.info(consolestrings.homenum);
			c = in.next();
		} while (!Validation.validatehome(a, c));
		al.add(a);
		al.add(b);
		al.add(c);
		return al;
	}

	public static ArrayList<String> getemaildetails(String a, ArrayList<String> al) {
		do {
			logger.info(consolestrings.email);
			a = in.next();
		} while (!Validation.validateemail(a));
		al.add(a);
		return al;
	}

}
