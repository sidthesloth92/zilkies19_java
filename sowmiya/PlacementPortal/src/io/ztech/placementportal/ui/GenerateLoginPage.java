package io.ztech.placementportal.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.CreateLoginService;

public class GenerateLoginPage {
	Logger log = Logger.getLogger("GenerateLogin.class");
	Scanner scan = new Scanner(System.in);

	public void getRegistrationDetail() {
		Register register = new Register();
		CreateLoginService c_login = new CreateLoginService();
		boolean isSuccess = false;
		log.info(ApplicationConstants.REGISTER_NO);
		register.setReg_no(getDetails(Regex.REGISTER_NO_REGEX, ApplicationConstants.VALIDATE_REGISTER_NO));
		log.info(ApplicationConstants.USERNAME);
		register.setUserName(getDetails(Regex.USERNAME_REGEX, ApplicationConstants.VALIDATE_USERMSG));
		log.info(ApplicationConstants.PASSWORD);
		register.setPassword(getDetails(Regex.PASSWORD_REGEX, ApplicationConstants.VALIDATE_PASSMSG));
		log.info(ApplicationConstants.EMAIL);
		register.setEmail(getDetails(Regex.EMAIL_REGEX, ApplicationConstants.VALIDATE_EMAIL));
		register.setRole("student");
		isSuccess = c_login.generateLogin(register);
		if (isSuccess)
			log.info(ApplicationConstants.REGISTRATION_SUCCESS);
		else
			log.info(ApplicationConstants.ERROR);

	}

	public String getDetails(String regex, String validate_msg) {
		String input;
		boolean isValid = false;
		input = scan.nextLine();
		do {
			isValid = ValidateInput.validateDetail(regex, input);
			if (isValid == true)
				return input;
			else
				log.info(validate_msg);
			input = scan.nextLine();
		} while (!isValid);
		return null;

	}

}
