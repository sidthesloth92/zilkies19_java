package com.zilker.application.services;


import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.application.beans.Contact;
import com.zilker.application.beans.Email;
import com.zilker.application.constants.Constants;
import com.zilker.application.constants.DisplayConstants;
import com.zilker.application.dao.ContactCreation;
import com.zilker.application.utils.Validation;

public class Creation {

	Scanner in = new Scanner(System.in);
	private static final Logger LOGGER = Logger.getLogger(Display.class.getName());
	ContactCreation contactCreation = new ContactCreation();
	public void showingOption(String firstName, String lastName) {
		LOGGER.info(DisplayConstants.OPTION_ALREADY_EXISTS);
		int option = in.nextInt();
		switch(option) {
		case 1:
			contactCreationServices();
			break;
		case 2:
			contactCreation.addAnotherNumber(firstName, lastName);
			break;
		}
	}
	
	public void contactCreationServices() {
		Contact contact = new Contact();
		LOGGER.info("Enter the First Name !");
		String firstName = in.nextLine();
		while(!Validation.isValid(firstName, Constants.NAME_REG_EX)) {
			LOGGER.info("You Have entered an Invalid Name");
			LOGGER.info("Enter Again");
			firstName = in.nextLine();
		}
		contact.name.setFirstName(firstName);
		LOGGER.info("Enter the Last Name !");
		String lastName = in.nextLine();
		while(!Validation.isValid(lastName, Constants.NAME_REG_EX)) {
			LOGGER.info("You Have entered an Invalid Last Name");
			LOGGER.info("Enter Again");
			lastName = in.nextLine();
		}
		contact.name.setLastName(lastName);
		//Checking if the first name and last name already exists !
		if(Validation.ifExists(firstName, lastName)) {
			showingOption(firstName, lastName);
		}else {
			LOGGER.info("Enter the Office Extension ");
			int officeEx = in.nextInt();
			while(!Validation.isValid(String.valueOf(officeEx), Constants.LAND_LINE_EX)) {
				LOGGER.info("You Have entered an Invalid Office Extension");
				LOGGER.info("Enter Again");
				officeEx = in.nextInt();
			}
			contact.office.setExtension(officeEx);
			
			LOGGER.info("Enter the Office Number !");
			int officeNumber = in.nextInt();
			while(!Validation.isValid(String.valueOf(officeNumber), Constants.LANDLINE_PATTERN)) {
				LOGGER.info("You Have entered an Invalid Office Number");
				LOGGER.info("Enter Again");
				officeNumber = in.nextInt();
			}
			contact.office.setNumber(officeNumber);
			
			LOGGER.info("Enter the Home Extension !");
			int homeExtension = in.nextInt();
			while(!Validation.isValid(String.valueOf(homeExtension), Constants.LAND_LINE_EX)) {
				LOGGER.info("You Have entered an Invalid Home Extension");
				LOGGER.info("Enter Again");
				homeExtension = in.nextInt();
			}
			contact.home.setExtension(homeExtension);
			
			LOGGER.info("Enter the Home Number !");
			int homeNumber = in.nextInt();
			while(!Validation.isValid(String.valueOf(homeNumber), Constants.LANDLINE_PATTERN)) {
				LOGGER.info("You Have entered an Invalid Home Number");
				LOGGER.info("Enter Again");
				homeNumber = in.nextInt();
			}
			contact.home.setNumber(homeNumber);
			
			LOGGER.info("Enter the Extension Number");
			int extension = in.nextInt();
			while(!Validation.isValid(String.valueOf(extension), Constants.MOBILE_EX)) {
				LOGGER.info("You Have entered an Invalid Mobile Extension");
				LOGGER.info("Enter Again");
				extension = in.nextInt();
			}
			contact.mobile.setExtension(extension);
			
			LOGGER.info("Enter your mobile Number !");
			String mobNumber = in.next();
				
			while(!Validation.isValid(mobNumber, Constants.MOB_PATTERN)) {
				LOGGER.info("You have entered an Invalid Mobile Number !,");
				LOGGER.info("Enter Again");
				mobNumber = in.next();
			}
			contact.mobile.setNumber(mobNumber);
		
			int loopEnding;
			boolean emailCondition = true;
			while(emailCondition) {
				LOGGER.info("Enter your Email Address !");
				Email emailObj = new Email();
				String email = in.next();
				while(Validation.isValid(email, Constants.E_PATTERN)) {
					LOGGER.info("Enter a Valid Email !");
					email = in.next();
				}
				emailObj.setEmail(email);
				contact.emailArrayList.add(emailObj);
				LOGGER.info("Enter -1 if you want to stop adding Email");
				loopEnding = in.nextInt();
				if(loopEnding == -1) {
					emailCondition = false;
				}
			}
			//Inserting Into Data Base
			contactCreation.insertingContact(contact);
		}
	}
	
	
}
