package io.ztech.placementportal.delegate;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.dao.LoginDao;

public class LoginDelegate {

	public Register checkLoginCredential(Register login) throws Exception {
		LoginDao loginDao = new LoginDao();
		return loginDao.login(login);
	}

}
