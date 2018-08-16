package io.ztech.placementportal.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.AddDetailsService;

public class AddStudentDetails {
	Logger log = Logger.getLogger("UpdateDetails.class");
	Scanner scan = new Scanner(System.in);

	public void addDetails() {
		Student student = new Student();
		Marks mark = new Marks();
		AdminDashboard admin = new AdminDashboard();
		AddDetailsService add = new AddDetailsService();
		boolean isSuccess;
		log.info(ApplicationConstants.STUDENT_DETAILS);
		log.info(ApplicationConstants.STUDENT_ID);
		student.setStudent_id(getDetails(Regex.REGISTER_NO_REGEX, ApplicationConstants.VALIDATE_REGISTER_NO));
		log.info(ApplicationConstants.STUDENT_NAME);
		student.setName(getDetails(Regex.STUDENT_NAME, ApplicationConstants.VALID_NAME));
		log.info(ApplicationConstants.PERCENTAGE_X);
		mark.setMark_X(getMark(ApplicationConstants.VALIDATE_MARK));
		log.info(ApplicationConstants.PERCENTAGE_XII);
		mark.setMark_XII(getMark(ApplicationConstants.VALIDATE_MARK));
		log.info(ApplicationConstants.CGPA);
		mark.setCgpa(getMark(ApplicationConstants.VALIDATE_MARK));
		log.info(ApplicationConstants.ARREAR_COUNT);
		mark.setArrear_count((int) getMark(ApplicationConstants.VALIDATE_MARK));
		isSuccess = add.addStudentDetail(student, mark);
		if (isSuccess) {
			log.info(ApplicationConstants.UPADTED);
		} else {
			log.info(ApplicationConstants.ERROR);
		}
		admin.viewDashboard();

	}

	public String getDetails(String regex, String validate_msg) {
		String input;
		boolean isValid = false;
		input = scan.nextLine();
		do {
			isValid = ValidateInput.validateDetail(regex, input);
			if (isValid == true)
				return input;
			else
				log.info(validate_msg);
			input = scan.nextLine();
		} while (!isValid);
		return null;

	}

	public float getMark(String validate_msg) {
		float input;
		boolean isValid = false;
		input = scan.nextFloat();
		do {
			isValid = ValidateInput.validateMark(input);
			if (isValid == true)
				return input;
			else
				log.info(validate_msg);
			input = scan.nextFloat();
		} while (!isValid);
		return 0;

	}

}
