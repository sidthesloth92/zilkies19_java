package io.ztech.fitnessapplication.service;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserProfile;
import io.ztech.fitnessapplication.delegate.LogInDelegate;
import io.ztech.fitnessapplication.delegate.UserDetailsDelegate;

public class AccountService {
	public int getLoggedInUserAccount(UserAccount account) {
		return new LogInDelegate().findUser(account);
	}

	public boolean logOutUser(UserAccount account) {
		resetLogin(account);
		return true;
	}

	public boolean setLogin(UserAccount account) {
		account.setLoginStatus(1);
		return new LogInDelegate().setLogin(account);
	}

	public boolean resetLogin(UserAccount account) {
		account.setLoginStatus(0);
		return new LogInDelegate().setLogin(account);
	}

	public int getAccessLevel(UserAccount account) {
		if (account == null) {
			return 0;
		}
		return new UserDetailsDelegate().getAccesslevel(account);
	}

	public UserProfile getProfile(UserAccount account) {
		return new UserDetailsDelegate().getProfile(account);
	}
}
