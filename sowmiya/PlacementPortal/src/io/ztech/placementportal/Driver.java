package io.ztech.placementportal;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.ui.LoginPortal;

public class Driver {
	public static Scanner scan = new Scanner(System.in);
	static Logger log = Logger.getLogger("Driver.class");

	public static void main(String args[]) {
		log.info(ApplicationConstants.LOGIN);
		LoginPortal login = new LoginPortal();
		login.loginDetails();

	}

}
