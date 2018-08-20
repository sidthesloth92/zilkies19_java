package io.ztech.onlinebidding.services;

import io.ztech.onlinebidding.delegate.NewUserDelegate;
import io.ztech.onlinebidding.ui.DisplayException;

public class CheckUserName {
	NewUserDelegate checkUser = new NewUserDelegate();

	public boolean checkUserName(String userName) {
		try {
			if (checkUser.userNameAvailable(userName)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			DisplayException.displayException();
		}
		return false;
	}
}
