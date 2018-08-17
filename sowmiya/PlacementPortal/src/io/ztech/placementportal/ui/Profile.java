package io.ztech.placementportal.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.ViewDetailsService;

public class Profile {
	Logger log = Logger.getLogger("Profile.class");
	static Scanner scan = new Scanner(System.in);

	public void buildProfile(Register login) {
		int option;
		char continueChoice = ' ';
		showDetails();
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
	}

	private void showDetails() {
		ViewDetailsService viewDetail = new ViewDetailsService();
		ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
		map = viewDetail.viewProfileDetail(LoginPortal.reg_no, ApplicationConstants.ACHIEVEMENT);
		log.info(ApplicationConstants.ACHIEVEMENT);
		map.forEach(item -> item.forEach((k, v) -> System.out.println(k + "" + v)));
		map = viewDetail.viewProfileDetail(LoginPortal.reg_no, ApplicationConstants.PROJECT);
		if (map.size() > 0) {
			log.info(ApplicationConstants.PROJECT);
			map.forEach(item -> item.forEach((k, v) -> System.out.println(k + "" + v)));
		}
		map = viewDetail.viewProfileDetail(LoginPortal.reg_no, ApplicationConstants.CERTIFICATIONS);
		if (map.size() > 0) {
			log.info(ApplicationConstants.CERTIFICATIONS);
			map.forEach(item -> item.forEach((k, v) -> System.out.println(k + "" + v)));
		}

	}

}
