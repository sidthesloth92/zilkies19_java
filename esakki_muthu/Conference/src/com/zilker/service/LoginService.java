package com.zilker.service;

import java.sql.SQLException;

import com.zilker.bean.LoginData;
import com.zilker.bean.UserData;
import com.zilker.delegate.LoginDelegate;
import com.zilker.ui.AdminPage;
import com.zilker.ui.Login;
import com.zilker.ui.UserPage;

public class LoginService {

	public void loginValues(LoginData loginData) throws SQLException {

		LoginDelegate loginDelegate = new LoginDelegate();

		UserData userData = loginDelegate.login_Process(loginData);

		if (userData.getLoginStatus() == 1) {
			

			if (userData.getRoleId() == 2)

				loginAdminSuccessService(userData);

			else

				loginUserSuccessService(userData);

		} else {
 
			loginFailedService();

		}

	}

	public void loginAdminSuccessService(UserData userData) {

		AdminPage adminPage = new AdminPage();

		adminPage.getAdmin(userData);
	}

	public void loginUserSuccessService(UserData userData) {

		UserPage userPage = new UserPage();

		userPage.getHome(userData);
	}

	public void loginFailedService() {

		Login login = new Login();

		login.loginFailed();
	}

}
