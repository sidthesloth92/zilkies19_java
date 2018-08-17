package io.ztech.placementportal.ui;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.UpdateStudentDetailService;
import io.ztech.placementportal.services.ViewDetailsService;

public class UpdateDetails {
	Logger log = Logger.getLogger("UpdateDetails.class");
	Scanner scan = new Scanner(System.in);

	public void getNewDetail(Student studentDetail) {
		int choice;
		char continueChoice;
		ViewDetailsService viewDetail = new ViewDetailsService();
		UpdateStudentDetailService update = new UpdateStudentDetailService();
		HashMap<String, String> map = new LinkedHashMap<>();
		map = viewDetail.viewStudentDetail(studentDetail.getStudent_id());
		map.forEach((k, v) -> System.out.println(k + "" + v));
		do {
			log.info(ApplicationConstants.EDIT_CHOICE);
			choice = scan.nextInt();
			scan.nextLine();
			switch (choice) {
			case 1:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.NAME);
				studentDetail.setName(scan.nextLine());
				break;
			case 2:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.DEPARTMENT);
				studentDetail.setDepartment(scan.nextLine());
				break;
			default:
				log.info(ApplicationConstants.VALID_INPUT);
				break;
			}
			log.info(ApplicationConstants.CONTINUE_EDIT);
			continueChoice = scan.nextLine().charAt(0);

		} while (continueChoice == 'Y' || continueChoice == 'y');
		if (update.updateDetail(studentDetail))
			log.info(ApplicationConstants.UPADTED);
		else
			log.info(ApplicationConstants.ERROR);

	}
}
