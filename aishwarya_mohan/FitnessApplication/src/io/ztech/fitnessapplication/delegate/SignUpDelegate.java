package io.ztech.fitnessapplication.delegate;

import io.ztech.fitnessapplication.beans.UserAccountDetails;
import io.ztech.fitnessapplication.dao.UserAccountDAO;

public class SignUpDelegate {
	public boolean signup(UserAccountDetails newProfile) {
		return new UserAccountDAO().addUserAccount(newProfile);
	}
}
