package io.ztech.cricalert.delegate;

import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.dao.CricketDAO;

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
	
	public void createUser(User newUser) {
		dao.insertUser(newUser);
	}
}
