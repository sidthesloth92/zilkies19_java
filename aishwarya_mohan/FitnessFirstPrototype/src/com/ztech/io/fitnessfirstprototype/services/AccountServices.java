package com.ztech.io.fitnessfirstprototype.services;

import java.sql.SQLException;

import com.ztech.io.fitnessfirstprototype.beans.UserAccount;
import com.ztech.io.fitnessfirstprototype.dao.UserAccountDao;

public class AccountServices {
	public boolean signUp(UserAccount newAccount) throws ClassNotFoundException, SQLException {
		return new UserAccountDao().signUp(newAccount);
	}

}
