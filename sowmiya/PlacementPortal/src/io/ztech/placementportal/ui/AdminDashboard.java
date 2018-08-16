package io.ztech.placementportal.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.Driver;
import io.ztech.placementportal.constants.ApplicationConstants;

public class AdminDashboard {
	Logger log = Logger.getLogger("AdminDashboard.class");
	Scanner scan = new Scanner(System.in);

	public void viewDashboard() {
		int option;
		char continueChoice = ' ';
		do {
			log.info(ApplicationConstants.ADMIN_DASHBOARD);
			option = scan.nextInt();
			switch (option) {
			case 1:
				GenerateLoginPage g_login = new GenerateLoginPage();
				g_login.getRegistrationDetail();
				break;
			case 2:
				AddStudentDetails details = new AddStudentDetails();
				details.addDetails();
				break;
			case 3:
				AddCompanyDetails company = new AddCompanyDetails();
				company.addCompanyDetail();
				break;
			case 4:
				UpdateStudentDetail updateStudent = new UpdateStudentDetail();
				updateStudent.chooseCategory();
				break;

			case 5:
				UpdateCompanyDetail updateCompany = new UpdateCompanyDetail();
				updateCompany.getNewDetail();
				break;
			case 6:
				ViewEligiblityList viewEligible = new ViewEligiblityList();
				viewEligible.viewEligibleList();
				break;
			case 7:
				LoginPortal.active_user = null;
				Driver.main(null);
				break;
			default:
				log.info(ApplicationConstants.WRONG_CHOICE);
			}
			log.info(ApplicationConstants.CONTINUE_CHOICE);
			continueChoice = scan.next().charAt(0);

		} while (continueChoice == 'Y' || continueChoice == 'y');
		log.info(ApplicationConstants.THANKYOU);

	}

}
