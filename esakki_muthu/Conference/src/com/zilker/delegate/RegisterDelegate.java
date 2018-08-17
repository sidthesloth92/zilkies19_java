package com.zilker.delegate;

import com.zilker.bean.RegisterData;

import com.zilker.dao.AddNewUser;

import com.zilker.service.RegisterService;

public class RegisterDelegate {

	AddNewUser addNewUser = new AddNewUser();

	public int registerProcess(RegisterData registerData) {

		String password = registerData.getPassword();

		String shaPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

		registerData.setPassword(shaPassword);

		int result = addNewUser.insertUser(registerData);	

		return result;

	}

}
