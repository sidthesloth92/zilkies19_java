package com.zilker.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

import com.zilker.delegates.FacultyDelegator;

public class FacultyServices {
	private final Logger LOGGER = Logger.getLogger(FacultyServices.class.getName());
	FacultyDelegator facultyDelegate=new FacultyDelegator();
	
	public ArrayList<LinkedHashMap<String, String>>checkRevaluationStatusBySubject(long RegistrationNumber,int collegeCode){
		try {
			return facultyDelegate.checkRevaluationStatusBySubject(RegistrationNumber,collegeCode);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return null;
		
	} 
	
	public boolean changeStatusByFaculty(int revalId, String status) {
		try {
			return facultyDelegate.changeStatusByFaculty(revalId, status);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return false;
	}
	
	public ArrayList<LinkedHashMap<String, String>> getResultByDept(int collegeCode, String dept, int semester) {
		try {
			return facultyDelegate.getResultByDept(collegeCode, dept, semester);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return null;
	}
	
	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(long facultyRegistrationNumber,int flag){
		try {
			return facultyDelegate.getSubjectDetailsById(facultyRegistrationNumber,flag);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return null;
	}
	
	public boolean insertFacultySubjectDetails(long facultyRegistrationNumber, String subjectCode) {
		try {
			return facultyDelegate.insertFacultySubjectDetails(facultyRegistrationNumber,subjectCode);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return false;
	}
	
	public boolean deleteFacultySubjectDetails(int facultySubjectId) {
		try {
			return facultyDelegate.deleteFacultySubjectDetails(facultySubjectId);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return false;
	}
	
	public ArrayList<LinkedHashMap<String, String>> getFacultySubjectDetailsById(long facultyRegistrationNumber){
		try {
			return facultyDelegate.getFacultySubjectDetailsById(facultyRegistrationNumber);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return null;
	}
	
	public ArrayList<LinkedHashMap<String, String>> getResultById(long studentRegistrationNumber, int semester,
			int flag) {
		try {
			return facultyDelegate.getResultById(studentRegistrationNumber, semester, flag);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return null;
	}
	
	public ArrayList<LinkedHashMap<String, String>> checkRevaluationStatusById(long studentRegistrationNumber,
			int semester) {
		try {
			return facultyDelegate.checkRevaluationStatusById(studentRegistrationNumber, semester);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return null;
	}
}
