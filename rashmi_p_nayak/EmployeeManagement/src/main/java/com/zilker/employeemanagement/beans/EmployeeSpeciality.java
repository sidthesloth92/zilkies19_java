package com.zilker.employeemanagement.beans;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "employee_speciality")
public class EmployeeSpeciality {
	
	@EmbeddedId
	private EmployeeSpecialityId employeeSpecialityId;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	@Column(name = "speciality_id")
	private int specialityId;

	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public int getSpecialityId() {
		return specialityId;
	}


	public void setSpecialityId(int specialityId) {
		this.specialityId = specialityId;
	}


	public EmployeeSpeciality(int employeeId, int specialityId) {
		super();
		this.employeeId = employeeId;
		this.specialityId = specialityId;
	}

	
	protected EmployeeSpeciality() {} 
	
	@Override
	public String toString() {
		return String.format("EmployeeSpeciality[employeeId = %d, specialityId = %d]", employeeId, specialityId);
	}
}
