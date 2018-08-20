package com.zilker.ui;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.zilker.bean.LoginData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.OptionConstants;
import com.zilker.dao.FetchUserData;
import com.zilker.service.LoginService;
import com.zilker.util.Inputs;

public class Login {
	
	Logger logger=Logger.getLogger(Login.class.getName());
	
	Inputs inputs=new Inputs();
	
	//LoginService loginService = new LoginService();
	
	public void userLogin(LoginData loginData) {				
		
		logger.info(StringConstants.ENTER_EMAIL);
		
		loginData.setEmail(inputs.getEmail());
		
		logger.info(StringConstants.ENTER_PASSWORD);
		
		loginData.setPassword(inputs.getPassword());
		
		LoginService loginService = new LoginService();
		
		try {
			loginService.loginValues(loginData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
	}
	
	public void loginFailed()
	{
		
		logger.info(StringConstants.INVALID_CREDENTIALS);
	}
	
}
