package io.zilker.contact.service;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.contact.constants.Applicationconstants;

public class UpdatePerson {
	static Scanner scanner = new Scanner(System.in);
	private static int optionInUpdate,choice,idOfUser;
	private static String firstName,lastName;
	public static Logger log = Logger.getLogger(UpdatePerson.class.getName());
	
	//add options redirect
	public static void addOptions(int userId,Connection con) throws SQLException {
		//1.add mobile 2.add email 3. home 4.office
		log.info(Applicationconstants.MODIFY_ADD_OPTIONS);
		choice = scanner.nextInt();
		switch(choice) {
		case 1:
			AddPerson.addMobile(userId,con);
			break;
		case 2:
			AddPerson.addMail(userId,con);
			break;
		case 3:
			AddPerson.addHome(userId,con);
			break;
		case 4:
			AddPerson.addOffice(userId,con);
			break;
		default:
			break;
		}
	}
	
	
	//displays options and redirect
	public static void displayOptions(int uid,Connection con) throws SQLException {
		// dispaly options as 1 for add 2. delete 3. modify
		log.info(Applicationconstants.MODIFY_DISPLAY_OPTIONS);
		optionInUpdate = scanner.nextInt();
		switch(optionInUpdate) {
		case 1:
			addOptions(uid,con);
			break;
		case 2:
			DeleteMain.chooseSpecific(uid,con);
			break;
		case 3:
			ModifyValues.modifyMainOptions(uid , con, false);
			break;
		default:
			break;
		}
		
	}
	
	//main in update
	public static void updateMain(Connection con) {
		try {
			//gets first and last name
			log.info(Applicationconstants.FIRST_NAME_ENTER);
			firstName = scanner.next();
			log.info(Applicationconstants.LAST_NAME_ENTER);
			lastName = scanner.next();
			if(!CheckExists.checkExists(firstName, lastName,con)) {
				//prompts no user
				log.info(Applicationconstants.USER_NOT_EXISTS);
				return;
			}
			//select id
			log.info(Applicationconstants.ENTER_ID);
			ListPersons.displayUsers(firstName, lastName,con);
			idOfUser = scanner.nextInt();
			displayOptions(idOfUser,con);
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}

}
