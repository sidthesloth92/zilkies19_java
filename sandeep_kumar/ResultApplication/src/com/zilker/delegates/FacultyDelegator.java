package com.zilker.delegates;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.dao.*;
public class FacultyDelegator {
	RevaluationDetailsDao revaluationDetailsDao = new RevaluationDetailsDao();
	ResultDetailsDao resultDetailsDao = new ResultDetailsDao();
	SubjectDetailsDao subjectDetailsDao = new SubjectDetailsDao();
	FacultySubjectDetailsDao facultySubjectDetailsDao = new FacultySubjectDetailsDao();

	public ArrayList<LinkedHashMap<String, String>> checkRevaluationStatusBySubject(long RegistrationNumber,int collegeCode) {
		return revaluationDetailsDao.checkRevaluationStatusBySubject(RegistrationNumber, collegeCode);
	}

	public boolean changeStatusByFaculty(int revalId, String status) {
		return revaluationDetailsDao.changeStatusbyFaculty(revalId, status);
	}

	public ArrayList<LinkedHashMap<String, String>> getResultByDept(int collegeCode, String dept, int semester) {
		return resultDetailsDao.getResultByDept(collegeCode, dept, semester);
	}

	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(long facultyRegistrationNumber,int flag) {
		return subjectDetailsDao.getSubjectDetailsById(facultyRegistrationNumber,flag);
	}

	public boolean insertFacultySubjectDetails(long facultyRegistrationNumber, String subjectCode) {
		return facultySubjectDetailsDao.insertFacultySubjectDetails(facultyRegistrationNumber,subjectCode);
	}

	public boolean deleteFacultySubjectDetails(int facultySubjectId) {
		return facultySubjectDetailsDao.deleteFacultySubjectDetails(facultySubjectId);
	}
	
	public ArrayList<LinkedHashMap<String, String>> getFacultySubjectDetailsById(long facultyRegistrationNumber) {
		return facultySubjectDetailsDao.getFacultySubjectDetailsById(facultyRegistrationNumber);
	}
}
