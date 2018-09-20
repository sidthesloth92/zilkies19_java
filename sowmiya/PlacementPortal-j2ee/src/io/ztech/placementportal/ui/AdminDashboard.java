package io.ztech.placementportal.ui;

import java.util.logging.Logger;

import io.ztech.placementportal.HomePage;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;

public class AdminDashboard {
	private Logger log;
	private ScanInput scan;

	public AdminDashboard() {
		log = Logger.getLogger("AdminDashboard.class");
		scan = new ScanInput();

	}

	public void viewDashboard() {
		int option;
		char continueChoice = ' ';
		do {
			try {
				log.info(ApplicationConstants.ADMIN_DASHBOARD);
				option = Integer.parseInt(scan.getInput(Regex.NUMBER, ApplicationConstants.VALID_OPTION));
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
					UpdateStudentDetail updateStudent = new UpdateStudentDetail();
					updateStudent.chooseCategory();
					break;
				case 4:
					AddCompanyDetails company = new AddCompanyDetails();
					company.addCompanyDetail();
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
					HomePage.main(null);
					break;
				default:
					log.info(ApplicationConstants.WRONG_CHOICE);
				}
			} catch (NumberFormatException e) {
				log.info(ApplicationConstants.VALID_NUMBER);
			}
			log.info(ApplicationConstants.CONTINUE_CHOICE);
			continueChoice = scan.getInput(Regex.CHOICE, ApplicationConstants.VALID_CHOICE).charAt(0);

		} while (continueChoice == 'Y' || continueChoice == 'y');
		log.info(ApplicationConstants.THANKYOU);

	}

}
