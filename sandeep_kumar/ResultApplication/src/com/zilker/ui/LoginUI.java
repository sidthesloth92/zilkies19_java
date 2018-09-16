package com.zilker.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.delegates.Validator;
import com.zilker.services.*;
import com.zilker.beans.LoggedInUserData;
import com.zilker.constants.*;

public class LoginUI {

	public static Scanner in = new Scanner(System.in);
	private static final Logger LOGGER = Logger.getLogger(LoginUI.class.getName());
	public boolean proceed;
	public int option;
	public String getInput;
	StudentUI studentui = new StudentUI();
	FacultyUI facultyui = new FacultyUI();
	AdminUI adminui = new AdminUI();

	public String scanInput(String displayMessage, String regex, String errorMessage) {
		LOGGER.info(displayMessage);
		proceed = true;
		do {
			getInput = in.nextLine();
			if(getInput.equals("-1")) {
				proceed = false;continue;
			}
			if (Validator.validate(getInput, regex)) {
				proceed = false;
			} else {
				System.err.println(errorMessage);
			}
		} while (proceed);
		return getInput;
	}

	public void login() {
		LoggedInUserData currentUser= new LoggedInUserData();
		LOGGER.info("LOGIN PAGE");
		do {
			long registrationNumber = Long.parseLong(this.scanInput(StringConstants.ENTER_REGNO,"[0-9]+", StringConstants.INVALID_REGNO));
			String password = this.scanInput(StringConstants.ENTER_PASS,"[a-zA-Z0-9]+", StringConstants.INVALID_PASS);
			LoginServices loginService = new LoginServices();
			currentUser = loginService.isValidUser(registrationNumber, password);
			if (currentUser.getRole().equals("student")) {
				studentui.studentMenu(currentUser);
			} else if (currentUser.getRole().equals("faculty")) {
				facultyui.facultyMenu(currentUser);
			} else if (currentUser.getRole().equals("admin")) {
				adminui.adminMenu();
			} else {
				System.err.println("Invalid Login Credentials!");
			}
		} while (true);
	}
}
