package io.ztech.placementportal.ui;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.UpdateStudentDetailService;
import io.ztech.placementportal.services.ViewDetailsService;

public class UpdateMarks {
	Logger log = Logger.getLogger("UpdateMarks.class");
	Scanner scan = new Scanner(System.in);

	public void getMarks(Marks mark) {
		int choice;
		char continueChoice = ' ';
		UpdateStudentDetailService updateService = new UpdateStudentDetailService();
		ViewDetailsService viewDetail = new ViewDetailsService();
		HashMap<String, Float> markMap = new LinkedHashMap<>();
		markMap = viewDetail.viewMarkDetail(mark.getStudent_id());
		markMap.forEach((k, v) -> System.out.println(k + "" + v));
		mark.setMark_X(markMap.get(ApplicationConstants.PERCENTAGE_X));
		mark.setMark_XII(markMap.get(ApplicationConstants.PERCENTAGE_XII));
		mark.setCgpa(markMap.get(ApplicationConstants.CGPA));
		mark.setArrear_count(markMap.get(ApplicationConstants.ARREAR_COUNT).intValue());
		do {
			log.info(ApplicationConstants.MARK_CHOICE);
			choice = scan.nextInt();
			switch (choice) {
			case 1:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.PERCENTAGE_X);
				mark.setMark_X(scan.nextFloat());
				break;
			case 2:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.PERCENTAGE_XII);
				mark.setMark_XII(scan.nextFloat());
				break;
			case 3:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.CGPA);
				mark.setCgpa(scan.nextFloat());
				break;
			case 4:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.ARREAR_COUNT);
				mark.setArrear_count(scan.nextInt());
				break;
			default:
				log.info(ApplicationConstants.VALID_INPUT);
				break;
			}
			log.info(ApplicationConstants.CONTINUE_EDIT);
			scan.nextLine();
			continueChoice = scan.nextLine().charAt(0);

		} while (continueChoice == 'Y' || continueChoice == 'y');
		if (updateService.updateMarkDetail(mark))
			log.info(ApplicationConstants.UPADTED);
		else
			log.info(ApplicationConstants.ERROR);

	}

}
