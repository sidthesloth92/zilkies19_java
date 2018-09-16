package com.zilker.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.beans.LoggedInUserData;
import com.zilker.dao.*;

public class StudentDelegator {
	ResultDetailsDao resultDetailsDao = new ResultDetailsDao();
	RevaluationDetailsDao revaluationDetailsDao = new RevaluationDetailsDao();

	public ArrayList<LinkedHashMap<String, String>> getResultById(long studentRegistrationNumber, int semester,
			int flag) throws SQLException {
		return resultDetailsDao.getResultById(studentRegistrationNumber, semester, flag);
	}

	public boolean applyRevaluation(int resultId, LoggedInUserData currentUser) throws SQLException {
		if (revaluationDetailsDao.findRevaluationCount(currentUser) <= 4) {
			return revaluationDetailsDao.applyRevaluation(resultId);
		}
		return false;
	}

	public ArrayList<LinkedHashMap<String, String>> checkRevaluationStatusById(long studentRegistrationNumber,
			int semester) throws SQLException {
		return revaluationDetailsDao.checkRevaluationStatusById(studentRegistrationNumber, semester);
	}
}
