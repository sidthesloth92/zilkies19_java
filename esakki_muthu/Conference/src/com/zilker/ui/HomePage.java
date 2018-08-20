package com.zilker.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.bean.LoginData;
import com.zilker.bean.RegisterData;
import com.zilker.constants.StringConstants;
import com.zilker.util.Inputs;
import com.zilker.constants.OptionConstants;

public class HomePage {

	static Logger logger = Logger.getLogger(HomePage.class.getName());

	Scanner scanner = new Scanner(System.in);

	static LoginData loginData = new LoginData();

	static RegisterData registerData = new RegisterData();

	static Inputs inputs = new Inputs();

	public void HomeOptions() {

		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd");
		String date2 = simple.format(date);
		simple = new SimpleDateFormat("yyyy");		
		int yr = Integer.parseInt(simple.format(date));
		simple = new SimpleDateFormat("MM");
		int month = Integer.parseInt(simple.format(date));
		simple = new SimpleDateFormat("dd");
		int day = Integer.parseInt(simple.format(date));
		date.setYear(yr);
		date.setMonth(month);
		date.setDate(day);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);		
			
		//System.out.println(date2);
		Date date1 = new Date(2018,8,20);
		date1.setMinutes(0);
		date1.setHours(0);
		date1.setSeconds(0);
		
		System.out.println(date1.getTime()-date.getTime());
		// logger.info(StringConstants.APP_NAME);

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
