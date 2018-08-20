package com.zilker.delegate;

import java.sql.SQLException;

import com.zilker.bean.LoginData;
import com.zilker.bean.UserData;
import com.zilker.dao.FetchUserData;
import com.zilker.service.LoginService;

public class LoginDelegate {

	public UserData login_Process(LoginData loginData) throws SQLException {
		
		
		String shaPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(loginData.getPassword());
		
		loginData.setPassword(shaPassword);

		FetchUserData fetchUserData = new FetchUserData();

		UserData userData = fetchUserData.getUser(loginData);
				
		return userData;
				
	}
	

	public void login_Failed() {

		LoginService loginService = new LoginService();

		loginService.loginFailedService();

	}

}
