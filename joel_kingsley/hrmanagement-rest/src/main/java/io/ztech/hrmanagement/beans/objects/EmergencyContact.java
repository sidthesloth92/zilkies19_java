package io.ztech.hrmanagement.beans.objects;

import java.math.BigInteger;

public class EmergencyContact {
	BigInteger emergency_contact_id;
	BigInteger emp_id;
	String emergency_contact_name;
	String emergency_contact_phone;
	
	public BigInteger getEmergency_contact_id() {
		return emergency_contact_id;
	}
	public void setEmergency_contact_id(BigInteger emergency_contact_id) {
		this.emergency_contact_id = emergency_contact_id;
	}
	public BigInteger getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(BigInteger emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmergency_contact_name() {
		return emergency_contact_name;
	}
	public void setEmergency_contact_name(String emergency_contact_name) {
		this.emergency_contact_name = emergency_contact_name;
	}
	public String getEmergency_contact_phone() {
		return emergency_contact_phone;
	}
	public void setEmergency_contact_phone(String emergency_contact_phone) {
		this.emergency_contact_phone = emergency_contact_phone;
	}
	
	
}
