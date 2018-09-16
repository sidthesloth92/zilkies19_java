package io.zilker.appstore.delegates;

import io.zilker.appstore.dao.*;
import io.zilker.appstore.exceptions.*;
import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.Errors;

public class UserDelegates {

	private UserDAO userDAO;

	public UserDelegates() {
		userDAO = new UserDAO();
	}

	public boolean registerUser(User user) throws Exception {
		if (!userDAO.checkUserName(user)) {
			if (!userDAO.insertUser(user))
				throw new BusinessException(Errors.DATABASE_ERR);
		} else
			throw new BusinessException(Errors.USERNAME_EXISTS);
		return true;
	}

	public User userLogin(User user) throws Exception {
		user = userDAO.getUser(user);
		if (user == null)
			throw new BusinessException(Errors.INVALID_USERNAME_PASSWORD);
		return user;
	}

	public boolean changeUserName(User user) throws Exception {
		if (!userDAO.checkUserName(user)) {
			return userDAO.updateUserName(user);
		} else
			throw new BusinessException(Errors.USERNAME_EXISTS);
	}

	public boolean changePassword(User user) throws Exception {
		return userDAO.updatePassword(user);
	}

	public boolean changeUserPrivilege(User user) throws Exception {
		return userDAO.updateUserPrivilege(user);
	}

	public float getUserRating(User user) throws Exception {
		return userDAO.averageUserReviews(user);
	}
	
	public int getUserPublishedAppCount(User user) throws Exception {
		return userDAO.userPublishedAppCount(user);
	}
	
}
