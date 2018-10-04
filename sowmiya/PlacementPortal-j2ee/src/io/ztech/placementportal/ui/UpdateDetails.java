package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.RetrieveDetailsService;
import io.ztech.placementportal.services.UpdateStudentDetailService;

public class UpdateDetails {
	Logger log;
	ScanInput scan;

	public UpdateDetails() {
		log = Logger.getLogger("UpdateDetails.class");
		scan = new ScanInput();
	}

	public void getNewDetail(Student studentDetail) {
		int choice;
		char continueChoice;
		RetrieveDetailsService viewDetail = new RetrieveDetailsService();
		UpdateStudentDetailService update = new UpdateStudentDetailService();
		try {
			log.info(viewDetail.viewStudentDetail(studentDetail.getStudentId()).toString());
			do {
				log.info(ApplicationConstants.EDIT_CHOICE);
				choice = Integer.parseInt(scan.getInput(Regex.NUMBER, ApplicationConstants.VALID_OPTION));
				switch (choice) {
				case 1:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.NAME);
					studentDetail.setName(scan.getInput(Regex.STUDENT_NAME, ApplicationConstants.VALID_NAME));
					break;
				case 2:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.DEPARTMENT);
					studentDetail.setDepartment(scan.getInput(Regex.DEPARTMENT, ApplicationConstants.VALID_DEPARTMENT));
					break;
				default:
					log.info(ApplicationConstants.VALID_INPUT);
					break;
				}
				log.info(ApplicationConstants.CONTINUE_EDIT);
				continueChoice = scan.getInput(Regex.CHOICE, ApplicationConstants.VALID_CHOICE).charAt(0);

			} while (continueChoice == 'Y' || continueChoice == 'y');
			update.updateDetail(studentDetail);
			log.info(ApplicationConstants.UPDATED);
			log.info(ApplicationConstants.SEPARATING_STRING);

		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
			log.info(ApplicationConstants.SEPARATING_STRING);
		} catch (NumberFormatException e) {
			log.info(ApplicationConstants.VALID_NUMBER);
			log.info(ApplicationConstants.SEPARATING_STRING);
		}

	}
}
