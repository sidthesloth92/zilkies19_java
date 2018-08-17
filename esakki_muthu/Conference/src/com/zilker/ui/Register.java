package com.zilker.ui;

import java.util.logging.Logger;

import com.zilker.bean.RegisterData;
import com.zilker.constants.StringConstants;
import com.zilker.dao.AddNewUser;
import com.zilker.service.RegisterService;
import com.zilker.util.Inputs;

public class Register {

	Logger logger=Logger.getLogger(Register.class.getName());
	
	Inputs inputs=new Inputs();
	
	AddNewUser addNewUser=new AddNewUser();
	
	RegisterService registerService = new RegisterService();
	
	public void registerUser(RegisterData registerData) {
		
		logger.info(StringConstants.ENTER_USER_NAME);
		
		registerData.setUserName(inputs.get_User_name());
		
		logger.info(StringConstants.ENTER_EMAIL);
		
		registerData.setEmail(inputs.getEmail());
		
		logger.info(StringConstants.ENTER_PHONE_NO);
		
		registerData.setPhoneNo(inputs.getPhone());
		
		logger.info(StringConstants.ENTER_PASSWORD);
		
		registerData.setPassword(inputs.getPassword());
		
		logger.info(StringConstants.ENTER_CONFIRM_PASSWORD);
		
		registerData.setConfirmPassword(inputs.getConfirmPassword());	
		
		int result = registerService.register(registerData);
		
		if(result == 1) {
			
			logger.info(StringConstants.REGISTER_SUCCESS);
			
		}else {
			
			logger.info(StringConstants.REGISTER_FAILED);
			
		}
				
					
	}
		
	
}
