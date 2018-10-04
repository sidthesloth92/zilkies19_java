package io.ztech.placementportal.bean;

import io.ztech.placementportal.constants.ApplicationConstants;

public class PlacedDetail {
	private String studentId,studentName,Department;
	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getDepartment() {
		return Department;
	}


	public void setDepartment(String department) {
		Department = department;
	}


	private int companyId,totalCount,cse,ece,eee,ei,mech;
	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getCse() {
		return cse;
	}


	public void setCse(int cse) {
		this.cse = cse;
	}


	public int getEce() {
		return ece;
	}


	public void setEce(int ece) {
		this.ece = ece;
	}


	public int getEee() {
		return eee;
	}


	public void setEee(int eee) {
		this.eee = eee;
	}


	public int getEi() {
		return ei;
	}


	public void setEi(int ei) {
		this.ei = ei;
	}


	public int getMech() {
		return mech;
	}


	public void setMech(int mech) {
		this.mech = mech;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	private String jobStatus,companyName;

	
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
