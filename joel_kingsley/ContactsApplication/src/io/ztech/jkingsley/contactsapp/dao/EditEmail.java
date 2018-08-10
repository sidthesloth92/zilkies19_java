package io.ztech.jkingsley.contactsapp.dao;

import io.ztech.jkingsley.contactsapp.beans.Email;

public class EditEmail {
	Email oldEmail;
	Email newEmail;
	Long userId;
	public Email getOldEmail() {
		return oldEmail;
	}
	public void setOldEmail(Email oldEmail) {
		this.oldEmail = oldEmail;
	}
	public Email getNewEmail() {
		return newEmail;
	}
	public void setNewEmail(Email newEmail) {
		this.newEmail = newEmail;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
