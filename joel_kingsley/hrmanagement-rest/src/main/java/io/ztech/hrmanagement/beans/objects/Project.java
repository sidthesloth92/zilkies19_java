package io.ztech.hrmanagement.beans.objects;

import java.math.BigInteger;

public class Project {
	BigInteger project_id;
	String project_name;
	String location;
	BigInteger reporting_manager_id;
	public BigInteger getProject_id() {
		return project_id;
	}
	public void setProject_id(BigInteger project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public BigInteger getReporting_manager_id() {
		return reporting_manager_id;
	}
	public void setReporting_manager_id(BigInteger reporting_manager_id) {
		this.reporting_manager_id = reporting_manager_id;
	}
	
	
}
