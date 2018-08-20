package io.ztech.cricketapp;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.UserEntryOptions;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.ui.Menu;
import io.ztech.cricketapp.ui.UserEntry;

public class Main {
	Scanner scanner;
	User user;
	UserEntry userEntry;
	Logger logger;
	
	public Main() {
		logger = Logger.getLogger(UserEntry.class.getName());
		scanner = new Scanner(System.in);
		user = new User();
		userEntry = new UserEntry();
	}
	
	public static void main(String[] args) {
		System.setProperty("java.util.logging.SimpleFormatter.format",
                "%5$s%6$s%n");
		Main main = new Main();
		main.logger.info(UserMessages.APP_TITLE);
		char retry;
		do {
			try {
				main.logger.info(UserMessages.USER_ENTRY);
				UserEntryOptions option = UserEntryOptions.values()[main.scanner.nextInt() - 1];
				retry = 'n';
				switch (option) {
				case SIGN_UP:
					main.userEntry.signUp();
				case SIGN_IN:
					main.user = main.userEntry.login();
					break;
				default:
					main.logger.info(UserMessages.INVALID_CHOICE);
					retry = 'y';
				}
			} catch (InputMismatchException e) {
				main.logger.info(UserMessages.INCORRECT_INPUT + e);
				retry = 'y';
				main.scanner.nextLine();
			}
		} while (Character.toLowerCase(retry) == 'y');
		
		Menu menuManager = new Menu(main.user);
		menuManager.showMainMenu();
	}
}
