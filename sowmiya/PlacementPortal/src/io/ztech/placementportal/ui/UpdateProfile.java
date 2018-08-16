package io.ztech.placementportal.ui;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.UpdateProfileService;
import io.ztech.placementportal.services.ViewDetailsService;

public class UpdateProfile {
	Logger log = Logger.getLogger("UpdateProfile.class");
	Scanner scan = new Scanner(System.in);

	public void getDetailsToUpdate(String category) {
		int choice;
		char continueChoice = ' ';
		Profile profile = new Profile();
		UpdateProfileService update = new UpdateProfileService();
		ViewDetailsService getDetail = new ViewDetailsService();
		HashMap<String, String> map = new HashMap<>();
		if (category.equals(ApplicationConstants.ACHIEVEMENT))
			log.info(ApplicationConstants.ACHIEVEMENT_ID);
		if (category.equals(ApplicationConstants.PROJECT))
			log.info(ApplicationConstants.PROJECT_ID);
		if (category.equals(ApplicationConstants.CERTIFICATIONS))
			log.info(ApplicationConstants.CERTIFICATION_ID);
		try {
			profile.setProfileDetailId(scan.nextInt());
			map = getDetail.viewProfileDetail(profile.getProfileDetailId(), category);
			profile.setTitle(map.get(ApplicationConstants.TITLE));
			profile.setDescription(map.get(ApplicationConstants.DESCRPTION));
			do {
				log.info(ApplicationConstants.PROFILE_UPDATE_CHOICE);
				choice = scan.nextInt();
				scan.nextLine();
				switch (choice) {
				case 1:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.TITLE);
					profile.setTitle(scan.nextLine());
					break;
				case 2:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.DESCRPTION);
					profile.setDescription(scan.nextLine());
					break;
				default:
					log.info(ApplicationConstants.VALID_INPUT);
					break;
				}
				log.info(ApplicationConstants.CONTINUE_EDIT);
				continueChoice = scan.nextLine().charAt(0);

			} while (continueChoice == 'Y' || continueChoice == 'y');
			profile.setStudent_id(LoginPortal.reg_no);
			if (update.updateProfileDetail(profile, category))
				log.info(ApplicationConstants.UPADTED);
			else
				log.info(ApplicationConstants.ERROR);

		} catch (InputMismatchException e) {
			log.info(ApplicationConstants.VALID_INPUT);
		}

	}

}
