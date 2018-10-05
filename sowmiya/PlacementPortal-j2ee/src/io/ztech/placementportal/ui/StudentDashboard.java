package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.HomePage;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.RetrieveDetailsService;

public class StudentDashboard {
	Logger log;
	Scanner scan;

	public StudentDashboard() {
		log = Logger.getLogger("StudentDashboard.class");
		scan = new Scanner(System.in);
	}

	public void viewDashboard(Register login) {
		int option;
		char continueChoice = ' ';
		try {
			showDetails(login);
			do {
				log.info(ApplicationConstants.STUDENT_DASHBOARD);
				option = scan.nextInt();
				switch (option) {
				case 1:
					Profile profile = new Profile();
					profile.buildProfile(login);
					break;
				case 2:
					JobDetail job = new JobDetail();
					job.viewJobs();
					break;

				case 3:
					break;
				case 4:
					HomePage.main(null);
					break;
				default:
					log.info(ApplicationConstants.WRONG_CHOICE);
				}
				log.info(ApplicationConstants.CONTINUE_CHOICE);
				continueChoice = scan.next().charAt(0);

			} while (continueChoice == 'Y');
			log.info(ApplicationConstants.THANKYOU);
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
		}
	}

	public void showDetails(Register login) throws SQLException {
		RetrieveDetailsService viewDetail = new RetrieveDetailsService();
		log.info(viewDetail.viewStudentDetail(login.getUserName()).toString());
		log.info(viewDetail.viewMarkDetail(login.getUserName()).toString());
	}

}
