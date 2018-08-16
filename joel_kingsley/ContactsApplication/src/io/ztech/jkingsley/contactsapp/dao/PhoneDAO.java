package io.ztech.jkingsley.contactsapp.dao;

import io.ztech.jkingsley.contactsapp.beans.PhoneNumber;

public class PhoneDAO {
	public Long userId;
	public PhoneNumber oldPhoneNumber;
	public PhoneNumber newPhoneNumber;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public PhoneNumber getOldPhoneNumber() {
		return oldPhoneNumber;
	}
	public void setOldPhoneNumber(PhoneNumber oldPhoneNumber) {
		this.oldPhoneNumber = oldPhoneNumber;
	}
	public PhoneNumber getNewPhoneNumber() {
		return newPhoneNumber;
	}
	public void setNewPhoneNumber(PhoneNumber newPhoneNumber) {
		this.newPhoneNumber = newPhoneNumber;
	}
	
	
}
