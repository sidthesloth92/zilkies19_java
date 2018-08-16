package com.zilker.application;

import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.application.constants.Constants;
import com.zilker.application.services.Creation;
import com.zilker.application.services.Display;
import com.zilker.application.services.Manipulation;
import com.zilker.application.constants.DisplayConstants;

enum Menu {
	CREATE, UPDATE, SORTBYFIRST, SORTBYLAST, END;
}

public class MenuInterface {
	static Display display = new Display();
	static Creation creation = new Creation();
	static Constants constant = new Constants();
	static Manipulation manipulation = new Manipulation();
	private static final Logger LOGGER = Logger.getLogger(MenuInterface.class.getName());
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		boolean displayMenu = true;
		while(displayMenu) {
			LOGGER.info(DisplayConstants.MENU_DISPLAY_OPTION);
			int option = in.nextInt();
			switch(option) {
				case 1:
					creation.contactCreationServices();
					break;
				case 2:
					manipulation.contactToUpdate();
					break;
				case 3:
					display.dsiplaySortFirstName();
					break;
				case 4:
					display.dsiplaySortLastName();
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
