package io.ztech.cricalertbe.delegates;

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
		return dao.fetchUser(user);
	}
	
	public boolean createUser(User newUser) {
		if(checkUser(newUser)) {
			return false;
		}
		dao.insertUser(newUser);
		return true;
	}
}
