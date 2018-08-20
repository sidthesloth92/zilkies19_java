package com.zilker.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.dao.*;
public class FacultyDelegator{
	RevaluationDetailsDao revaluationDetailsDao = new RevaluationDetailsDao();
	ResultDetailsDao resultDetailsDao = new ResultDetailsDao();
	SubjectDetailsDao subjectDetailsDao = new SubjectDetailsDao();
	FacultySubjectDetailsDao facultySubjectDetailsDao = new FacultySubjectDetailsDao();

	public ArrayList<LinkedHashMap<String, String>> checkRevaluationStatusBySubject(long RegistrationNumber,
			int collegeCode) throws SQLException{
		return revaluationDetailsDao.checkRevaluationStatusBySubject(RegistrationNumber, collegeCode);
	}

	public boolean changeStatusByFaculty(int revalId, String status) throws SQLException{
		return revaluationDetailsDao.changeStatusbyFaculty(revalId, status);
	}

	public ArrayList<LinkedHashMap<String, String>> getResultByDept(int collegeCode, String dept, int semester) throws SQLException{
		return resultDetailsDao.getResultByDept(collegeCode, dept, semester);
	}

	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(long facultyRegistrationNumber,int flag) throws SQLException{
		return subjectDetailsDao.getSubjectDetailsById(facultyRegistrationNumber,flag);
	}

	public boolean insertFacultySubjectDetails(long facultyRegistrationNumber, String subjectCode) throws SQLException{
		return facultySubjectDetailsDao.insertFacultySubjectDetails(facultyRegistrationNumber,subjectCode);
	}

	public boolean deleteFacultySubjectDetails(int facultySubjectId) throws SQLException{
		return facultySubjectDetailsDao.deleteFacultySubjectDetails(facultySubjectId);
	}
	
	public ArrayList<LinkedHashMap<String, String>> getFacultySubjectDetailsById(long facultyRegistrationNumber) throws SQLException{
		return facultySubjectDetailsDao.getFacultySubjectDetailsById(facultyRegistrationNumber);
	}
	
	public ArrayList<LinkedHashMap<String, String>> getResultById(long studentRegistrationNumber, int semester,
			int flag) throws SQLException {
		return resultDetailsDao.getResultById(studentRegistrationNumber, semester, flag);
	}
	
	public ArrayList<LinkedHashMap<String, String>> checkRevaluationStatusById(long studentRegistrationNumber,
			int semester) throws SQLException {
		return revaluationDetailsDao.checkRevaluationStatusById(studentRegistrationNumber, semester);
	}
}
