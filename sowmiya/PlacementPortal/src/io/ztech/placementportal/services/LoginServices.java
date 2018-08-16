package io.ztech.placementportal.services;

import java.util.logging.Logger;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.delegate.LoginDelegate;

public class LoginServices {
	Logger log = Logger.getLogger("LoginServices.class");
	public static String active_user = "";

	public String login(Register login) {
		LoginDelegate loginDelegate = new LoginDelegate();
		return loginDelegate.checkLoginCredential(login);

	}

}
