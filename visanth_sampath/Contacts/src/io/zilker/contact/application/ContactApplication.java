package io.zilker.contact.application;

import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.contact.DAO.DbUtils;
import io.zilker.contact.constants.Applicationconstants;

public class ContactApplication {
	
	private static Scanner scanner = new Scanner(System.in);
	public static Logger log = Logger.getLogger(ContactApplication.class.getName());
	
	
	public static void main(String [] args) {
		try {
			log.info(Applicationconstants.CONTACT_MAIN_CHOICE);
			int option = scanner.nextInt();
			switch(option) {
			case 1:DbUtils.addPersonConnection();
				break;
			case 2:DbUtils.removePersonConnection();
				break;
			case 3:DbUtils.listPeronsConnection();
				break;
			case 4: DbUtils.updatePersonConnection();
				break;
			default:
				break;
			}
			//AddPerson.addMain(con);
			
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		finally {
			DbUtils.closeConnection();
		}
	}
	

}
