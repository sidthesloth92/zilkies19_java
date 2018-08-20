package com.zilker.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.Map.Entry;

import com.zilker.beans.LoggedInUserData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.RegexConstants;
import com.zilker.delegates.Validator;
import com.zilker.services.FacultyServices;

public class FacultyUI {

	public boolean proceed;
	public String getInput;
	public static Scanner in = new Scanner(System.in);
	private static final Logger LOGGER = Logger.getLogger(FacultyUI.class.getName());
	FacultyServices facultyService = new FacultyServices();

	public String scanInput(String displayMessage, String regex, String errorMessage) {
		LOGGER.info(displayMessage);
		proceed = true;
		do {
			getInput = in.nextLine();
			if (getInput.equals("-1")) {
				proceed = false;
				continue;
			}
			if (Validator.validate(getInput, regex)) {
				proceed = false;
			} else {
				LOGGER.info(errorMessage);
			}
		} while (proceed);
		return getInput;
	}

	public void viewResults(LoggedInUserData currentUser) {
		int option = 0,semester=0;
		do {
			option = Integer.parseInt(this.scanInput(StringConstants.FACULTY_VIEW_RESULT_MENU,
					RegexConstants.EDIT_MENU_REGEX, StringConstants.INVALID_INPUT));
			switch (option) {
			case 1:
				long registrationNumber=Long.parseLong(this.scanInput(StringConstants.ENTER_REGNO, RegexConstants.NUMERIC_REGEX, StringConstants.INVALID_INPUT));
				semester = Integer.parseInt(this.scanInput(StringConstants.ENTER_SEMESTER, RegexConstants.SEMESTER_REGEX,
						StringConstants.INVALID_INPUT));
				if (print(facultyService.getResultById(registrationNumber, semester,1))) {
					LOGGER.info(StringConstants.AFTER_REVAL);
					print(facultyService.checkRevaluationStatusById(registrationNumber,semester));
				}
				break;
			case 2:
				semester = Integer.parseInt(this.scanInput(StringConstants.ENTER_SEMESTER, RegexConstants.SEMESTER_REGEX,
						StringConstants.INVALID_INPUT));
				print(facultyService.getResultByDept(currentUser.getCollegeCode(), currentUser.getDepartment(), semester));
				break;
			case 3:
				break;
			}
		} while (option != 4);
		
		print(facultyService.getResultByDept(currentUser.getCollegeCode(), currentUser.getDepartment(), semester));
	}

	public void revaluationRequest(LoggedInUserData currentUser) {
		int revaluationId = 0, statusId = 0;
		String status = "";
		if (print(facultyService.checkRevaluationStatusBySubject(currentUser.getRegistrationNumber(),
				currentUser.getCollegeCode()))) {
			revaluationId = Integer.parseInt(this.scanInput(StringConstants.ENTER_REVALID, RegexConstants.NUMERIC_REGEX,
					StringConstants.INVALID_INPUT));
			LOGGER.info("");
			statusId = Integer
					.parseInt(this.scanInput(StringConstants.APPROVE_STATUS, "[1|2]", StringConstants.INVALID_INPUT));
			if (statusId == 1) {
				status = "Approved";
			} else if (statusId == 2) {
				status = "Rejected";
			}
			if (facultyService.changeStatusByFaculty(revaluationId, status)) {
				LOGGER.info(StringConstants.UPDATE_SUCCESS);
			} else {
				LOGGER.info(StringConstants.UPDATE_ERROR);
			}
		}
	}

	public static boolean print(ArrayList<LinkedHashMap<String, String>> result) {
		if (result.isEmpty()) {
			LOGGER.info(StringConstants.NO_RECORDS);
			return false;
		}
		for (LinkedHashMap<String, String> map : result) {
			LOGGER.info(StringConstants.STAR);
			for (Entry<String, String> pair : map.entrySet()) {
				LOGGER.info(pair.getKey() + pair.getValue());
			}
		}
		LOGGER.info(StringConstants.STAR);
		return true;
	}

	public void addFacultySubjectDetails(LoggedInUserData currentUser) {
		String subjectCode = "";
		if (print(facultyService.getSubjectDetailsById(currentUser.getRegistrationNumber(), 1))) {
			subjectCode = this.scanInput(StringConstants.ENTER_SUBJECT_CODE, RegexConstants.ALPHA_NUMERIC_REGEX,
					StringConstants.INVALID_INPUT);
			facultyService.insertFacultySubjectDetails(currentUser.getRegistrationNumber(), subjectCode);
		}

	}

	public void deleteFacultySubjectDetails(LoggedInUserData currentUser) {
		int facultySubjectId = 0;
		if (print(facultyService.getFacultySubjectDetailsById(currentUser.getRegistrationNumber()))) {
			facultySubjectId = Integer
					.parseInt(this.scanInput(StringConstants.ENTER_ID, "[1-9]+", StringConstants.INVALID_INPUT));
			if (facultyService.deleteFacultySubjectDetails(facultySubjectId)) {
				LOGGER.info(StringConstants.DELETE_SUCCESS);
			} else {
				LOGGER.info(StringConstants.DELETE_ERROR);
			}
		}
	}

	public void facultyMenu(LoggedInUserData currentUser) {
		LOGGER.info("FACULTY PORTAL: WELCOME " + currentUser.getName());
		int option = 0;
		do {
			option = Integer
					.parseInt(this.scanInput(StringConstants.FACULTY_MENU, "[1-6]", StringConstants.INVALID_INPUT));
			switch (option) {
			case 1:
				this.viewResults(currentUser);
				break;
			case 2:
				this.revaluationRequest(currentUser);
				break;
			case 3:
				break;
			case 4:
				this.addFacultySubjectDetails(currentUser);
				break;
			case 5:
				this.deleteFacultySubjectDetails(currentUser);
				break;
			case 6:
				break;
			}
		} while (option != 6);

	}
}
