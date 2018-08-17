package io.ztech.fitnessapplication.delegate;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserProfile;
import io.ztech.fitnessapplication.dao.UserAccountDAO;

public class UserDetailsDelegate {
	public int getAccesslevel(UserAccount account) {
		return new UserAccountDAO().getUserAccesslevel(account);
	}
	
	public UserProfile getProfile(UserAccount account) {
		return new UserAccountDAO().getProfile(account);
	}
}
