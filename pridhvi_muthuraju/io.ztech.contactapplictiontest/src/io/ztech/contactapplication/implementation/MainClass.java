package io.ztech.contactapplication.implementation;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contactapplication.beans.EmailDetails;
import io.ztech.contactapplication.beans.HomeDetails;
import io.ztech.contactapplication.beans.MobileDetails;
import io.ztech.contactapplication.beans.NameDetails;
import io.ztech.contactapplication.beans.OfficeDetails;
import io.ztech.contactapplication.constants.AppConstants;
import io.ztech.contactapplication.constants.SQLConstants;

public class MainClass {

	public static final Logger logger = Logger.getLogger(MainClass.class.getName());

	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(System.in);
			int option;
			EmailDetails emailDetails = new EmailDetails();
			HomeDetails homeDetails = new HomeDetails();
			MobileDetails mobileDetails = new MobileDetails();
			NameDetails nameDetails = new NameDetails();
			OfficeDetails officeDetails = new OfficeDetails();
			while (true) {
				logger.info(AppConstants.MENU);
				option = s.nextInt();
				ContactDAO cdao = new ContactDAOImpl();
				switch (option) {
				case 1: {
					cdao.addNewContact(nameDetails, emailDetails, homeDetails, mobileDetails, officeDetails);
					break;
				}
				case 2: {
					cdao.enterUpdateName(nameDetails, emailDetails);
					break;
				}
				case 3: {
					cdao.searchByFirstName(nameDetails, emailDetails, homeDetails, mobileDetails, officeDetails);
					break;
				}
				case 4: {
					cdao.display(SQLConstants.SELECT_ALL_CONTACTS, nameDetails, emailDetails);
					break;
				}
				case 5: {
					cdao.display(SQLConstants.SELECT_ALL_CONTACTS_FNAME, nameDetails, emailDetails);
					break;
				}
				case 6: {
					cdao.display(SQLConstants.SELECT_ALL_CONTACTS_LNAME, nameDetails, emailDetails);
					break;
				}
				case 7: {
					s.close();
					System.exit(0);
					break;
				}
				default: {
					logger.info(AppConstants.INVALID_INPUT);
				}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
