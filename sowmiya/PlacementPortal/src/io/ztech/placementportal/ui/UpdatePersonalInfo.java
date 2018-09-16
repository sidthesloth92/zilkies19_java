package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.RetrieveDetailsService;
import io.ztech.placementportal.services.UpdateStudentDetailService;

public class UpdatePersonalInfo {
	Logger log = Logger.getLogger("UpdatePersonalInfo.class");
	Scanner scan = new Scanner(System.in);

	public void update(Register login) {
		int choice;
		char continueChoice = ' ';
		UpdateStudentDetailService updateService = new UpdateStudentDetailService();
		RetrieveDetailsService view = new RetrieveDetailsService();
		PersonalInfo info = new PersonalInfo();
		info.setStudentId(login.getReg_no());
		try {
			info = view.viewPersonalInfo(info.getStudentId());
			log.info(info.toString());
			do {
				log.info(ApplicationConstants.PERSONAL_INFO_CHOICE);
				choice = scan.nextInt();
				scan.nextLine();
				switch (choice) {
				case 1:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.D_O_B);
					info.setDateOfBirth(scanInput(Regex.DATE));
					break;
				case 2:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.BLOOD_GROUP);
					info.setBloodGroup(scanInput(Regex.BLOOD_GROUP));
					break;
				case 3:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.PHONE_NUMBER);
					info.setPhoneNumber(scanInput(Regex.PHONE_NUMBER));
					break;
				case 4:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.ALTERNATE_PHONE);
					info.setAlternatePhone(scanInput(Regex.PHONE_NUMBER));
					break;
				case 5:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.EMAIL_ID);
					info.setEmail(scanInput(Regex.EMAIL_REGEX));
					break;
				case 6:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.ALTERNATE_EMAIL_ID);
					info.setAlternateEmail(scanInput(Regex.EMAIL_REGEX));
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
			updateService.updatePersonalDetail(info);
			log.info(ApplicationConstants.UPDATED);
		} catch (NumberFormatException e) {
			log.info(ApplicationConstants.VALID_NUMBER);
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
		} catch (InputMismatchException e) {
			log.info(ApplicationConstants.VALID_INPUT);
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
