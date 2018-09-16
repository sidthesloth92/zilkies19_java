package com.zilker.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

import com.zilker.beans.*;
import com.zilker.delegates.AdminDelegator;

public class AdminServices {
	AdminDelegator adminDelegate = new AdminDelegator();
	private final Logger LOGGER = Logger.getLogger(AdminServices.class.getName());

	public boolean addStudentDetails(StudentData obj) {
		try {
			return (adminDelegate.addStudentDetails(obj) && adminDelegate.registerStudent(obj));
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public boolean addFacultyDetails(FacultyData obj) {
		try {
			return (adminDelegate.addFacultyDetails(obj) && adminDelegate.registerFaculty(obj));
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public boolean addSubjectDetails(SubjectData obj) {
		try {
			return adminDelegate.addSubjectDetails(obj);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public boolean addCollegeDetails(CollegeData obj) {
		try {
			return adminDelegate.addCollegeDetails(obj);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public boolean deleteStudentDetails(long studentRegistrationNumber) {
		try {
			return (adminDelegate.deleteStudentDetails(studentRegistrationNumber));
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public boolean deleteFacultyDetails(long facultyRegistrationNumber) {
		try {
			return (adminDelegate.deleteFacultyDetails(facultyRegistrationNumber));
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public boolean deleteSubjectDetails(String subjectCode) {
		try {
			return adminDelegate.deleteSubjectDetails(subjectCode);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public boolean deleteCollegeDetails(int collegeCode) {
		try {
			return adminDelegate.deleteCollegeDetails(collegeCode);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public boolean updateStudentDetails(long studentRegistrationNumber, String dataToValidate, int column, int flag) {
		try {
			return (adminDelegate.updateStudentDetails(studentRegistrationNumber, dataToValidate, column, flag));
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public boolean updateFacultyDetails(long facultyRegistrationNumber, String dataToValidate, int column, int flag) {
		try {
			return (adminDelegate.updateFacultyDetails(facultyRegistrationNumber, dataToValidate, column, flag));
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public boolean addResults(ResultData addResult) {
		try {
			return adminDelegate.addResults(addResult);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public ArrayList<LinkedHashMap<String, String>> getApprovedRevaluationList() {
		try {
			return adminDelegate.getApprovedRevaluationList();
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return null;
	}

	public boolean updateGrade(int revalId, char grade) {
		try {
			return adminDelegate.updateGrade(revalId, grade);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return false;
	}

	public ArrayList<LinkedHashMap<String, String>> getAllStudentDetails() {
		try {
			return adminDelegate.getAllStudentDetails();
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return null;
	}

	public ArrayList<LinkedHashMap<String, String>> getAllFacultyDetails() {
		try {
			return adminDelegate.getAllFacultyDetails();
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return null;
	}

	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(int regno, int flag) {
		try {
			return adminDelegate.getSubjectDetailsById(regno, flag);
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return null;
	}

	public ArrayList<LinkedHashMap<String, String>> getAllCollegeDetails() {
		try {
			return adminDelegate.getAllCollegeDetails();
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured!");
		}
		return null;
	}
}
