package io.zilker.appstore.controller;

import io.zilker.appstore.beans.*;
import io.zilker.appstore.delegates.*;

public class AdminController {

	ControllerValidator validator;
	AdminDelegates adminDelegates;

	public AdminController() {
		validator = new ControllerValidator();
		adminDelegates = new AdminDelegates();
	}

	public boolean hasReferenceText(String text) throws Exception {
		return adminDelegates.hasReferenceText(text);
	}

	public boolean adminRegister(Administrator admin) throws Exception {
		validator.checkUserName(admin.getUserName());
		validator.checkPassword(admin.getPassword());
		return adminDelegates.registerAdmin(admin);
	}

	public Administrator adminLogin(Administrator admin) throws Exception {
		validator.checkUserName(admin.getUserName());
		validator.checkPassword(admin.getPassword());
		return adminDelegates.adminLogin(admin);
	}

	public boolean changeUserName(Administrator admin) throws Exception {
		validator.checkID(admin.getAdminID());
		validator.checkUserName(admin.getUserName());
		validator.checkPassword(admin.getPassword());
		return adminDelegates.changeUserName(admin);
	}

	public boolean changePassword(Administrator admin) throws Exception {
		validator.checkID(admin.getAdminID());
		validator.checkUserName(admin.getUserName());
		validator.checkPassword(admin.getPassword());
		return adminDelegates.changePassword(admin);
	}

}
