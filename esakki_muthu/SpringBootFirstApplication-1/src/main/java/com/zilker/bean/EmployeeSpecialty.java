package com.zilker.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_specialty")
public class EmployeeSpecialty {
	
	@Id
	private int id;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	@Column(name = "spcialty_id")
	private int spcialtyId;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getSpcialtyId() {
		return spcialtyId;
	}

	public void setSpcialtyId(int spcialtyId) {
		this.spcialtyId = spcialtyId;
	}
	
}
