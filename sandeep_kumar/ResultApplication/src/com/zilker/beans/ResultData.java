package com.zilker.beans;

public class ResultData {
	private long studentRegistrationNumber;
	private String subjectCode;
	private String grade;
	public int writtenIn;
	public long getStudentRegistrationNumber() {
		return studentRegistrationNumber;
	}
	public void setStudentRegistrationNumber(long studentRegistrationNumber) {
		this.studentRegistrationNumber = studentRegistrationNumber;
	}
	public String getSubjectCode() {
		return subjectCode; 
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getWrittenIn() {
		return writtenIn;
	}
	public void setWrittenIn(int writtenIn) {
		this.writtenIn = writtenIn;
	}
	
}
