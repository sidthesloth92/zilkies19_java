package com.zilker.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.Map.Entry;

import com.zilker.beans.LoggedInUserData;
import com.zilker.constants.DisplayConstants;
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
	
	public void viewResults(LoggedInUserData currentUser) {
		int semester=Integer.parseInt(this.scanInput(DisplayConstants.ENTER_SEMESTER, RegexConstants.SEMESTER_REGEX, DisplayConstants.INVALID_INPUT));
		print(facultyService.getResultByDept(currentUser.getCollegeCode(), currentUser.getDepartment(), semester));
	}
	
	public void revaluationRequest(LoggedInUserData currentUser) {
		int revaluationId=0,statusId=0;String status="";
		if(print(facultyService.checkRevaluationStatusBySubject(currentUser.getRegistrationNumber(),currentUser.getCollegeCode()))) {
			revaluationId=Integer.parseInt(this.scanInput(DisplayConstants.ENTER_REVALID, RegexConstants.INT_REGEX, DisplayConstants.INVALID_INPUT));
			LOGGER.info("");
			statusId=Integer.parseInt(this.scanInput(DisplayConstants.APPROVE_STATUS,"[1|2]", DisplayConstants.INVALID_INPUT));
			if(statusId==1) {
				status="Approved";
			}
			else if(statusId==2) {
				status="Rejected";
			}
			if(facultyService.changeStatusByFaculty(revaluationId, status)) {
				LOGGER.info(DisplayConstants.UPDATE_SUCCESS);
			}
			else {
				LOGGER.info(DisplayConstants.UPDATE_ERROR);
			}
		}
	}

	public static boolean print(ArrayList<LinkedHashMap<String, String>> result) {
		if (result.isEmpty()) {
			LOGGER.info(DisplayConstants.NO_RECORDS);
			return false;
		}
		for (LinkedHashMap<String, String> map : result) {
			LOGGER.info(DisplayConstants.STAR);
			for(Entry<String, String> pair : map.entrySet()) {
				LOGGER.info(pair.getKey() + pair.getValue());
			}
		}
		LOGGER.info(DisplayConstants.STAR);
		return true;
	}
	
	public void addFacultySubjectDetails(LoggedInUserData currentUser) {
		String subjectCode="";
		if(print(facultyService.getSubjectDetailsById(currentUser.getRegistrationNumber(),1))){
			subjectCode=this.scanInput(DisplayConstants.ENTER_SUBJECT_CODE, RegexConstants.ALPHA_NUMERIC_REGEX, DisplayConstants.INVALID_INPUT);
			facultyService.insertFacultySubjectDetails(currentUser.getRegistrationNumber(),subjectCode);
		}
		
	}
	
	public void deleteFacultySubjectDetails(LoggedInUserData currentUser) {
		int facultySubjectId=0;
		if(print(facultyService.getFacultySubjectDetailsById(currentUser.getRegistrationNumber()))) {
			facultySubjectId=Integer.parseInt(this.scanInput(DisplayConstants.ENTER_ID, "[1-9]+", DisplayConstants.INVALID_INPUT));
			if(facultyService.deleteFacultySubjectDetails(facultySubjectId)) {
				LOGGER.info(DisplayConstants.DELETE_SUCCESS);
			}
			else {
				LOGGER.info(DisplayConstants.DELETE_ERROR);
			}
		}
	}
	
	public void facultyMenu(LoggedInUserData currentUser) {
		LOGGER.info("FACULTY PORTAL: WELCOME "+currentUser.getName());
		int option = 0;
		do {
			option = Integer.parseInt(this.scanInput(DisplayConstants.FACULTY_MENU, "[1-6]",DisplayConstants.INVALID_INPUT ));		
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
		}while(option!=6);

	}
}
