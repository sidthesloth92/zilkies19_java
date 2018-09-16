package io.ztech.placementportal.bean;

import io.ztech.placementportal.constants.ApplicationConstants;

public class Eligiblity {
	private float cgpa, MarkX, MarkXII;
	private int arrearCount;

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

	@Override
	public String toString() {
		return ApplicationConstants.CGPA + cgpa + ApplicationConstants.PERCENTAGE_X + MarkX
				+ ApplicationConstants.PERCENTAGE_XII + MarkXII + ApplicationConstants.ARREAR_COUNT + arrearCount;
	}

}
