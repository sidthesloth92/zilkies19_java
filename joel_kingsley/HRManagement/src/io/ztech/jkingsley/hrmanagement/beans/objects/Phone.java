package io.ztech.jkingsley.hrmanagement.beans.objects;

import java.math.BigInteger;

import io.ztech.jkingsley.hrmanagement.beans.types.PhoneType;

public class Phone {
	BigInteger phone_id;
	BigInteger emp_id;
	String phone_number;
	PhoneType phone_type;
	
	public BigInteger getPhone_id() {
		return phone_id;
	}
	public void setPhone_id(BigInteger phone_id) {
		this.phone_id = phone_id;
	}
	public BigInteger getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(BigInteger emp_id) {
		this.emp_id = emp_id;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public PhoneType getPhone_type() {
		return phone_type;
	}
	public void setPhone_type(PhoneType phone_type) {
		this.phone_type = phone_type;
	}
	
	
}
