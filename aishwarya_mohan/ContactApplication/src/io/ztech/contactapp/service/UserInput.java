package io.ztech.contactapp.service;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
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

}
