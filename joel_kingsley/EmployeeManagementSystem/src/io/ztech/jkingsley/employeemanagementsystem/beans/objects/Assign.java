package io.ztech.jkingsley.employeemanagementsystem.beans.objects;

import java.math.BigInteger;
import java.sql.Date;

public class Assign {
	BigInteger assign_id;
	Date assign_date;
	BigInteger project_id;
	BigInteger emp_id;
	BigInteger unit_id;
	public BigInteger getAssign_id() {
		return assign_id;
	}
	public void setAssign_id(BigInteger assign_id) {
		this.assign_id = assign_id;
	}
	public Date getAssign_date() {
		return assign_date;
	}
	public void setAssign_date(Date assign_date) {
		this.assign_date = assign_date;
	}
	public BigInteger getProject_id() {
		return project_id;
	}
	public void setProject_id(BigInteger project_id) {
		this.project_id = project_id;
	}
	public BigInteger getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(BigInteger emp_id) {
		this.emp_id = emp_id;
	}
	public BigInteger getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(BigInteger unit_id) {
		this.unit_id = unit_id;
	}
	
	
}
