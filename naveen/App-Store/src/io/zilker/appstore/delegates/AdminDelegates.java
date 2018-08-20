package io.zilker.appstore.delegates;

import io.zilker.appstore.dao.*;
import io.zilker.appstore.exceptions.*;
import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.Errors;

public class AdminDelegates {

	private AdminDAO adminDAO;

	public AdminDelegates() {
		adminDAO = new AdminDAO();
	}

	public boolean hasReferenceText(String text) throws Exception {
		return adminDAO.hasAdminReferenceText(text);
	}
	
	public boolean registerAdmin(Administrator admin) throws Exception {
		if (!adminDAO.checkAdminUserName(admin)) {
			return adminDAO.insertAdmin(admin);
		} else
			throw new BusinessException(Errors.USERNAME_EXISTS);
	}

	public Administrator adminLogin(Administrator admin) throws Exception {
		admin = adminDAO.getAdmin(admin);
		if(admin == null)
			throw new BusinessException(Errors.INVALID_USERNAME_PASSWORD);
		return admin;
	}
	
	public boolean changeUserName(Administrator admin) throws Exception {
		return adminDAO.updateUserName(admin);
	}
	
	public boolean changePassword(Administrator admin) throws Exception {
		return adminDAO.updatePassword(admin);
	}
	
}
