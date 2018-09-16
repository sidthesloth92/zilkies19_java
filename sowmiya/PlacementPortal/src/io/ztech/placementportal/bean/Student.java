package io.ztech.placementportal.bean;

import io.ztech.placementportal.constants.ApplicationConstants;

public class Student {
	private String name;
	private int placed;
	private String studentId, department;

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

}
