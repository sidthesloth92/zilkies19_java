package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.RetrieveDetailsService;
import io.ztech.placementportal.services.UpdateStudentDetailService;

public class UpdateMarks {
	Logger log;
	ScanInput scanInput;

	public UpdateMarks() {
		log = Logger.getLogger("UpdateMarks.class");
		scanInput = new ScanInput();
	}

	public void getMarks(Marks mark) {
		int choice;
		char continueChoice = ' ';
		UpdateStudentDetailService updateService = new UpdateStudentDetailService();
		RetrieveDetailsService viewDetail = new RetrieveDetailsService();
		try {
			mark = viewDetail.viewMarkDetail(mark.getStudentId());
			log.info(mark.toString());
			do {
				log.info(ApplicationConstants.MARK_CHOICE);
				choice = Integer.parseInt(scanInput.getInput(Regex.NUMBER, ApplicationConstants.VALID_OPTION));
				switch (choice) {
				case 1:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.PERCENTAGE_X);
					mark.setMarkX(Float.parseFloat(scanInput.getInput(Regex.MARKS, ApplicationConstants.VALID_MARK)));
					break;
				case 2:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.PERCENTAGE_XII);
					mark.setMarkXII(Float.parseFloat(scanInput.getInput(Regex.MARKS, ApplicationConstants.VALID_MARK)));
					break;
				case 3:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.CGPA);
					mark.setCgpa(Float.parseFloat(scanInput.getInput(Regex.CGPA, ApplicationConstants.VALID_CGPA)));
					break;
				case 4:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.ARREAR_COUNT);
					mark.setArrearCount(Integer
							.parseInt(scanInput.getInput(Regex.ARREAR_COUNT, ApplicationConstants.VALID_ARREAR_COUNT)));
					break;
				default:
					log.info(ApplicationConstants.VALID_INPUT);
					break;
				}
				log.info(ApplicationConstants.CONTINUE_EDIT);
				continueChoice = scanInput.getInput(Regex.CHOICE, ApplicationConstants.VALID_CHOICE).charAt(0);

			} while (continueChoice == 'Y' || continueChoice == 'y');
			updateService.updateMarkDetail(mark);
			log.info(ApplicationConstants.UPDATED);
		} catch (NumberFormatException e) {
			log.info(ApplicationConstants.VALID_NUMBER);
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
			// TODO: handle exception
		}
	}

}
