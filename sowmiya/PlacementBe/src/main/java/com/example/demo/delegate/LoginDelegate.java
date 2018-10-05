package com.example.demo.delegate;

import com.example.demo.beans.Register;
import com.example.demo.dao.LoginDao;

public class LoginDelegate {

	public Register checkLoginCredential(Register login) {
		LoginDao loginDao = new LoginDao();
		System.out.println(loginDao.login(login));
		return  loginDao.login(login);
	}

}
