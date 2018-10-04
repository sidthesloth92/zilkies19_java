package com.zilker.delegates;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zilker.beans.CollegeData;
import com.zilker.beans.FacultyData;
import com.zilker.beans.ResultData;
import com.zilker.beans.StudentData;
import com.zilker.beans.SubjectData;
import com.zilker.utils.RestServiceHandler;

public class AdminDelegate {

	/*
	 * public boolean addStudentDetails(StudentData obj) throws SQLException {
	 * return studentDetailsDao.insertStudentDetails(obj); }
	 * 
	 * public boolean registerStudent(StudentData obj) throws SQLException { return
	 * loginDetailsDao.registerStudent(obj); }
	 * 
	 * public boolean addFacultyDetails(FacultyData obj) throws SQLException {
	 * return facultyDetailsDao.insertFacultyDetails(obj); }
	 * 
	 * public boolean registerFaculty(FacultyData obj) throws SQLException { return
	 * loginDetailsDao.registerFaculty(obj); }
	 * 
	 * public boolean addSubjectDetails(SubjectData obj) throws SQLException {
	 * return subjectDetailsDao.insertSubjectDetails(obj); }
	 * 
	 * public boolean addCollegeDetails(CollegeData obj) throws SQLException {
	 * return collegeDetailsDao.insertCollegeDetails(obj); }
	 * 
	 * public boolean addResults(ResultData obj) throws SQLException { return
	 * resultDetailsDao.insertResults(obj); }
	 * 
	 * public boolean deleteStudentDetails(long studentRegistrationNumber) throws
	 * SQLException { return
	 * studentDetailsDao.deleteStudentDetails(studentRegistrationNumber); }
	 * 
	 * public boolean deleteFacultyDetails(long facultyRegistrationNumber) throws
	 * SQLException { return
	 * facultyDetailsDao.deleteFacultyDetails(facultyRegistrationNumber); }
	 * 
	 * public boolean deleteSubjectDetails(String subjectCode) throws SQLException {
	 * return subjectDetailsDao.deleteSubjectDetails(subjectCode); }
	 * 
	 * public boolean deleteCollegeDetails(int collegeCode) throws SQLException {
	 * return collegeDetailsDao.deleteCollegeDetails(collegeCode); }
	 * 
	 * public boolean updateStudentDetails(long currentRegistrationNumber,
	 * StudentData obj) throws SQLException { return
	 * (studentDetailsDao.updateStudentDetails(currentRegistrationNumber, obj)); }
	 * 
	 * public boolean updateFacultyDetails(long currentRegistrationNumber,
	 * FacultyData obj) throws SQLException { return
	 * (facultyDetailsDao.updateFacultyDetails(currentRegistrationNumber, obj)); }
	 * 
	 * public boolean updateCollegeDetails(int currentCollegeCode, CollegeData obj)
	 * throws SQLException { return
	 * (collegeDetailsDao.updateCollegeDetails(currentCollegeCode, obj)); }
	 * 
	 * public boolean updateSubjectDetails(String currentSubjectCode, SubjectData
	 * obj) throws SQLException { return
	 * (subjectDetailsDao.updateSubjectDetails(currentSubjectCode, obj)); }
	 * 
	 * public ArrayList<LinkedHashMap<String, String>> getApprovedRevaluationList()
	 * throws SQLException { return
	 * revaluationDetailsDao.getApprovedRevaluationList(); }
	 * 
	 * public boolean updateGrade(int revalId, char grade) throws SQLException {
	 * String status = "No Change"; char currentGrade; currentGrade =
	 * revaluationDetailsDao.getCurrentGrade(revalId); if (currentGrade == 'o') {
	 * return false; } else if ((int) grade < (int) currentGrade) { status =
	 * "Change"; } else { grade=currentGrade; } return
	 * revaluationDetailsDao.updateGrade(revalId, Character.toString(grade),
	 * status);
	 * 
	 * }
	 * 
	 * public boolean isSubjectNamePresent(SubjectData subject) throws SQLException
	 * { return subjectDetailsDao.isSubjectNamePresent(subject); }
	 * 
	 * public boolean isSubjectCodePresent(SubjectData subject) throws SQLException
	 * { return subjectDetailsDao.isSubjectCodePresent(subject); }
	 * 
	 * public boolean isCollegeNamePresent(CollegeData college) throws SQLException
	 * { return collegeDetailsDao.isCollegeNamePresent(college); }
	 * 
	 * public boolean isCollegeCodePresent(CollegeData college) throws SQLException
	 * { return collegeDetailsDao.isCollegeCodePresent(college); }
	 */
	/*
	 * public ArrayList<FacultyData> getAllFacultyDetails() throws SQLException {
	 * return facultyDetailsDao.getAllFacultyDetails(); }
	 * 
	 * public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(int
	 * regno, int flag) throws SQLException { return
	 * subjectDetailsDao.getSubjectDetailsById(regno, flag); }
	 * 
	 * public ArrayList<SubjectData> getAllSubjectDetails() throws SQLException {
	 * return subjectDetailsDao.getAllSubjectDetails(); }
	 * 
	 * public ArrayList<CollegeData> getAllCollegeDetails() throws SQLException {
	 * return collegeDetailsDao.getAllCollegeDetails(); }
	 * 
	 * public ArrayList<ResultData> getAllResultDetails() throws SQLException {
	 * return resultDetailsDao.getAllResultDetails(); }
	 * 
	 * public long getLastFacultyRegistrationNumber(int collegeCode) throws
	 * SQLException { return
	 * facultyDetailsDao.getLastFacultyRegistrationNumber(collegeCode); }
	 */

