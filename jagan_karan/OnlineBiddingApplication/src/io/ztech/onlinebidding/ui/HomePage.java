package io.ztech.onlinebidding.ui;

/*
 * USERNAME PASSWORD
 * jagan13	13061998Kj@
 * vicky	Continuefb@13
 * sivesh	Sivesh@13
 * esakki	Esakki@13
 * joel		Joelking@13 
 * */
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;

public class HomePage {
	public static Logger logger = Logger.getLogger("HomePage");
	public static Scanner input = new Scanner(System.in);

	enum User {
		LOGIN, REGISTER, EXIT;
	}

	public static void showMenu() {
		String choice;
		RegisterDetails register = new RegisterDetails();
		LoginPage login = new LoginPage();
		try {
			do {
				logger.info(ConstantDisplayStatement.WELCOME);
				logger.info(ConstantDisplayStatement.LOGIN_OPTION);
				choice = input.nextLine();
				choice = choice.toUpperCase();
				switch (User.valueOf(choice)) {
				case LOGIN:
					login.getLoginDetails();
					break;
				case REGISTER:
					logger.info(ConstantDisplayStatement.REGISTER_PAGE);
					register.fetchRegisterDetails();
					break;
				default:
					logger.info(ConstantDisplayStatement.BYE);

				}
			} while (!User.valueOf(choice).equals(User.EXIT));
		} catch (Exception e) {
			logger.info(ConstantDisplayStatement.INVALID_LOGIN_OPTION);
			showMenu();
		}
	}

	public static void main(String[] args) {

		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");
		showMenu();
	}

}
