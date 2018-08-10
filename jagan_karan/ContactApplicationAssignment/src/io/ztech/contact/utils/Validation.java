package io.ztech.contact.utils;

import java.util.logging.Logger;

import io.ztech.contact.constants.ConstantDisplayStatements;

public class Validation {
	public static final Logger logger = Logger.getLogger("Validation");

	public static boolean validateEmail(String email) {
		if (!email.matches("^[a-zA-Z0-9.-_]+@[a-zA-Z]+[.][a-zA-Z]{2,10}([.][a-zA-Z]{2,10})*")) {
			logger.info(ConstantDisplayStatements.INVALIDEMAIL);
			return false;
		} else {

			return true;
		}
	}

	public static boolean validateHomeNumber(String number) {
		if (number.matches("91[4][0-9]{9}"))
			return true;
		else {
			logger.info(ConstantDisplayStatements.INVALIDHOME);
			return false;
		}
	}

	public static boolean validateofficeNumber(String number) {
		if (number.matches("91[4][0-9]{9}"))
			return true;
		else {
			logger.info(ConstantDisplayStatements.INVALIDOFFICE);
			return false;
		}
	}

	public static boolean validatemobileNumber(String number) {
		if (number.matches("91[6-9][0-9]{10}")) {
			return true;
		}else {
			logger.info(ConstantDisplayStatements.INVALIDMOBILE);
			return false;
		}
	}
}
