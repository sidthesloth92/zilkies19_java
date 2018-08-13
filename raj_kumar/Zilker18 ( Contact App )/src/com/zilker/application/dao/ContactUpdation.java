package com.zilker.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.application.ConnectionConfig;
import com.zilker.application.utils.Validation;
import com.zilker.application.constants.Constants;
import com.zilker.application.services.Manipulation;

public class ContactUpdation extends ConnectionConfig {
	private static final Logger LOGGER = Logger.getLogger(ContactUpdation.class.getName());
	static Scanner in = new Scanner(System.in);
	Validation validation = new Validation();
	ContactCreation contactCreation = new ContactCreation();
	Manipulation manipulate = new Manipulation();
	static Connection con = getConnection();
	
	//https://alvinalexander.com/java/java-mysql-update-query-example
	public void updateContact() {
		boolean isPresent = false;
		LOGGER.info("Enter the First Name Of the Contact You Want to Update");
		String firstName = in.next();
		LOGGER.info("Enter the Last Name of the Conatct You Want to Update ");
		String lastName = in.next();
		try {
			PreparedStatement preparedStmt = con.prepareStatement(Constants.selectByFirstAndLast);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			ResultSet rs = preparedStmt.executeQuery();
			while(rs.next()) {
				isPresent = true;
				LOGGER.info(rs.getInt("CONTACT_ID")+" ");
				LOGGER.info(rs.getString("FIRST_NAME"));
			}
			if(!isPresent) {
				LOGGER.info("No Contact with the given Number is Found");
			}
			LOGGER.info("Enter the Contact ID you want to edit ");
			int CONT_ID = in.nextInt();
			int option = gettingWhatToEdit();
			switch(option) {
				case 1:
					updatingMobile(CONT_ID, firstName, lastName);
					break;
				case 2:
					updatingMobile(CONT_ID, firstName, lastName);
					break;
				case 3:
					updatingOffice(CONT_ID, firstName, lastName);
					break;
				case 4:
					updatingEmail(CONT_ID, firstName, lastName);
					break;
			}
		}catch(Exception e) {
			LOGGER.info("An Error Inside updateContact()");
		}
	}
	
	
	public void updatingMobile(int CONT_ID, String firstName, String lastName) {
		try {
			PreparedStatement preparedStmtMobile = con.prepareStatement(Constants.mobileDisplay);
			preparedStmtMobile.setInt(1, CONT_ID);
			ResultSet rsMobile = preparedStmtMobile.executeQuery();
			LOGGER.info("the Mobile Number that "+firstName+" "+lastName+" has are: ");
			manipulate.mobileManipulation(rsMobile);
			LOGGER.info("Enter the Mobile Id that you want to change !");
			int MOBILE_ID_TO_CHANGE = in.nextInt();
			LOGGER.info("Enter the Updated Extension ");
			int updatedExtension = in.nextInt();
			LOGGER.info("Enter the Updated Mobile Number");
		    String updatedNumber = in.next();
			PreparedStatement preparedStmtUpdateMob = con.prepareStatement(Constants.updateMobileNumber);
			preparedStmtUpdateMob.setInt(1, updatedExtension);
			preparedStmtUpdateMob.setString(2, updatedNumber);
			preparedStmtUpdateMob.setInt(3, CONT_ID);
			preparedStmtUpdateMob.setInt(4, MOBILE_ID_TO_CHANGE);
			contactCreation.executionResult(firstName, preparedStmtUpdateMob.execute());
		}catch(Exception e) {
			LOGGER.info("An Error Occured During Updating Mobile Number");
		}
	}
	
