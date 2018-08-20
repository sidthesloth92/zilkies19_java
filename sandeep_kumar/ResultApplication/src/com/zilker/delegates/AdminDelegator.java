package com.zilker.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.beans.*;
import com.zilker.dao.*;

public class AdminDelegator {
	LoginDetailsDao loginDetailsDao = new LoginDetailsDao();
	StudentDetailsDao studentDetailsDao = new StudentDetailsDao();
	FacultyDetailsDao facultyDetailsDao = new FacultyDetailsDao();
	SubjectDetailsDao subjectDetailsDao = new SubjectDetailsDao();
	CollegeDetailsDao collegeDetailsDao = new CollegeDetailsDao();
	ResultDetailsDao resultDetailsDao = new ResultDetailsDao();
	RevaluationDetailsDao revaluationDetailsDao = new RevaluationDetailsDao();

	public boolean addStudentDetails(StudentData obj) throws SQLException {
		return studentDetailsDao.insertStudentDetails(obj);
	}

	public boolean registerStudent(StudentData obj) throws SQLException {
		return loginDetailsDao.registerStudent(obj);
	}

	public boolean addFacultyDetails(FacultyData obj) throws SQLException {
		return facultyDetailsDao.insertFacultyDetails(obj);
	}

	public boolean registerFaculty(FacultyData obj) throws SQLException {
		return loginDetailsDao.registerFaculty(obj);
	}

	public boolean addSubjectDetails(SubjectData obj) throws SQLException {
		return subjectDetailsDao.insertSubjectDetails(obj);
	}

	public boolean addCollegeDetails(CollegeData obj) throws SQLException {
		return collegeDetailsDao.insertCollegeDetails(obj);
	}

	public boolean addResults(ResultData obj) throws SQLException {
		return resultDetailsDao.insertResults(obj);
	}

	public boolean deleteStudentDetails(long studentRegistrationNumber) throws SQLException {
		return studentDetailsDao.deleteStudentDetails(studentRegistrationNumber);
	}

	public boolean deleteFacultyDetails(long facultyRegistrationNumber) throws SQLException {
		return facultyDetailsDao.deleteFacultyDetails(facultyRegistrationNumber);
	}

	public boolean deleteSubjectDetails(String subjectCode) throws SQLException {
		return subjectDetailsDao.deleteSubjectDetails(subjectCode);
	}

	public boolean deleteCollegeDetails(int collegeCode) throws SQLException {
		return collegeDetailsDao.deleteCollegeDetails(collegeCode);
	}

	public boolean updateStudentDetails(long studentRegistrationNumber, String dataToValidate, int column, int flag)
			throws SQLException {
		return (studentDetailsDao.updateStudentDetails(studentRegistrationNumber, dataToValidate, column, flag));
	}

	public boolean updateFacultyDetails(long facultyRegistrationNumber, String dataToValidate, int column, int flag)
			throws SQLException {
		return (facultyDetailsDao.updateFacultyDetails(facultyRegistrationNumber, dataToValidate, column, flag));
	}

	public ArrayList<LinkedHashMap<String, String>> getApprovedRevaluationList() throws SQLException {
		return revaluationDetailsDao.getApprovedRevaluationList();
	}

	public boolean updateGrade(int revalId, char grade) throws SQLException {
		String status = "No Change";
		char currentGrade;
		currentGrade = revaluationDetailsDao.getCurrentGrade(revalId);
		if (currentGrade == 'o') {
			return false;
		} else if ((int) grade < (int) currentGrade) {
			status = "Change";
		}
		else {
			grade=currentGrade;
		}
		return revaluationDetailsDao.updateGrade(revalId, Character.toString(grade), status);

	}

	public ArrayList<LinkedHashMap<String, String>> getAllStudentDetails() throws SQLException {
		return studentDetailsDao.getAllStudentDetails();
	}

	public ArrayList<LinkedHashMap<String, String>> getAllFacultyDetails() throws SQLException {
		return facultyDetailsDao.getAllFacultyDetails();
	}

	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(int regno, int flag) throws SQLException {
		return subjectDetailsDao.getSubjectDetailsById(regno, flag);
	}

	public ArrayList<LinkedHashMap<String, String>> getAllCollegeDetails() throws SQLException {
		return collegeDetailsDao.getAllCollegeDetails();
	}
}
