package com.zilker.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

import com.zilker.beans.LoggedInUserData;
import com.zilker.delegates.StudentDelegator;

public class StudentServices {
	private final Logger LOGGER = Logger.getLogger(StudentServices.class.getName());
	StudentDelegator studentDelegate = new StudentDelegator();

	public ArrayList<LinkedHashMap<String, String>> getResultById(long studentRegistrationNumber, int semester,
			int flag) {
		try {
			return studentDelegate.getResultById(studentRegistrationNumber, semester, flag);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return null;
	}

	public boolean applyRevaluation(int resultId, LoggedInUserData currentUser) {
		try {
			return studentDelegate.applyRevaluation(resultId, currentUser);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return false;
	}

	public ArrayList<LinkedHashMap<String, String>> checkRevaluationStatusById(long studentRegistrationNumber,
			int semester) {
		try {
			return studentDelegate.checkRevaluationStatusById(studentRegistrationNumber, semester);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return null;
	}
}
