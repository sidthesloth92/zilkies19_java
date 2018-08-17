package io.ztech.placementportal.services;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.ui.ValidateInput;

public class ScanDetails {
	public static Scanner scan = new Scanner(System.in);
	Logger log = Logger.getLogger("ScanDetails.class");

	public String scanDetails(String regex, String validateMsg) {
		boolean flag;
		try {
			do {
				flag = false;
				String input = scan.nextLine();
				if (ValidateInput.validateDetail(regex, input)) {
					return input;
				} else {
					log.info(validateMsg);
					flag = true;

				}
			} while (flag);
		} catch (InputMismatchException e) {
			log.info(ApplicationConstants.VALID_INPUT);
		}
		return "";
	}
}
