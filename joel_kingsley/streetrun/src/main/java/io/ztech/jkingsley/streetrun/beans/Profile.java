package io.ztech.jkingsley.streetrun.beans;

import java.io.Serializable;

public class Profile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9750468027770882L;
	
	Account account;
	User user;
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
