package io.ztech.fitnessapplication.service;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserAccountDetails;
import io.ztech.fitnessapplication.delegate.LogInDelegate;
import io.ztech.fitnessapplication.delegate.UserDetailsDelegate;

public class AccountService {
	public boolean loginUser(UserAccount account) {
		return new LogInDelegate().loginUser(account);
	}

	public int getAccessLevel(UserAccount account) {
		if (account == null) {
			return 0;
		}
		return new UserDetailsDelegate().getAccesslevel(account);
	}

	public UserAccountDetails getProfile(UserAccount account) {
		return new UserDetailsDelegate().getProfile(account);
	}
	public boolean updateAccount(UserAccountDetails account) {
		return new UserDetailsDelegate().updateAccount(account);
	}
}
