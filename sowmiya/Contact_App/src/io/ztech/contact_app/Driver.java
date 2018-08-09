package io.ztech.contact_app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.result.UpdatableResultSet;

import io.ztech.contact_app.bean.ContactDetail;
import io.ztech.contact_app.constants.ApplicationConstants;
import io.ztech.contact_app.services.AddContact;
import io.ztech.contact_app.services.EditContact;
import io.ztech.contact_app.services.UpdateContact;
import io.ztech.contact_app.services.ViewContact;

public class Driver {

	public static Scanner scan = new Scanner(System.in);
	public static Logger log = Logger.getLogger("Driver.class");

	public static void main(String args[]) throws SQLException {
		int operationChoice;
		char continueChoice;
		ContactDetail contact = new ContactDetail();
		try {
			do {
				log.info(ApplicationConstants.MENU);
				operationChoice = scan.nextInt();
				// scan.nextLine();
				switch (operationChoice) {
				case 1:
					AddContact newContact = new AddContact();
					newContact.newContactDetails(contact);
					break;
				case 2:
					EditContact edit = new EditContact();
					edit.editDetails(contact);
					break;

				case 3:
					ViewContact viewContact = new ViewContact();
					viewContact.viewContactDetails();
					break;

				case 4:
					UpdateContact update = new UpdateContact();
					update.updateContact(contact);
					break;
				default:
					log.info(ApplicationConstants.WRONG_CHOICE);
					break;
				}
				log.info(ApplicationConstants.CONTINUE_CHOICE);
				continueChoice = scan.next().charAt(0);

			} while (continueChoice == 'Y');
		} catch (InputMismatchException e) {
			log.info(ApplicationConstants.Is_VALID);
		}
	}

}
