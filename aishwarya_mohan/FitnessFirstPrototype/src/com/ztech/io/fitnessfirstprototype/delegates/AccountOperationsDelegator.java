package com.ztech.io.fitnessfirstprototype.delegates;

import java.sql.SQLException;

import com.ztech.io.fitnessfirstprototype.beans.User;
import com.ztech.io.fitnessfirstprototype.beans.UserAccount;
import com.ztech.io.fitnessfirstprototype.dao.UserAccountDao;

public class AccountOperationsDelegator {
	public boolean signUp(UserAccount newAccount) throws ClassNotFoundException, SQLException {
		return new UserAccountDao().signUp(newAccount);
	}
	
	public boolean login(User user) throws ClassNotFoundException, SQLException {
		return new UserAccountDao().login(user);
	}
	
	public boolean ifUsernameExists(User user) throws ClassNotFoundException, SQLException  {
		return new UserAccountDao().ifUsernameExists(user);
	}
	
	public boolean ifEmailExists(String email) throws ClassNotFoundException, SQLException  {
		return new UserAccountDao().ifEmailExists(email);
	}
	

}
