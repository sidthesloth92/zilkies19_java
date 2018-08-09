package io.ztech.contacts.services;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contacts.constants.DisplayConstants;

//displays menu
public class ContactsMenu {
	static Scanner in = new Scanner(System.in);
	static DisplayConstants d = new DisplayConstants();
	static Logger LOGGER = Logger.getLogger(ContactsMenu.class.getName());
	
	public static void showMenu() {
		try {
			LOGGER.info(d.MENU);
			int choice = in.nextInt();
			switch (choice) {
			case 1:
				AddDetails.addEntireContact();
				break;
			case 2:
				ViewDetails.viewDetails();
				break;
			case 3:
				SearchDetails.searchDetails();
				break;
			case 4:
				AddDetails.addAttr();
				break;
			case 5:
				UpdateDetails.updateDetails();
				break;
			case 6:
				DeleteDetails.deleteDetails();
				break;
			case 7:
				System.exit(0);
				break;
			default: {
				LOGGER.info(d.INVALID_CHOICE);
				showMenu();
				return;
			}
			}
			showMenu();
		} catch (InputMismatchException e) {
			LOGGER.info(d.INVALID_CHOICE);
			in.nextLine();
			showMenu();
			return;
		}
	}

}
