package io.ztech.onlinebidding.services;

import io.ztech.onlinebidding.bean.CustomerDetail;
import io.ztech.onlinebidding.delegate.NewUserDelegate;

public class NewUserRegisterService {
	NewUserDelegate userDelegate = new NewUserDelegate();

	public void newUser(CustomerDetail customerDetail) throws Exception {
		try {
			customerDetail = userDelegate.userPasswordEncode(customerDetail);
			userDelegate.insertDetailsToDb(customerDetail);
		} catch (Exception e) {
			throw e;
		}
	}
}
