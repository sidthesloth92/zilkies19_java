package com.zilker.application;

import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.application.constants.Constants;
import com.zilker.application.dao.ContactCreation;
import com.zilker.application.dao.ContactDisplay;
import com.zilker.application.dao.ContactUpdation;
import com.zilker.application.constants.DisplayConstants;

enum Menu {
	CREATE, UPDATE, SORTBYFIRST, SORTBYLAST, END;
}

public class MenuInterface {
	static ContactDisplay contactDisplay = new ContactDisplay();
	static ContactCreation contactCreation = new ContactCreation();
	static ContactUpdation contactManipulation = new ContactUpdation();
	static Constants constant = new Constants();
	private static final Logger LOGGER = Logger.getLogger(MenuInterface.class.getName());
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		boolean displayMenu = true;
		while(displayMenu) {
			LOGGER.info(DisplayConstants.displayMenuOption);
			int option = in.nextInt();
			switch(option) {
				case 1:
					contactCreation.creatingContact();
					break;
				case 2:
					contactManipulation.updateContact();
					break;
				case 3:
					contactDisplay.sortByFirstName();
					break;
				case 4:
					contactDisplay.sortByLastName();
					break;
				case 5:
					displayMenu = false;
				default: 
					break;
			}
		}
		in.close();
	}
}
