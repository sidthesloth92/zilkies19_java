package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;

public class UpdateStudentDetail extends AddStudentDetails {
	Logger log;
	ScanInput scanInput;

	public UpdateStudentDetail() {
		log = Logger.getLogger("UpdateStudentDetail.class");
		scanInput = new ScanInput();
	}

	public void chooseCategory() {
		int choice;
		char continueChoice = ' ';
		String id;
		try {
			log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.STUDENT_ID);
			id = isExists(scanInput.getInput(Regex.REGISTER_NO_REGEX, ApplicationConstants.VALID_REGISTER_NO),
					ApplicationConstants.REGISTER_NO);

			do {
				log.info(ApplicationConstants.CATEGORY);
				choice = Integer.parseInt(scanInput.getInput(Regex.NUMBER, ApplicationConstants.VALID_OPTION));
				switch (choice) {
				case 1:
					Student studentDetail = new Student();
					UpdateDetails updateDetail = new UpdateDetails();
					studentDetail.setStudentId(id);
					updateDetail.getNewDetail(studentDetail);
					break;
				case 2:
					Marks mark = new Marks();
					UpdateMarks updateMark = new UpdateMarks();
					mark.setStudentId(id);
					updateMark.getMarks(mark);
					break;
				case 3:
					PlacedDetail student = new PlacedDetail();
					UpdatePlacementStatus update = new UpdatePlacementStatus();
					student.setStudentId(id);
					update.getPlacementDetail(student);
					break;

				default:
					log.info(ApplicationConstants.VALID_INPUT);
					break;
				}
				log.info(ApplicationConstants.CONTINUE_EDIT);
				continueChoice = scanInput.getInput(Regex.CHOICE, ApplicationConstants.VALID_CHOICE).charAt(0);
			} while (continueChoice == 'Y' || continueChoice == 'y');
		} catch (NumberFormatException e) {
			log.info(ApplicationConstants.VALID_NUMBER);
			log.info(ApplicationConstants.SEPARATING_STRING);
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
			log.info(ApplicationConstants.SEPARATING_STRING);

		}

	}

}
