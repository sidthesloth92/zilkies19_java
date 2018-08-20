package io.ztech.onlinebidding.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.onlinebidding.bean.CustomerDetail;
import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.services.LoginUserData;

public class LoginPage implements ConstantDisplayStatement {
	CustomerDetail customerDetails = new CustomerDetail();
	Scanner input = new Scanner(System.in);
	LoginUserData loginData = new LoginUserData();
	public static Logger logger = Logger.getLogger("LoginPage");
	UserPage userPage = new UserPage();
	AdminPage adminPage = new AdminPage();

	public void getLoginDetails() {
		logger.info(LOGIN_PAGE);
		logger.info(ENTER_USERNAME);
		customerDetails.setUserName(input.nextLine());
		logger.info(ENTER_PASSWORD);
		customerDetails.setPassword(input.nextLine());
		String enteredPassword = customerDetails.getPassword();
		customerDetails = loginData.LoginUser(customerDetails);
		if (customerDetails.getPassword().equals(enteredPassword)
				&& customerDetails.getTypeOfUser().equals("customer")) {
			userPage.customerOptions(customerDetails.getUserName());
		} else if (customerDetails.getPassword().equals(enteredPassword)
				&& customerDetails.getTypeOfUser().equals("admin")) {
			adminPage.AdminOption();
		} else {
			logger.info(INVALID_CREDENTIALS);
			getLoginDetails();
		}
	}
}
