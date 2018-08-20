package com.zilker.delegates;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.beans.LoggedInUserData;
import com.zilker.dao.*;

public class StudentDelegator{
	ResultDetailsDao resultDetailsDao =new ResultDetailsDao();
	RevaluationDetailsDao revaluationDetailsDao =new RevaluationDetailsDao();
	
	public ArrayList<LinkedHashMap<String, String>> getResultById(long studentRegistrationNumber, int semester,int flag) {
		return resultDetailsDao.getResultById(studentRegistrationNumber, semester,flag);
	}
	
	public boolean applyRevaluation(int resultId,LoggedInUserData currentUser) {
		if(revaluationDetailsDao.findRevaluationCount(currentUser)<=4) {
			return revaluationDetailsDao.applyRevaluation(resultId);
		}
		return false;
	}
	
	public ArrayList<LinkedHashMap<String, String>> checkRevaluationStatusById(long studentRegistrationNumber, int semester) {
		return revaluationDetailsDao.checkRevaluationStatusById(studentRegistrationNumber, semester);
	}
}
