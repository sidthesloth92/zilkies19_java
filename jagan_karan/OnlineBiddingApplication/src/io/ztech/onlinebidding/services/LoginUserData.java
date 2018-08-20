package io.ztech.onlinebidding.services;

import io.ztech.onlinebidding.bean.CustomerDetail;
import io.ztech.onlinebidding.delegate.LoginUserDelegate;
import io.ztech.onlinebidding.ui.DisplayException;

public class LoginUserData {
	LoginUserDelegate loginData = new LoginUserDelegate();

	public CustomerDetail LoginUser(CustomerDetail details) {
		try {
			details = loginData.userDetail(details);
			details = loginData.userType(details);
		} catch (Exception e) {
			DisplayException.displayException();
		}
		return details;

	}
}
