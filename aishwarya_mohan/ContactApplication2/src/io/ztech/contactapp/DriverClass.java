package io.ztech.contactapp;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contactapp.constants.ApplicationStringConstants;
import io.ztech.contactapp.service.ExecuteFeatures;

public class DriverClass {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final ExecuteFeatures obj = new ExecuteFeatures();
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String args[]) {
		try {
			do {
				logger.info(ApplicationStringConstants.MENU_DISPLAY);
				logger.info(ApplicationStringConstants.MENU_CHOICE_ENQUIRY);

				int choice = sc.nextInt();

				switch (choice) {
				case 1:// create contact - single entry for each field
					obj.createContact();
					break;

				case 2:// update contact - insert non existing fields
					obj.updateContact();
					break;

				case 3:// edit contact - alter existing fields
					obj.editContact();
					break;

				case 4:// search contact - display a particular contact
					obj.searchContact();
					break;

				case 5:// display contacts - sort and display all contacts
					obj.displayContact();
					break;

				default:
					logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
				}

				logger.info(ApplicationStringConstants.EXIT_APPLICATION);
			} while (sc.next().charAt(0) == 'n');
		} catch (Exception e) {
			logger.info(e + "");
		} finally {
			sc.close();
		}
	}
}
