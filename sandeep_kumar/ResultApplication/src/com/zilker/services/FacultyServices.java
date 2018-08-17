package com.zilker.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.delegates.FacultyDelegator;

public class FacultyServices {
	
	FacultyDelegator facultyDelegate=new FacultyDelegator();
	
	public ArrayList<LinkedHashMap<String, String>>checkRevaluationStatusBySubject(long RegistrationNumber,int collegeCode){
		return facultyDelegate.checkRevaluationStatusBySubject(RegistrationNumber,collegeCode);
	} 
	
	public boolean changeStatusByFaculty(int revalId, String status) {
		return facultyDelegate.changeStatusByFaculty(revalId, status);
	}
	
	public ArrayList<LinkedHashMap<String, String>> getResultByDept(int collegeCode, String dept, int semester) {
		return facultyDelegate.getResultByDept(collegeCode, dept, semester);
	}
	
	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(long facultyRegistrationNumber,int flag){
		return facultyDelegate.getSubjectDetailsById(facultyRegistrationNumber,flag);
	}
	
	public boolean insertFacultySubjectDetails(long facultyRegistrationNumber, String subjectCode) {
		return facultyDelegate.insertFacultySubjectDetails(facultyRegistrationNumber,subjectCode);
	}
	
	public boolean deleteFacultySubjectDetails(int facultySubjectId) {
		return facultyDelegate.deleteFacultySubjectDetails(facultySubjectId);
	}
	
	public ArrayList<LinkedHashMap<String, String>> getFacultySubjectDetailsById(long facultyRegistrationNumber){
		return facultyDelegate.getFacultySubjectDetailsById(facultyRegistrationNumber);
	}
}
