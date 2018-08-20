package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.AddDetailsService;

public class AddPersonalInfo {
	private Logger log;
	private Scanner scan;

	public AddPersonalInfo() {
		log = Logger.getLogger("Profile.class");
		scan = new Scanner(System.in);

	}

	public void getInfo(Register login) {
		PersonalInfo personalInfo = new PersonalInfo();
		AddDetailsService addService = new AddDetailsService();
		personalInfo.setStudentId(login.getReg_no());
		try {
			log.info(ApplicationConstants.D_O_B);
			personalInfo.setDateOfBirth(scanInput(Regex.DATE));
			log.info(ApplicationConstants.EMAIL_ID + ApplicationConstants.MAIL_CONSTRAINT);
			personalInfo.setEmail(scanInput(Regex.EMAIL_REGEX));
			log.info(ApplicationConstants.ALTERNATE_EMAIL_ID + ApplicationConstants.MAIL_CONSTRAINT);
			personalInfo.setAlternateEmail(scanInput(Regex.EMAIL_REGEX));
			log.info(ApplicationConstants.PHONE_NUMBER + ApplicationConstants.PHONE_CONSTRAINT);
			personalInfo.setPhoneNumber(scanInput(Regex.PHONE_NUMBER));
			log.info(ApplicationConstants.ALTERNATE_PHONE + ApplicationConstants.PHONE_CONSTRAINT);
			personalInfo.setAlternatePhone(scanInput(Regex.PHONE_NUMBER));
			log.info(ApplicationConstants.BLOOD_GROUP + ApplicationConstants.MAIL_CONSTRAINT);
			personalInfo.setBloodGroup(scanInput(Regex.BLOOD_GROUP));
			log.info(ApplicationConstants.LOCATION);
			personalInfo.setLocation(scanInput(Regex.LOCATION));
			log.info(ApplicationConstants.GENDER + ApplicationConstants.GENDER_CONSTRAINT);
			personalInfo.setGender(scanInput(Regex.GENDER));
			addService.addPersonalInfo(personalInfo);
			log.info(ApplicationConstants.UPDATED);
			log.info(ApplicationConstants.SEPARATING_STRING);
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
			log.info(ApplicationConstants.SEPARATING_STRING);

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
