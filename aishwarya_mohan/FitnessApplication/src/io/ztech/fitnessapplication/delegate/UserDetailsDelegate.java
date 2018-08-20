package io.ztech.fitnessapplication.delegate;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserAccountDetails;
import io.ztech.fitnessapplication.dao.UserAccountDAO;

public class UserDetailsDelegate {
	public int getAccesslevel(UserAccount account) {
		return new UserAccountDAO().getUserAccesslevel(account);
	}

	public UserAccountDetails getProfile(UserAccount account) {
		return new UserAccountDAO().getAccount(account);
	}

	public boolean updateAccount(UserAccountDetails account) {
		return new UserAccountDAO().updateAccount(account);
	}

}
