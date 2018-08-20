package io.ztech.fitnessapplication.delegate;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.dao.UserAccountDAO;

public class LogInDelegate {
	public boolean loginUser(UserAccount account) {
		return new UserAccountDAO().loginUser(account);
	}

}
