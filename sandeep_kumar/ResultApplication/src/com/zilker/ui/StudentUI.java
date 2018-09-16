package com.zilker.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.delegates.Validator;
import com.zilker.services.StudentServices;
import com.zilker.beans.*;
import com.zilker.constants.StringConstants;
import com.zilker.constants.RegexConstants;

public class StudentUI {
	public static Scanner in = new Scanner(System.in);
	private static final Logger LOGGER = Logger.getLogger(StudentUI.class.getName());
	public boolean proceed;
	public String getInput;
	StudentServices studentService = new StudentServices();

	public String scanInput(String displayMessage, String regex, String errorMessage) {
		LOGGER.info(displayMessage);
		proceed = true;
		do {
			getInput = in.nextLine();
			if(getInput.equals("-1")) {
				proceed = false;continue;
			}
			if (Validator.validate(getInput, regex)) {
				proceed = false;
			} else {
				LOGGER.info(errorMessage);
			}
		} while (proceed);
		return getInput;
	}

	public void viewResult(LoggedInUserData currentUser) {
		int semester = 0;
		semester = Integer.parseInt(this.scanInput(StringConstants.ENTER_SEMESTER, RegexConstants.SEMESTER_REGEX, StringConstants.INVALID_INPUT));
		if (print(studentService.getResultById(currentUser.getRegistrationNumber(), semester,1))) {
			LOGGER.info(StringConstants.AFTER_REVAL);
			print(studentService.checkRevaluationStatusById(currentUser.getRegistrationNumber(),currentUser.getSemester()-1));
		}

	}

	public void applyRevaluation(LoggedInUserData currentUser) {
		int resultId = 0;
		if (print(studentService.getResultById(currentUser.getRegistrationNumber(), currentUser.getSemester()-1,2))) {
			resultId = Integer.parseInt(this.scanInput(StringConstants.RESULT_ID, RegexConstants.NUMERIC_REGEX, StringConstants.INVALID_INPUT));
			if (studentService.applyRevaluation(resultId,currentUser)) {
				LOGGER.info(StringConstants.APPLY_SUCCESS);
			} else {
				LOGGER.info(StringConstants.LIMIT_EXCEED);
			}
		}
	}

	public void checkRevaluationStatus(LoggedInUserData currentUser) {
		print(studentService.checkRevaluationStatusById(currentUser.getRegistrationNumber(), currentUser.getSemester() - 1));
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

	public void studentMenu(LoggedInUserData currentUser) {
		LOGGER.info("STUDENT PORTAL: WELCOME " + currentUser.getName());
		int option = 0;
		do {
			option = Integer.parseInt(this.scanInput(StringConstants.STUDENT_MENU, "[1-4]", StringConstants.INVALID_INPUT));
			switch (option) {
			case 1:
				this.viewResult(currentUser);
				break;
			case 2:
				this.applyRevaluation(currentUser);
				break;
			case 3:
				this.checkRevaluationStatus(currentUser);
				break;
			case 4:
				break;
			}
		} while (option != 4);

	}
}