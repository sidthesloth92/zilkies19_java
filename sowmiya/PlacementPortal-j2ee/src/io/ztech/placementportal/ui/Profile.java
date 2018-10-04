package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.RetrieveDetailsService;

public class Profile {
	Logger log;
	Scanner scan;

	public Profile() {
		log = Logger.getLogger("Profile.class");
		scan = new Scanner(System.in);
	}

	public void buildProfile(Register login) {
		int option;
		char continueChoice = ' ';
		try {
			showDetails(login);
			BuildProfile buildProfile = new BuildProfile();
			UpdateProfile updateProfile = new UpdateProfile();
			do {
				log.info(ApplicationConstants.PROFILE_CHOICE);
				option = scan.nextInt();
				scan.nextLine();
				switch (option) {
				case 1:
					AddPersonalInfo personalInfo = new AddPersonalInfo();
					personalInfo.getInfo(login);
					break;
				case 2:
					UpdatePersonalInfo updateInfo = new UpdatePersonalInfo();
					updateInfo.update(login);
					break;
				case 3:
					buildProfile.getProfileDetail(ApplicationConstants.ACHIEVEMENT);
					break;
				case 4:
					updateProfile.getDetailsToUpdate(ApplicationConstants.ACHIEVEMENT);
					break;
				case 5:
					buildProfile.getProfileDetail(ApplicationConstants.PROJECT);
					break;
				case 6:
					updateProfile.getDetailsToUpdate(ApplicationConstants.PROJECT);
					break;
				case 7:
					buildProfile.getProfileDetail(ApplicationConstants.CERTIFICATIONS);
					break;
				case 8:
					updateProfile.getDetailsToUpdate(ApplicationConstants.CERTIFICATIONS);
					break;
				default:
					log.info(ApplicationConstants.WRONG_CHOICE);
				}
				log.info(ApplicationConstants.CONTINUE_CHOICE);
				continueChoice = scan.nextLine().charAt(0);

			} while (continueChoice == 'Y' || continueChoice == 'y');
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
		}
	}

	private void showDetails(Register login) throws SQLException {
		RetrieveDetailsService viewDetail = new RetrieveDetailsService();
		ArrayList<io.ztech.placementportal.bean.Profile> profile;
		profile = viewDetail.viewProfileDetail(login.getUserName(), ApplicationConstants.ACHIEVEMENT);
		if (profile != null) {
			log.info(ApplicationConstants.ACHIEVEMENT);
			log.info(ApplicationConstants.SEPARATING_STRING);
			profile.forEach(item -> item.toString());
			log.info(ApplicationConstants.SEPARATING_STRING);
		}
		profile = viewDetail.viewProfileDetail(login.getUserName(), ApplicationConstants.PROJECT);
		if (profile != null) {
			log.info(ApplicationConstants.PROJECT);
			log.info(ApplicationConstants.SEPARATING_STRING);
			profile.forEach(item -> item.toString());
			log.info(ApplicationConstants.SEPARATING_STRING);
		}
		profile = viewDetail.viewProfileDetail(login.getUserName(), ApplicationConstants.CERTIFICATIONS);
		if (profile != null) {
			log.info(ApplicationConstants.PROJECT);
			log.info(ApplicationConstants.SEPARATING_STRING);
			profile.forEach(item -> item.toString());
			log.info(ApplicationConstants.SEPARATING_STRING);
		}

	}

}
