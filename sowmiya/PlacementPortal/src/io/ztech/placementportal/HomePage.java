package io.ztech.placementportal;

import java.util.logging.Logger;

import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.ui.LoginPortal;

public class HomePage {
	private static Logger log = Logger.getLogger("Driver.class");;

	public static void main(String args[]) {
		System.setProperty(ApplicationConstants.LOGGER_FORMATER, "%5$s%6$s%n");
		log.info(ApplicationConstants.APPLICATION);
		log.info(ApplicationConstants.LOGIN);
		LoginPortal login = new LoginPortal();
		login.loginDetails();

	}

}
