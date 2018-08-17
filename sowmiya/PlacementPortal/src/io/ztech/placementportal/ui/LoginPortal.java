package io.ztech.placementportal.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.Driver;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.LoginServices;

public class LoginPortal {
	public Logger log = Logger.getLogger("LoginPortal");
	public static Scanner scan = new Scanner(System.in);
	public static String active_user, reg_no;

	public void loginDetails() {
		int role;
		String success;
		Register login = new Register();
		LoginServices loginService = new LoginServices();
		log.info(ApplicationConstants.USERNAME);
		login.setUserName(scan.nextLine());
		log.info(ApplicationConstants.PASSWORD);
		login.setPassword(scan.nextLine());
		log.info(ApplicationConstants.ROLE);
		role = scan.nextInt();
		if (role == 1)
			login.setRole("admin");
		if (role == 2)
			login.setRole("student");
		success = loginService.login(login);
		if (success != null && login.getRole() == "admin") {
			active_user = login.getUserName();
			AdminDashboard admin = new AdminDashboard();
			admin.viewDashboard();
		} else if (success != null && login.getRole() == "student") {
			StudentDashboard student = new StudentDashboard();
			login.setReg_no(success);
			student.viewDashboard(login);
		} else {
			Driver.main(null);

		}

	}

}
