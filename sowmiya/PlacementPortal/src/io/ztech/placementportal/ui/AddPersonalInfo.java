package io.ztech.placementportal.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.AddDetailsService;

public class AddPersonalInfo {
	Logger log = Logger.getLogger("Profile.class");
	static Scanner scan = new Scanner(System.in);

	public void getInfo(Register login) {
		PersonalInfo personalInfo = new PersonalInfo();
		AddDetailsService addService = new AddDetailsService();
		personalInfo.setStudent_id(login.getReg_no());
		log.info(ApplicationConstants.D_O_B);
		personalInfo.setD_O_B(scanInput(Regex.DATE));
		log.info(ApplicationConstants.EMAIL_ID);
		personalInfo.setEmail(scanInput(Regex.EMAIL_REGEX));
		log.info(ApplicationConstants.ALTERNATE_EMAIL_ID);
		personalInfo.setAlternate_email(scanInput(Regex.EMAIL_REGEX));
		log.info(ApplicationConstants.PHONE_NUMBER);
		personalInfo.setPhone_number(scanInput(Regex.PHONE_NUMBER));
		log.info(ApplicationConstants.ALTERNATE_PHONE);
		personalInfo.setAlternate_phone(scanInput(Regex.PHONE_NUMBER));
		log.info(ApplicationConstants.BLOOD_GROUP);
		personalInfo.setBlood_group(scanInput(Regex.BLOOD_GROUP));
		log.info(ApplicationConstants.LOCATION);
		personalInfo.setLocation(scanInput(Regex.LOCATION));
		log.info(ApplicationConstants.GENDER);
		personalInfo.setGender(scanInput(Regex.GENDER));
		if (addService.addPersonalInfo(personalInfo)) {
			log.info(ApplicationConstants.SUCCESS);
		} else {
			log.info(ApplicationConstants.ERROR);
		}

	}

	private String scanInput(String pattern) {
		boolean isValid;
		do {
			isValid = false;
			String input = scan.nextLine();
			if (ValidateInput.validateDetail(pattern, input)) {
				return input;
			}
			isValid = true;
			log.info(ApplicationConstants.VALID_INPUT);
		} while (isValid);
		return null;
	}

}
