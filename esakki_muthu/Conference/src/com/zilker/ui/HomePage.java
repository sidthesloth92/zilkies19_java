package com.zilker.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.bean.LoginData;
import com.zilker.bean.RegisterData;
import com.zilker.constants.StringConstants;
import com.zilker.util.Inputs;
import com.zilker.constants.OptionConstants;

public class HomePage {

	static Logger logger = Logger.getLogger(HomePage.class.getName());

	static Scanner scanner = new Scanner(System.in);

	static LoginData loginData = new LoginData();

	static RegisterData registerData = new RegisterData();
	
	static Inputs inputs = new Inputs();

	public void HomeOptions() {

		//logger.info(StringConstants.APP_NAME);

		int option;

		logger.info(StringConstants.CHOOSE_OPTION);

		while (true) {

			logger.info(OptionConstants.LOGIN_OPTIONS);

			option = inputs.numberInput();

			switch (option) {

			case 1:
				
				
				Login login = new Login();

				login.userLogin(loginData);

				break;

			case 2:

				Register register = new Register();

				register.registerUser(registerData);

				break;

			}

		}
	}

}
