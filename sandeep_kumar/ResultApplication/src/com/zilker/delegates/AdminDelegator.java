package com.zilker.delegates;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.beans.*;
import com.zilker.dao.*;

public class AdminDelegator {
	LoginDetailsDao loginDetailsDao =new LoginDetailsDao();
	StudentDetailsDao studentDetailsDao =new StudentDetailsDao();
	FacultyDetailsDao facultyDetailsDao =new FacultyDetailsDao();
	SubjectDetailsDao subjectDetailsDao =new SubjectDetailsDao();
	CollegeDetailsDao collegeDetailsDao =new CollegeDetailsDao();
	ResultDetailsDao resultDetailsDao =new ResultDetailsDao();
	RevaluationDetailsDao revaluationDetailsDao =new RevaluationDetailsDao();
	
	public boolean addStudentDetails(StudentData obj) {	
		return studentDetailsDao.insertStudentDetails(obj);
	}
	
	public boolean registerStudent(StudentData obj) {
		return loginDetailsDao.registerStudent(obj);
	}
	
	public boolean addFacultyDetails(FacultyData obj) {
		return facultyDetailsDao.insertFacultyDetails(obj);
	}
	
	public boolean registerFaculty(FacultyData obj) {
		return loginDetailsDao.registerFaculty(obj);
	}
	
	public boolean addSubjectDetails(SubjectData obj) {
		return subjectDetailsDao.insertSubjectDetails(obj);
	}
	
	public boolean addCollegeDetails(CollegeData obj) {
		return collegeDetailsDao.insertCollegeDetails(obj);
	}
	
	public boolean addResults(ResultData obj) {
		return resultDetailsDao.insertResults(obj);
	}
	
	public boolean deleteStudentDetails(long studentRegistrationNumber) {	
		return studentDetailsDao.deleteStudentDetails(studentRegistrationNumber);
	}
	
	public boolean deleteFacultyDetails(long facultyRegistrationNumber) {
		return facultyDetailsDao.deleteFacultyDetails(facultyRegistrationNumber);
	}
	
	public boolean deleteSubjectDetails(String subjectCode) {
		return subjectDetailsDao.deleteSubjectDetails(subjectCode);
	}
	
	public boolean deleteCollegeDetails(int collegeCode) {
		return collegeDetailsDao.deleteCollegeDetails(collegeCode);
	}
	
	public boolean updateStudentDetails (long studentRegistrationNumber,String dataToValidate, int column,int flag) {
		return (studentDetailsDao.updateStudentDetails(studentRegistrationNumber,dataToValidate,column,flag));
	}
	
	public boolean updateFacultyDetails (long facultyRegistrationNumber,String dataToValidate, int column,int flag) {
		return (facultyDetailsDao.updateFacultyDetails(facultyRegistrationNumber,dataToValidate,column,flag));
	}
	
	public ArrayList<LinkedHashMap<String, String>> getApprovedRevaluationList() {
		return revaluationDetailsDao.getApprovedRevaluationList();
	}
	
	public boolean updateGrade(int revalId, char grade) {
		String status="No Change"; char currentGrade;
		currentGrade=revaluationDetailsDao.getCurrentGrade(revalId);
		if(currentGrade=='o') {
			return false;
		}
		else if((int)grade<(int)currentGrade) {
			status="Change";
		}
		return revaluationDetailsDao.updateGrade(revalId, Character.toString(grade), status);
		
	}
	
	public ArrayList<LinkedHashMap<String, String>> getAllStudentDetails(){
		return studentDetailsDao.getAllStudentDetails();
	}
	
	public ArrayList<LinkedHashMap<String, String>> getAllFacultyDetails(){
		return facultyDetailsDao.getAllFacultyDetails();
	}
	
	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(int regno,int flag){
		return subjectDetailsDao.getSubjectDetailsById(regno,flag);
	}
	
	public ArrayList<LinkedHashMap<String, String>> getAllCollegeDetails(){
		return collegeDetailsDao.getAllCollegeDetails();
	}
}
