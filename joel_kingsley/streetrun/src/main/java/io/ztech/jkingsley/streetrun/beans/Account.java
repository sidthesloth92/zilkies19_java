package io.ztech.jkingsley.streetrun.beans;

import java.io.Serializable;
import java.math.BigInteger;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -743557115916156051L;
	
	BigInteger user_id;
	String user_name;
	String password;
	String email;
	String alternate_email;
	
	public BigInteger getUser_id() {
		return user_id;
	}
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlternate_email() {
		return alternate_email;
	}
	public void setAlternate_email(String alternate_email) {
		this.alternate_email = alternate_email;
	}
	
	
}
