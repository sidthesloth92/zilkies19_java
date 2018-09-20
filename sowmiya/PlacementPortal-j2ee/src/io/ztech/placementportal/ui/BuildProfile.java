package io.ztech.placementportal.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.BuildProfileService;

public class BuildProfile {
	Logger log;
	Scanner scan;

	public BuildProfile() {
		log = Logger.getLogger("BuildProfile.class");
		scan = new Scanner(System.in);
	}

	public void getProfileDetail(String achievement) {
		Profile profile = new Profile();
		BuildProfileService buildService = new BuildProfileService();
		String input = null;
		log.info(ApplicationConstants.ENTER + ApplicationConstants.TITLE);
		profile.setTitle(scan.nextLine());
		log.info(ApplicationConstants.ENTER + ApplicationConstants.DESCRPTION);
		input = scan.nextLine();
		profile.setDescription(input);
		profile.setStudent_id(LoginPortal.reg_no);
		if (buildService.addToProfile(profile, achievement))
			log.info(ApplicationConstants.SUCCESS);
		else
			log.info(ApplicationConstants.ERROR);

	}

}
