package io.ztech.placementportal.bean;

import io.ztech.placementportal.constants.ApplicationConstants;

public class Marks {
	private float cgpa, MarkX, MarkXII;
	private int arrearCount;
	private String studentId;

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

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return ApplicationConstants.CGPA + cgpa + "\n" + ApplicationConstants.PERCENTAGE_X + MarkX + "\n"
				+ ApplicationConstants.PERCENTAGE_XII + MarkXII + "\n" + ApplicationConstants.ARREAR_COUNT
				+ arrearCount;
	}

}
