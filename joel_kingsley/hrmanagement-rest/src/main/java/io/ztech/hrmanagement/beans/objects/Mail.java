package io.ztech.hrmanagement.beans.objects;

import java.math.BigInteger;

import io.ztech.hrmanagement.beans.types.MailType;

public class Mail {
	BigInteger mail_id;
	BigInteger emp_id;
	String mail_address;
	MailType mail_type;
	
	public BigInteger getMail_id() {
		return mail_id;
	}
	public void setMail_id(BigInteger mail_id) {
		this.mail_id = mail_id;
	}
	public BigInteger getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(BigInteger emp_id) {
		this.emp_id = emp_id;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public MailType getMail_type() {
		return mail_type;
	}
	public void setMail_type(MailType mail_type) {
		this.mail_type = mail_type;
	}
	
	
}
