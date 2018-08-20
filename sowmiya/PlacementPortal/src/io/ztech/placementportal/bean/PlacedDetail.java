package io.ztech.placementportal.bean;

import io.ztech.placementportal.constants.ApplicationConstants;

public class PlacedDetail {
	private String studentId;
	private int companyId;
	private String jobStatus;

	
	@Override
	public String toString() {
		return ApplicationConstants.JOB + jobStatus;
	}


	public String getStudentId() {
		return studentId;
	}


	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}


	public int getCompanyId() {
		return companyId;
	}


	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


	public String getJobStatus() {
		return jobStatus;
	}


	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

}
