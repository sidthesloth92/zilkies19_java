package io.zilker.appstore.controller;

import io.zilker.appstore.beans.*;
import io.zilker.appstore.delegates.*;
import io.zilker.appstore.constants.*;
import io.zilker.appstore.exceptions.*;

public class UserController {

	ControllerValidator validator;
	UserDelegates userDelegates;

	public UserController() {
		validator = new ControllerValidator();
		userDelegates = new UserDelegates();
	}

	public void userRegister(User user) throws Exception {
		if (!validator.isValidated(user.getFullName(), RegularExpressions.NAME)) {
			throw new BusinessException(Errors.NAME_STRUC_MISMATCH);
		}
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		if (user.getUserPrivilege().compareTo("u") != 0 && user.getUserPrivilege().compareTo("d") != 0) {
			throw new BusinessException(Errors.PRIVILAGE_STRUC_MISMATCH);
		}
		if(!userDelegates.registerUser(user)) {
			throw new SystemException(Errors.DATABASE_ERR);
		}
	}
	
	public User userLogin (User user) throws Exception {
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		return userDelegates.userLogin(user);
	}

	public boolean changeUserName (User user) throws Exception {
		validator.checkID(user.getUserID());
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		return userDelegates.changeUserName(user);
	}
	
	public boolean changePassword (User user) throws Exception {
		validator.checkID(user.getUserID());
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		return userDelegates.changePassword(user);
	}
	
	public boolean changeUserPrivilege(User user) throws Exception {
		validator.checkID(user.getUserID());
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		if (user.getUserPrivilege().compareTo("u") != 0 && user.getUserPrivilege().compareTo("d") != 0) {
			throw new BusinessException(Errors.PRIVILAGE_STRUC_MISMATCH);
		}
		return userDelegates.changeUserPrivilege(user);
	}
	
	public float getUserRating(User user) throws Exception {
		return userDelegates.getUserRating(user);
	}
	
	public int getUserPublishedAppCount(User user) throws Exception {
		return userDelegates.getUserPublishedAppCount(user);
	}
	
}
