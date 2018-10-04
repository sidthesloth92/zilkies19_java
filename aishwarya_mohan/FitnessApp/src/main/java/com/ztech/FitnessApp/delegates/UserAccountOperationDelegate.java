package com.ztech.FitnessApp.delegates;

import com.ztech.FitnessApp.beans.UserAccount;
import com.ztech.FitnessApp.dao.UserAccountDao;

public class UserAccountOperationDelegate {

	public boolean signUp(UserAccount newAccount) {
		boolean isAccountAdded = false;
		try {
			isAccountAdded = new UserAccountDao().signUp(newAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAccountAdded;
	}

	public boolean ifUserNameExists(String userName) {
		boolean isExists = true;
		try {
			isExists = new UserAccountDao().ifUsernameExists(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isExists;
	}

	public boolean IfEmailExists(String email) {
		boolean isExists = false;
		try {
			isExists = new UserAccountDao().ifEmailExists(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isExists;
	}

}
