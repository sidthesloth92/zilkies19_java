package io.ztech.placementportal.bean;

import io.ztech.placementportal.constants.ApplicationConstants;

public class Company {
	private int companyId;
	private String companyName, companyDescription, companyType;
	private String jobLocation, designation, payment, dayOfRecruitment, lastDateForRegistration,registeredDate;
	public String getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	private float cgpa, MarkX, MarkXII;
	private int arrearCount;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getDayOfRecruitment() {
		return dayOfRecruitment;
	}

	public void setDayOfRecruitment(String dayOfRecruitment) {
		this.dayOfRecruitment = dayOfRecruitment;
	}

	public String getLastDateForRegistration() {
		return lastDateForRegistration;
	}

	public void setLastDateForRegistration(String lastDate) {
		this.lastDateForRegistration = lastDate;
	}

	/*@Override
	public String toString() {
		return ApplicationConstants.COMPANY_ID + companyId + "\n" + ApplicationConstants.COMPANY_NAME + companyName
				+ "\n" + ApplicationConstants.COMPANY_DESCRIPTION + companyDescription + "\n"
				+ ApplicationConstants.COMPANY_TYPE + companyType + "\n" + ApplicationConstants.JOB_LOCATION
				+ jobLocation + "\n" + ApplicationConstants.DESIGNATION + designation + "\n"
				+ ApplicationConstants.PAYMENT + payment + "\n" + ApplicationConstants.RECRUITMENT_DATE
				+ dayOfRecruitment + "\n" + ApplicationConstants.LAST_DATE_FOR_REGISTRATION + lastDateForRegistration;
	}*/

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