	public boolean addStudentDetails(StudentData student) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler("http://localhost:8000/ResultApplication/admin/students",
				"POST", new Gson().toJson(student));
		return Boolean.parseBoolean(response);
	}

	public boolean addFacultyDetails(FacultyData faculty) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler("http://localhost:8000/ResultApplication/admin/faculty",
				"POST", new Gson().toJson(faculty));
		return Boolean.parseBoolean(response);
	}

	public boolean addCollegeDetails(CollegeData college) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler("http://localhost:8000/ResultApplication/admin/colleges",
				"POST", new Gson().toJson(college));
		return Boolean.parseBoolean(response);
	}

	public boolean addSubjectDetails(SubjectData subject) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler("http://localhost:8000/ResultApplication/admin/subjects",
				"POST", new Gson().toJson(subject));
		return Boolean.parseBoolean(response);
	}

	public ArrayList<StudentData> getAllStudentDetails() throws SQLException {

		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler("http://localhost:8000/ResultApplication/admin/students",
				"GET", null);
		Type listType = new TypeToken<List<StudentData>>() {
		}.getType();
		ArrayList<StudentData> studentList = new Gson().fromJson(response, listType);
		return studentList;
	}

	public ArrayList<FacultyData> getAllFacultyDetails() throws SQLException {

		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler("http://localhost:8000/ResultApplication/admin/faculty", "GET",
				null);
		Type listType = new TypeToken<List<FacultyData>>() {
		}.getType();
		ArrayList<FacultyData> facultyList = new Gson().fromJson(response, listType);
		return facultyList;
	}

	public ArrayList<CollegeData> getAllCollegeDetails() throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler("http://localhost:8000/ResultApplication/admin/colleges",
				"GET", null);
		Type listType = new TypeToken<List<CollegeData>>() {
		}.getType();
		ArrayList<CollegeData> collegeList = new Gson().fromJson(response, listType);
		return collegeList;
	}

	public ArrayList<SubjectData> getAllSubjectDetails() throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler("http://localhost:8000/ResultApplication/admin/subjects",
				"GET", null);
		Type listType = new TypeToken<List<SubjectData>>() {
		}.getType();
		ArrayList<SubjectData> subjectList = new Gson().fromJson(response, listType);
		return subjectList;
	}

	public ArrayList<ResultData> getAllResultDetails() throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler("http://localhost:8000/ResultApplication/admin/results", "GET",
				null);
		Type listType = new TypeToken<List<ResultData>>() {
		}.getType();
		ArrayList<ResultData> resultList = new Gson().fromJson(response, listType);
		return resultList;
	}

	public long getLastStudentRegistrationNumber(int collegeCode) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler(
				"http://localhost:8000/ResultApplication/admin/lastStudentRegistrationNumber/" + collegeCode, "GET",
				null);
		return Long.parseLong(response);
	}

	public long getLastFacultyRegistrationNumber(int collegeCode) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler(
				"http://localhost:8000/ResultApplication/admin/lastFacultyRegistrationNumber/" + collegeCode, "GET",
				null);
		return Long.parseLong(response);
	}

	public boolean updateStudentDetails(long currentRegistrationNumber, StudentData student) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler(
				"http://localhost:8000/ResultApplication/admin/students/" + currentRegistrationNumber, "PUT", new Gson().toJson(student));
		return Boolean.parseBoolean(response);
	}

	public boolean deleteStudentDetails(long studentRegistrationNumber) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler(
				"http://localhost:8000/ResultApplication/admin/students/" + studentRegistrationNumber, "DELETE", null);
		return Boolean.parseBoolean(response);
	}

	public boolean deleteFacultyDetails(long facultyRegistrationNumber) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler(
				"http://localhost:8000/ResultApplication/admin/faculty/" + facultyRegistrationNumber, "DELETE", null);
		return Boolean.parseBoolean(response);
	}

	public boolean deleteCollegeDetails(int collegeCode) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler(
				"http://localhost:8000/ResultApplication/admin/colleges/" + collegeCode, "DELETE", null);
		return Boolean.parseBoolean(response);
	}

	public boolean deleteSubjectDetails(int subjectode) throws SQLException {
		RestServiceHandler restService = new RestServiceHandler();
		String response = restService.restServiceHandler(
				"http://localhost:8000/ResultApplication/admin/subjects/" + subjectode, "DELETE", null);
		return Boolean.parseBoolean(response);
	}
}