	//=============================
	// Updating the Home Number
	//=============================
	public void updatingHome(int CONT_ID, String firstName, String lastName) {
		try {
			PreparedStatement preparedStmtHome = con.prepareStatement(Constants.homeDisplay);
			preparedStmtHome.setInt(1, CONT_ID);
			ResultSet rsHome = preparedStmtHome.executeQuery();
			LOGGER.info("the Mobile Number that "+firstName+" "+lastName+" has are: ");
			manipulate.homeManipulation(rsHome);
			LOGGER.info("Enter the Home Id that you want to change !");
			int HOME_ID_TO_CHANGE = in.nextInt();
			LOGGER.info("Enter the Updated Area Code ");
			int updatedExtensionHome = in.nextInt();
			LOGGER.info("Enter the Updated Home Number");
		    String updatedNumberHome = in.next();
			PreparedStatement preparedStmtUpdateHome = con.prepareStatement(Constants.updateHomeNumber);
			preparedStmtUpdateHome.setInt(1, updatedExtensionHome);
			preparedStmtUpdateHome.setString(2, updatedNumberHome);
			preparedStmtUpdateHome.setInt(3, CONT_ID);
			preparedStmtUpdateHome.setInt(4, HOME_ID_TO_CHANGE);
			contactCreation.executionResult(firstName, preparedStmtUpdateHome.execute());
		}catch(Exception e) {
			LOGGER.info("Error During Updating Home ");
		}
	}
	//=============================
	// Updating the Office Number
	//=============================
	public void updatingOffice(int CONT_ID, String firstName, String lastName) {
		try {
			PreparedStatement preparedStmtOffice = con.prepareStatement(Constants.officeDisplay);
			preparedStmtOffice.setInt(1, CONT_ID);
			ResultSet rsOffice = preparedStmtOffice.executeQuery();
			LOGGER.info("the Office Number that "+firstName+" "+lastName+" has are: ");
			manipulate.officeManipulation(rsOffice);
			LOGGER.info("Enter the Office Id that you want to change !");
			int OFFICE_ID_TO_CHANGE = in.nextInt();
			LOGGER.info("Enter the Updated Area Code ");
			int updatedExtensionOffice = in.nextInt();
			LOGGER.info("Enter the Updated Office Number");
		    String updatedNumberOffice = in.next();
			PreparedStatement preparedStmtUpdateOffice = con.prepareStatement(Constants.updateOfficeNumber);
			preparedStmtUpdateOffice.setInt(1, updatedExtensionOffice);
			preparedStmtUpdateOffice.setString(2, updatedNumberOffice);
			preparedStmtUpdateOffice.setInt(3, CONT_ID);
			preparedStmtUpdateOffice.setInt(4, OFFICE_ID_TO_CHANGE);
			contactCreation.executionResult(firstName, preparedStmtUpdateOffice.execute());
		}catch(Exception e) {
			LOGGER.info("Error During Updating Office");
		}
	}
	
	public void updatingEmail(int CONT_ID, String firstName, String lastName) {
		try {
			PreparedStatement preparedStmtEmail = con.prepareStatement(Constants.emailDisplay);
			preparedStmtEmail.setInt(1, CONT_ID);
			ResultSet rsEmail = preparedStmtEmail.executeQuery();
			LOGGER.info("The Email Address that "+firstName+" "+lastName+" has are: ");
			manipulate.emailManipulation(rsEmail);
			LOGGER.info("Enter the Email Id that you want to change !");
			int EMAIL_ID_TO_CHANGE = in.nextInt();
			LOGGER.info("Enter the Updated Email ");
			String updatedEmail = in.next();
			PreparedStatement preparedStmtUpdateEmail = con.prepareStatement(Constants.updateEmail);
			preparedStmtUpdateEmail.setString(1, updatedEmail);
			preparedStmtUpdateEmail.setInt(2, CONT_ID);
			preparedStmtUpdateEmail.setInt(3, EMAIL_ID_TO_CHANGE);
			contactCreation.executionResult(firstName, preparedStmtUpdateEmail.execute());
		}catch(Exception e) {
			LOGGER.info("Error During Updating Email");
		}
	}
	
	
	

	public static int gettingWhatToEdit() {
		LOGGER.info("Enter What you want to Edit! \n1. Mobile Number\n2. Home Number\n3. Office Number\n4. Email");
		int option = in.nextInt();
		return option;
	}
}
