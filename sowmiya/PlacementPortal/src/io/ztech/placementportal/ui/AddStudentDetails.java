package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.AddDetailsService;
import io.ztech.placementportal.services.RetrieveDetailsService;

public class AddStudentDetails {
	Logger log;
	ScanInput scanInput;
	AdminDashboard admin;

	public AddStudentDetails() {
		log = Logger.getLogger("UpdateDetails.class");
		scanInput = new ScanInput();
		admin = new AdminDashboard();

	}

	public void addDetails() {
		Student student = new Student();
		Marks mark = new Marks();
		AddDetailsService addStudentDetail = new AddDetailsService();
		log.info(ApplicationConstants.STUDENT_DETAILS);
		log.info(ApplicationConstants.ENTER + ApplicationConstants.STUDENT_ID);
		try {
			student.setStudentId(
					(isExists(scanInput.getInput(Regex.REGISTER_NO_REGEX, ApplicationConstants.VALID_REGISTER_NO),
							ApplicationConstants.REGISTER_NO)));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.STUDENT_NAME);
			student.setName(scanInput.getInput(Regex.STUDENT_NAME, ApplicationConstants.VALID_NAME));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.DEPARTMENT);
			student.setDepartment(scanInput.getInput(Regex.DEPARTMENT, ApplicationConstants.VALID_DEPARTMENT));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.PERCENTAGE_X);
			mark.setMarkX(Float.parseFloat(scanInput.getInput(Regex.MARKS, ApplicationConstants.VALID_MARK)));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.PERCENTAGE_XII);
			mark.setMarkXII(Float.parseFloat(scanInput.getInput(Regex.MARKS, ApplicationConstants.VALID_MARK)));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.CGPA);
			mark.setCgpa(Float.parseFloat(scanInput.getInput(Regex.CGPA, ApplicationConstants.VALID_CGPA)));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.ARREAR_COUNT);
			mark.setArrearCount(
					Integer.parseInt(scanInput.getInput(Regex.ARREAR_COUNT, ApplicationConstants.VALID_ARREAR_COUNT)));
			addStudentDetail.addStudentDetail(student, mark);
			log.info(ApplicationConstants.STUDENT_DETAIL_INSERTED);
			log.info(ApplicationConstants.SEPARATING_STRING);
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
			log.info(ApplicationConstants.SEPARATING_STRING);

		}

	}

	public String isExists(String input, String field) throws SQLException {
		RetrieveDetailsService retrieveDetail = new RetrieveDetailsService();
		if (retrieveDetail.checkIsAvailable(input, field)) {
			return input;
		} else {
			if (field.equals(ApplicationConstants.EMAIL_ID))
				log.info(ApplicationConstants.EMAIL_EXISTS);
			if (field.equals(ApplicationConstants.REGISTER_NO))
				log.info(ApplicationConstants.LOGIN_NOT_GENERATED);
			admin.viewDashboard();
		}

		return null;
	}

}
