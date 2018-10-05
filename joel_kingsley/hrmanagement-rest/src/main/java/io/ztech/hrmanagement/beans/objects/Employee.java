package io.ztech.hrmanagement.beans.objects;

import java.math.BigInteger;
import java.sql.Date;

import io.ztech.hrmanagement.beans.types.AccountType;
import io.ztech.hrmanagement.beans.types.BloodGroup;
import io.ztech.hrmanagement.beans.types.EmployeeStatus;
import io.ztech.hrmanagement.beans.types.Gender;
import io.ztech.hrmanagement.beans.types.MaritalStatus;
import io.ztech.hrmanagement.beans.types.Qualification;

public class Employee {
	BigInteger emp_id;
	String emp_name;
	String emp_password;
	AccountType account_type;
	Gender gender;
	MaritalStatus marital_status;
	BigInteger designation_id;
	Date dob;
	Date doj;
	Qualification highest_qualification;
	BloodGroup blood_group;
	String pan;
	String aadhar;
	String uan;
	String present_address;
	String permanent_address;
	EmployeeStatus emp_status;
	
	public BigInteger getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(BigInteger emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public AccountType getAccount_type() {
		return account_type;
	}
	public void setAccount_type(AccountType account_type) {
		this.account_type = account_type;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public MaritalStatus getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(MaritalStatus marital_status) {
		this.marital_status = marital_status;
	}
	public BigInteger getDesignation_id() {
		return designation_id;
	}
	public void setDesignation_id(BigInteger designation_id) {
		this.designation_id = designation_id;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Qualification getHighest_qualification() {
		return highest_qualification;
	}
	public void setHighest_qualification(Qualification highest_qualification) {
		this.highest_qualification = highest_qualification;
	}
	public BloodGroup getBlood_group() {
		return blood_group;
	}
	public void setBlood_group(BloodGroup blood_group) {
		this.blood_group = blood_group;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public String getUan() {
		return uan;
	}
	public void setUan(String uan) {
		this.uan = uan;
	}
	public String getPresent_address() {
		return present_address;
	}
	public void setPresent_address(String present_address) {
		this.present_address = present_address;
	}
	public String getPermanent_address() {
		return permanent_address;
	}
	public void setPermanent_address(String permanent_address) {
		this.permanent_address = permanent_address;
	}
	public EmployeeStatus getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(EmployeeStatus emp_status) {
		this.emp_status = emp_status;
	}
	
	
}
