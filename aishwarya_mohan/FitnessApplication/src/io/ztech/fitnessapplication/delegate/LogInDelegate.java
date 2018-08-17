package io.ztech.fitnessapplication.delegate;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.dao.UserAccountDAO;

public class LogInDelegate {
	public int findUser(UserAccount account) {
		return new UserAccountDAO().findUser(account);
	}

	public boolean setLogin(UserAccount account) {
		return new UserAccountDAO().setLogin(account);
	}

}
