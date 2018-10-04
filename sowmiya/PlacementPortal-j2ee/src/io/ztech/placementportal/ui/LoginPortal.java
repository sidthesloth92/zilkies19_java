package io.ztech.placementportal.ui;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

import io.ztech.placementportal.HomePage;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.LoginServices;

public class LoginPortal {
	private Logger log;
	public static String active_user, reg_no;

	public LoginPortal() {
		log = Logger.getLogger("LoginPortal");
	}

	public void loginDetails() {
		String success;
		Register login = new Register();
		ScanInput scan = new ScanInput();
		Date date = new Date();
		LoginServices loginService = new LoginServices();
		log.info(ApplicationConstants.USERNAME);
		login.setUserName(scan.getInput(Regex.USERNAME_REGEX, ApplicationConstants.VALID_USERNAME));
		log.info(ApplicationConstants.PASSWORD);
		login.setPassword(scan.getInput(Regex.PASSWORD_REGEX, ApplicationConstants.VALID_PASSWORD));
		login.setTime(new Timestamp(date.getTime()));
		log.info(ApplicationConstants.SEPARATING_STRING);
		try {
			success = loginService.login(login);
			if (success.equals("admin")) {
				active_user = login.getUserName();
				AdminDashboard admin = new AdminDashboard();
				admin.viewDashboard();
			} else if (success.equals("student")) {
				reg_no = login.getUserName();
				StudentDashboard student = new StudentDashboard();
				student.viewDashboard(login);
			} else {
				log.info(ApplicationConstants.INVALID_CREDENTIALS);
				log.info(ApplicationConstants.SEPARATING_STRING);
				HomePage.main(null);

			}
		} catch (Exception e) {
			log.warning(e.getMessage());
		}

	}

}
