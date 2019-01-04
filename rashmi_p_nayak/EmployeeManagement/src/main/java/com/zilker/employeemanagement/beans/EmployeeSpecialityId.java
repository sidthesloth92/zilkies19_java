package com.zilker.employeemanagement.beans;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeSpecialityId implements Serializable {
	
	private static final long serialVersionUID = 1L;

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

	public EmployeeSpecialityId(int employeeId, int specialityId) {
		super();
		this.employeeId = employeeId;
		this.specialityId = specialityId;
	}
	
	@Override
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		}
		if(!(object instanceof EmployeeSpecialityId)) {
			return false;
		}
		EmployeeSpecialityId employeeSpecialityId = (EmployeeSpecialityId) object;
		return Objects.equals(getEmployeeId(), employeeSpecialityId.getEmployeeId()) && Objects.equals(getSpecialityId(), employeeSpecialityId.getSpecialityId());
	}
	
	@Override 
	public int hashCode() {
		return Objects.hash(getEmployeeId(), getSpecialityId());
	}

}
