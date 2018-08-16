package io.ztech.placementportal.ui;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.UpdateStudentDetailService;
import io.ztech.placementportal.services.ViewDetailsService;

public class UpdatePersonalInfo {
	Logger log = Logger.getLogger("UpdatePersonalInfo.class");
	Scanner scan = new Scanner(System.in);

	public void update(Register login) {
		int choice;
		char continueChoice = ' ';
		UpdateStudentDetailService updateService = new UpdateStudentDetailService();
		ViewDetailsService view = new ViewDetailsService();
		PersonalInfo info = new PersonalInfo();
		HashMap<String, String> map;
		info.setStudent_id(login.getReg_no());
		map = view.viewPersonalInfo(info.getStudent_id());
		map.forEach((k,v)->System.out.println(k+" "+v));
		info.setD_O_B(map.get(ApplicationConstants.D_O_B));
		info.setAlternate_email(map.get(ApplicationConstants.ALTERNATE_EMAIL_ID));
		info.setEmail(map.get(ApplicationConstants.EMAIL_ID));
		info.setAlternate_phone(map.get(ApplicationConstants.ALTERNATE_PHONE));
		info.setPhone_number(map.get(ApplicationConstants.PHONE_NUMBER));
		info.setBlood_group(map.get(ApplicationConstants.BLOOD_GROUP));
		info.setLocation(map.get(ApplicationConstants.LOCATION));
		info.setGender(ApplicationConstants.GENDER);
		do {
			log.info(ApplicationConstants.PERSONAL_INFO_CHOICE);
			choice = scan.nextInt();
			scan.nextLine();
			switch (choice) {
			case 1:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.D_O_B);
				info.setD_O_B(scanInput(Regex.DATE));
				break;
			case 2:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.BLOOD_GROUP);
				info.setBlood_group(scanInput(Regex.BLOOD_GROUP));
				break;
			case 3:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.PHONE_NUMBER);
				info.setPhone_number(scanInput(Regex.PHONE_NUMBER));
				break;
			case 4:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.ALTERNATE_PHONE);
				info.setAlternate_phone(scanInput(Regex.PHONE_NUMBER));
				break;
			case 5:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.EMAIL_ID);
				info.setEmail(scanInput(Regex.EMAIL_REGEX));
				break;
			case 6:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.ALTERNATE_EMAIL_ID);
				info.setAlternate_email(scanInput(Regex.EMAIL_REGEX));
				break;
			case 7:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.GENDER);
				info.setGender(scanInput(Regex.GENDER));
				break;
			case 8:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.LOCATION);
				info.setLocation(scanInput(Regex.LOCATION));
				break;
			default:
				log.info(ApplicationConstants.VALID_INPUT);
				break;
			}
			log.info(ApplicationConstants.CONTINUE_EDIT);
			continueChoice = scan.nextLine().charAt(0);

		} while (continueChoice == 'Y' || continueChoice == 'y');
		if (updateService.updatePersonalDetail(info))
			log.info(ApplicationConstants.UPADTED);
		else
			log.info(ApplicationConstants.ERROR);

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
