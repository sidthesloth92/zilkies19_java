package io.ztech.fitnessapplication.delegate;

import io.ztech.fitnessapplication.beans.UserProfile;
import io.ztech.fitnessapplication.dao.UserAccountDAO;

public class SignUpDelegate {
	public boolean signup(UserProfile newProfile) {
		return new UserAccountDAO().addUserAccount(newProfile);
	}
}
