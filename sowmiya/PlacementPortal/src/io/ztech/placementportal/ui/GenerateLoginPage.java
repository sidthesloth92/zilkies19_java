package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.CreateLoginService;
import io.ztech.placementportal.services.RetrieveDetailsService;

public class GenerateLoginPage {
	Logger log;
	Scanner scan;
	ScanInput scanInput;

	public GenerateLoginPage() {
		log = Logger.getLogger("GenerateLogin.class");
		scanInput = new ScanInput();
		scan = new Scanner(System.in);
	}

	public void getRegistrationDetail() {
		Register register = new Register();
		CreateLoginService createLogin = new CreateLoginService();
		try {
			log.info(ApplicationConstants.REGISTER_NO);
			register.setReg_no(isExists(
					scanInput.getInput(Regex.REGISTER_NO_REGEX, ApplicationConstants.VALID_REGISTER_NO),
					ApplicationConstants.REGISTER_NO, ApplicationConstants.VALID_REGISTER_NO, Regex.REGISTER_NO_REGEX));
			log.info(ApplicationConstants.PASSWORD);
			register.setPassword(scanInput.getInput(Regex.PASSWORD_REGEX, ApplicationConstants.VALID_PASSWORD));
			log.info(ApplicationConstants.EMAIL);
			register.setEmail(isExists(scanInput.getInput(Regex.EMAIL_REGEX, ApplicationConstants.VALID_EMAIL),
					ApplicationConstants.EMAIL, ApplicationConstants.VALID_EMAIL, Regex.EMAIL_REGEX));
			register.setRole("student");
			log.info(ApplicationConstants.SEPARATING_STRING);
			createLogin.generateLogin(register);
			log.info(ApplicationConstants.REGISTRATION_SUCCESS);
			log.info(ApplicationConstants.SEPARATING_STRING);
		} catch (SQLException e) {
			log.info(e.getMessage());
			log.info(ApplicationConstants.SEPARATING_STRING);
		}
	}

	public String isExists(String input, String field, String errorMsg, String regex) throws SQLException {
		RetrieveDetailsService retrieveDetail = new RetrieveDetailsService();
		if (field.equals(ApplicationConstants.EMAIL_ID) || field.equals(ApplicationConstants.REGISTER_NO)) {
			if (!retrieveDetail.checkIsAvailable(input, field)) {
				return input;
			} else {
				if (field.equals(ApplicationConstants.EMAIL_ID))
					log.info(ApplicationConstants.EMAIL_EXISTS);
				if (field.equals(ApplicationConstants.REGISTER_NO))
					log.info(ApplicationConstants.REGISTER_NUMBER_EXISTS);
				input = scanInput.getInput(regex, errorMsg);
			}

		}
		return null;
	}
}
