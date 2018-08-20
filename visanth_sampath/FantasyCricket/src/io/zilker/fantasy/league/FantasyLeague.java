package io.zilker.fantasy.league;

import java.util.*;
import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.constants.DisplayConstants;
import io.zilker.fantasy.ui.AdminUI;
import io.zilker.fantasy.ui.UserUI;
import io.zilker.fantasy.utility.UserValidator;

public class FantasyLeague {
	static AdminUI printer = new AdminUI();
	private static int choice;
	private static boolean isLoggedIn = false;
	static User user = new User();
	private static Scanner scanner = new Scanner(System.in);

	// main starting of the program
	public static void main(String[] args) {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");
		UserValidator userValidator = new UserValidator();
		do {
			printer.displayAlert(DisplayConstants.STARTING_OPTIONS);
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				user = userValidator.enterLoginDetails();
				if (user.getUserId() == 0) {
					printer.displayAlert(DisplayConstants.INVALID_CREDENTIALS);
					return;
				}
				isLoggedIn = true;
				printer.displayAlert(DisplayConstants.LOGIN_SUCESS);
				break;
			case 2:
				userValidator.enterSignUpDetails();
				printer.displayAlert(DisplayConstants.SIGNUP_SUCESS);
				break;
			default:
				break;
			}
		} while (!isLoggedIn);

		if (user.getUserType() == 2) {
			new AdminUI().displayAdminOperations();
		} else if (user.getUserType() == 1) {
			new UserUI().userMainMenu(user);
		}

	}

}
