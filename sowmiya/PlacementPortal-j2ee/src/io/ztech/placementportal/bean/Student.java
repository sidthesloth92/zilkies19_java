package io.ztech.placementportal.bean;

import io.ztech.placementportal.constants.ApplicationConstants;

public class Student {
	private String name;
	private int placed;
	private String studentId, department;
	private float cgpa, MarkX, MarkXII;
	private int arrearCount;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlaced() {
		return placed;
	}

	public void setPlaced(int placed) {
		this.placed = placed;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return ApplicationConstants.STUDENT_ID + studentId + "\n" + ApplicationConstants.NAME + name + "\n"
				+ ApplicationConstants.DEPARTMENT + department;
	}

	public float getCgpa() {
		return cgpa;
	}

	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}

	public float getMarkX() {
		return MarkX;
	}

	public void setMarkX(float markX) {
		MarkX = markX;
	}

	public float getMarkXII() {
		return MarkXII;
	}

	public void setMarkXII(float markXII) {
		MarkXII = markXII;
	}

	public int getArrearCount() {
		return arrearCount;
	}

	public void setArrearCount(int arrearCount) {
		this.arrearCount = arrearCount;
	}

}
