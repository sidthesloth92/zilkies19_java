package com.zilker.application.services;

import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.application.constants.DisplayConstants;
import com.zilker.application.dao.ContactUpdation;

public class Manipulation {
	private static final Logger LOGGER = Logger.getLogger(Manipulation.class.getName());
	Scanner in = new Scanner(System.in);
	ContactUpdation contactUpdation = new ContactUpdation();
	int CONT_ID;
	
	public void contactToUpdate() {
		boolean isPresent = false;
		LOGGER.info("Enter the First Name !");
		String firstName = in.next();
		LOGGER.info("Enter the Last Name !");
		String lastName = in.next();
		ResultSet rs = contactUpdation.displayPresentContact(firstName, lastName);
		try {
			while(rs.next()) {
				isPresent = true;
				LOGGER.info(rs.getInt("CONTACT_ID")+" ");
				LOGGER.info(rs.getString("FIRST_NAME"));
			}
			if(!isPresent) {
				LOGGER.info("No Contact with the given Number is Found");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Enter the Contact ID you want to edit ");
		CONT_ID = in.nextInt();
		LOGGER.info(DisplayConstants.WHAT_TO_EDIT);
		int option = in.nextInt();
		switch(option) {
			case 1:
				mobileManipulation(contactUpdation.updatingMobile(CONT_ID, firstName, lastName));
				break;
			case 2:
				homeManipulation(contactUpdation.updatingHome(CONT_ID, firstName, lastName));
				break;
			case 3:
				officeManipulation(contactUpdation.updatingOffice(CONT_ID, firstName, lastName));
				break;
			case 4:
				emailManipulation(contactUpdation.updatingEmail(CONT_ID, firstName, lastName));
				break;
		}
	}
	
	public void mobileManipulation(ResultSet rs) {
		try {
			while(rs.next()) {
				String extNumber = rs.getString("EXTENSION");
				String mobileNumber = rs.getString("MOB_NUMBER");
				int MOBILE_ID = rs.getInt("ID");
				LOGGER.info(" :" +extNumber+"  " +mobileNumber+" The Mobile ID for this is "+MOBILE_ID);
			}
		}catch(Exception e) {
			LOGGER.info("Error While Manipulating Mobile");
		}
		LOGGER.info("Enter the ID you want to Edit");
		int MOBILE_ID_TO_CHANGE = in.nextInt();
		LOGGER.info("Enter the Updated Extension ");
		int updatedExtension = in.nextInt();
		LOGGER.info("Enter the Updated Mobile Number");
	    String updatedNumber = in.next();
	    contactUpdation.changeMobile(updatedExtension, updatedNumber, CONT_ID, MOBILE_ID_TO_CHANGE);
	}
	
	public void homeManipulation(ResultSet rs) {
		try {
			while(rs.next()) {
				String extNumber = rs.getString("AREA_CODE");
				String mobileNumber = rs.getString("HOME_NUMBER");
				int MOBILE_ID = rs.getInt("ID");
				LOGGER.info(": " +extNumber+"  " +mobileNumber+" The Home ID for this is "+MOBILE_ID);
			}
		}catch(Exception e) {
			LOGGER.info("Error While Manipulating Home Number");
		}
		LOGGER.info("Enter the ID you want to Edit");
		int MOBILE_ID_TO_CHANGE = in.nextInt();
		LOGGER.info("Enter the Updated Extension ");
		int updatedExtension = in.nextInt();
		LOGGER.info("Enter the Updated Home Number");
	    String updatedNumber = in.next();
	    contactUpdation.changeHome(updatedExtension, updatedNumber, CONT_ID, MOBILE_ID_TO_CHANGE);
	}
	
	public void officeManipulation(ResultSet rs) {
		try {
			while(rs.next()) {
				String extNumber = rs.getString("OFF_EXT");
				String mobileNumber = rs.getString("OFF_NUMBER");
				int OFFICE_ID = rs.getInt("ID");
				LOGGER.info(" : " +extNumber+"  " +mobileNumber+" The Office ID for this is "+OFFICE_ID);
			}
		}catch(Exception e) {
			LOGGER.info("Error While Manipulating Office Number");
		}
		LOGGER.info("Enter the ID you want to Edit");
		int MOBILE_ID_TO_CHANGE = in.nextInt();
		LOGGER.info("Enter the Updated Extension ");
		int updatedExtension = in.nextInt();
		LOGGER.info("Enter the Updated Office Number");
	    String updatedNumber = in.nextLine();
	    contactUpdation.changeOffice(updatedExtension, updatedNumber, CONT_ID, MOBILE_ID_TO_CHANGE);
	}
	
	public void emailManipulation(ResultSet rs) {
		try {
			while(rs.next()) {
				String email_address = rs.getString("EMAIL");
				int EMAIL_ID = rs.getInt("ID");
				LOGGER.info("  " +email_address+" The Email ID for this is "+EMAIL_ID);
			}
		}catch(Exception e) {
			LOGGER.info("Error While Manipulating Email");
		}
		LOGGER.info("Enter the ID you want to Edit");
		int MOBILE_ID_TO_CHANGE = in.nextInt();
		LOGGER.info("Enter the Updated Email");
	    String updatedEmail = in.next();
	    contactUpdation.changeEmail(updatedEmail, CONT_ID, MOBILE_ID_TO_CHANGE);
	}
	
	
}
