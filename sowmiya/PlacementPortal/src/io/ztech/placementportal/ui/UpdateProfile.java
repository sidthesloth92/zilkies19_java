package io.ztech.placementportal.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.RetrieveDetailsService;
import io.ztech.placementportal.services.UpdateProfileService;

public class UpdateProfile {
	private Logger log;
	private ScanInput scan;
	private Scanner scanner;

	public UpdateProfile() {
		log = Logger.getLogger("UpdateProfile.class");
		scan = new ScanInput();
		scanner = new Scanner(System.in);
	}

	public void getDetailsToUpdate(String category) {
		int choice;
		char continueChoice = ' ';
		Profile profile = new Profile();
		UpdateProfileService update = new UpdateProfileService();
		RetrieveDetailsService getDetail = new RetrieveDetailsService();
		if (category.equals(ApplicationConstants.ACHIEVEMENT))
			log.info(ApplicationConstants.ACHIEVEMENT_ID);
		if (category.equals(ApplicationConstants.PROJECT))
			log.info(ApplicationConstants.PROJECT_ID);
		if (category.equals(ApplicationConstants.CERTIFICATIONS))
			log.info(ApplicationConstants.CERTIFICATION_ID);
		try {
			profile.setProfileDetailId(
					Integer.parseInt(scan.getInput(Regex.NUMBER, ApplicationConstants.VALID_COMPANY_ID)));
			profile = getDetail.viewProfileDetail(profile.getProfileDetailId(), category);
			do {
				log.info(ApplicationConstants.PROFILE_UPDATE_CHOICE);
				choice = Integer.parseInt(scan.getInput(Regex.NUMBER, ApplicationConstants.VALID_OPTION));
				switch (choice) {
				case 1:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.TITLE);
					profile.setTitle(scanner.nextLine());
					break;
				case 2:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.DESCRPTION);
					profile.setDescription(scanner.nextLine());
					break;
				default:
					log.info(ApplicationConstants.VALID_INPUT);
					break;
				}
				log.info(ApplicationConstants.CONTINUE_EDIT);
				continueChoice = scan.getInput(Regex.CHOICE, ApplicationConstants.VALID_CHOICE).charAt(0);

			} while (continueChoice == 'Y' || continueChoice == 'y');
			profile.setStudent_id(LoginPortal.reg_no);
			if (update.updateProfileDetail(profile, category))
				log.info(ApplicationConstants.UPDATED);
			else
				log.info(ApplicationConstants.ERROR);

		} catch (InputMismatchException e) {
			log.info(ApplicationConstants.VALID_INPUT);
		} catch (Exception e) {
			log.info(ApplicationConstants.ERROR);
		}

	}

}
