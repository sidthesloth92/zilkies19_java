package com.zilker.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.beans.LoggedInUserData;
import com.zilker.delegates.StudentDelegator;

public class StudentServices {
	
	StudentDelegator studentDelegate=new StudentDelegator();
	
	public ArrayList<LinkedHashMap<String, String>> getResultById(long studentRegistrationNumber, int semester,int flag) {
		return studentDelegate.getResultById(studentRegistrationNumber, semester,flag);
	}
	
	public boolean applyRevaluation(int resultId,LoggedInUserData currentUser) {
		return studentDelegate.applyRevaluation(resultId,currentUser);
	}
	
	public ArrayList<LinkedHashMap<String, String>> checkRevaluationStatusById(long studentRegistrationNumber, int semester) {
		return studentDelegate.checkRevaluationStatusById(studentRegistrationNumber, semester);
	}
}
