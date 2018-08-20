package com.zilker.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.beans.*;
import com.zilker.delegates.AdminDelegator;
public class AdminServices {
	AdminDelegator adminDelegate=new AdminDelegator();
	
	public boolean addStudentDetails (StudentData obj) {
		return (adminDelegate.addStudentDetails(obj) && adminDelegate.registerStudent(obj));
	}
	
	public boolean addFacultyDetails (FacultyData obj) {
		return (adminDelegate.addFacultyDetails(obj) && adminDelegate.registerFaculty(obj));
	}
	
	public boolean addSubjectDetails (SubjectData obj) {
		return adminDelegate.addSubjectDetails(obj);
	}
	
	public boolean addCollegeDetails (CollegeData obj) {
		return adminDelegate.addCollegeDetails(obj);
	}
	
	public boolean deleteStudentDetails (long studentRegistrationNumber) {
		return (adminDelegate.deleteStudentDetails(studentRegistrationNumber));
	}
	
	public boolean deleteFacultyDetails (long facultyRegistrationNumber) {
		return (adminDelegate.deleteFacultyDetails(facultyRegistrationNumber));
	}
	
	public boolean deleteSubjectDetails (String subjectCode) {
		return adminDelegate.deleteSubjectDetails(subjectCode);
	}
	
	public boolean deleteCollegeDetails (int collegeCode) {
		return adminDelegate.deleteCollegeDetails(collegeCode);
	}
	
	public boolean updateStudentDetails (long studentRegistrationNumber,String dataToValidate, int column,int flag) {
		return (adminDelegate.updateStudentDetails(studentRegistrationNumber,dataToValidate,column,flag));
	}
	
	public boolean updateFacultyDetails (long facultyRegistrationNumber,String dataToValidate, int column,int flag) {
		return (adminDelegate.updateFacultyDetails(facultyRegistrationNumber,dataToValidate,column,flag));
	}
	
	public boolean addResults (ResultData addResult) {
		return adminDelegate.addResults(addResult);
	}
	
	public ArrayList<LinkedHashMap<String, String>> getApprovedRevaluationList() {
		return adminDelegate.getApprovedRevaluationList();
	}
	
	public boolean updateGrade(int revalId, char grade) {
		return adminDelegate.updateGrade(revalId, grade);
	}
	
	public ArrayList<LinkedHashMap<String, String>> getAllStudentDetails(){
		return adminDelegate.getAllStudentDetails();
	}
	
	public ArrayList<LinkedHashMap<String, String>> getAllFacultyDetails(){
		return adminDelegate.getAllFacultyDetails();
	}
	
	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(int regno,int flag){
		return adminDelegate.getSubjectDetailsById(regno,flag);
	}
	
	public ArrayList<LinkedHashMap<String, String>> getAllCollegeDetails(){
		return adminDelegate.getAllCollegeDetails();
	}
}
