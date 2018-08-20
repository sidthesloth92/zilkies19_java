package com.zilker.service;

import java.sql.SQLException;

import com.zilker.bean.RegisterData;
import com.zilker.delegate.RegisterDelegate;
import com.zilker.ui.Register;

public class RegisterService {
	
	RegisterDelegate registerDelegate = new RegisterDelegate();
	
	public int register(RegisterData registerData) throws SQLException
	{
		
		return registerDelegate.registerProcess(registerData);				
		
	}
	

}
