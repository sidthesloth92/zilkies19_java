package io.ztech.cricalertbe.delegates;

import java.util.logging.Logger;

import io.ztech.cricalertbe.beans.User;
import io.ztech.cricalertbe.dao.CricketDAO;

public class UserDelegate {
	CricketDAO dao;
	
	public UserDelegate() {
		dao = new CricketDAO();
	}
	
	public boolean checkUser(User user) {
		return dao.searchUser(user);
	}
	
	public User verifyUser(User user) {
		Logger logger = Logger.getLogger(UserDelegate.class.getName());
		logger.info("Entered UserDelegate (BackEnd)");
		User returnUser = dao.fetchUser(user);
		logger.info("Exited UserDelegate (BackEnd)");
		return returnUser;
	}
	
	public boolean createUser(User newUser) {
		if(checkUser(newUser)) {
			return false;
		}
		dao.insertUser(newUser);
		return true;
	}
}
