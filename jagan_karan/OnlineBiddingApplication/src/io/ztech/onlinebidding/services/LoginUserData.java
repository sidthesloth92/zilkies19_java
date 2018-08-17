package io.ztech.onlinebidding.services;

import io.ztech.onlinebidding.bean.CustomerDetail;
import io.ztech.onlinebidding.delegate.LoginUserDelegate;

public class LoginUserData {
	LoginUserDelegate loginData = new LoginUserDelegate();

	public CustomerDetail LoginUser(CustomerDetail details) {
		details = loginData.userDetail(details);
		details=loginData.userType(details);
		return details;
	}
}
