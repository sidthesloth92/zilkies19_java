package com.zilker.application.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.application.ConnectionConfig;
import com.zilker.application.beans.Contact;
import com.zilker.application.beans.Email;
import com.zilker.application.constants.Constants;
import com.zilker.application.constants.DisplayConstants;



public class ContactCreation extends ConnectionConfig {
	private static final Logger LOGGER = Logger.getLogger(ContactCreation.class.getName());
	static Scanner in = new Scanner(System.in);
	//Setting up the Connection from ConnectionConfig Class
	static Connection con = getConnection();
	
	public void insertingContact(Contact contact) {
		try {
			con.setAutoCommit(false);
			java.sql.Statement st = con.createStatement();
			PreparedStatement preparedStmt = con.prepareStatement(Constants.insertionIntoContactDetail);
			preparedStmt.setString(1, contact.name.getFirstName());
			preparedStmt.setString(2, contact.name.getLastName());
			executionResult(contact.name.getFirstName(), preparedStmt.execute());
			ResultSet rs = st.executeQuery(Constants.gettingID);
			//https://stackoverflow.com/questions/2120255/resultset-exception-before-start-of-result-set
			rs.next();
			int latestId = rs.getInt("MAX(CONTACT_ID)");
			insertingMobileNumber(latestId, contact);
			insertingHomeNumber(latestId, contact);
			insertingOfficeNumber(latestId, contact);
			insertingEmail(latestId, contact);
			con.commit();
			//Closing Connection
			closeConnection(con, preparedStmt);
		}catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LOGGER.info("Error Occured While inserting Contact");
			e.printStackTrace();
			
		}
		
	}
	

	//===============================
	//  Inserting Mobile Number
	//================================
	public void insertingMobileNumber(int latestId, Contact contact) {
		try {
			PreparedStatement preparedStmtMobileTable = con.prepareStatement(Constants.insertionIntoMobile);
			preparedStmtMobileTable.setInt(1, latestId);
			preparedStmtMobileTable.setInt(2, contact.mobile.getExtension());
			preparedStmtMobileTable.setString(3, contact.mobile.getNumber());
			executionResult(contact.name.getFirstName(), preparedStmtMobileTable.execute());
		}catch(Exception e) {
			LOGGER.info("insertion into Mobile Table has some issues !");
			e.printStackTrace();
		}
	}
	//===============================
	//    Inserting Home Number
	//===============================
	public void insertingHomeNumber(int latestId, Contact contact) {
		try {
			PreparedStatement preparedStmtHomeTable = con.prepareStatement(Constants.insertIntoHome);
			preparedStmtHomeTable.setInt(1, latestId);
			preparedStmtHomeTable.setInt(2, contact.home.getExtension());
			preparedStmtHomeTable.setInt(3, contact.home.getNumber());
			executionResult(contact.name.getFirstName(), preparedStmtHomeTable.execute());
		}catch(Exception e) {
			LOGGER.info("Insertion into Home Table has some issues !");
			e.printStackTrace();
		}
		
	}
	//===============================
	//  Inserting Office Number
	//================================
	public void insertingOfficeNumber(int latestId, Contact contact) {
		try {
			PreparedStatement preparedStmtOfficeTable = con.prepareStatement(Constants.insertIntoOffice);
			preparedStmtOfficeTable.setInt(1, latestId);
			preparedStmtOfficeTable.setInt(2, contact.office.getExtension());
			preparedStmtOfficeTable.setInt(3, contact.office.getNumber());
			executionResult(contact.name.getFirstName(), preparedStmtOfficeTable.execute());
		}catch(Exception e) {
			LOGGER.info("Insertion into Office Table has some issues !");
			e.printStackTrace();
		}
	}
	//===============================
	//    Inserting Email Number
	//===============================
	public void insertingEmail(int latestId, Contact contact) {
		Iterator<Email> itr = contact.emailArrayList.iterator();
		while(itr.hasNext()) {
			try {
				PreparedStatement preparedStmtEmailTable = con.prepareStatement(Constants.insertIntoEmail);
				preparedStmtEmailTable.setInt(1, latestId);
				preparedStmtEmailTable.setString(2, itr.next().getEmail());
				executionResult(contact.name.getFirstName(), preparedStmtEmailTable.execute());
			}catch(Exception e) {
				LOGGER.info("Insertion into Email Table has some issues !");
				e.printStackTrace();
			}
		}
		
	}
	
	
	// Adding Another Number for the Same CUST_ID
	public void addAnotherNumber(String firstName, String lastName) {
		//Displaying Contacts with the same contact Name with ID
		try {
			PreparedStatement preparedStmt = con.prepareStatement(Constants.searchingWithFirstName);
			preparedStmt.setString(1, firstName);
			ResultSet rs = preparedStmt.executeQuery();
			try {
				LOGGER.info("=========================");
				while(rs.next()) {
					LOGGER.info(rs.getInt("CONTACT_ID")+" ");
					LOGGER.info(rs.getString("FIRST_NAME"));
				}
				LOGGER.info("=========================");
			}catch(Exception e) {
				
			}
			LOGGER.info("Enter the Contact Id where You want to add the Number");
			int contactID = in.nextInt();
			LOGGER.info(DisplayConstants.ADD_ANOTHER_NUM_PRINT);
			int numberOption = in.nextInt();
			switch(numberOption) {
				case 1:
					addMobile(firstName, contactID);
					break;
				case 2:
					addOffice(firstName, contactID);
					break;
				case 3:
					addHome(firstName, contactID);
					break;
				case 4:
					addEmail(firstName, contactID);
					break;
				
			}
			// Closing Connection
			closeConnection(con, preparedStmt);
		}catch(Exception e) {
			LOGGER.info("An Error Occured Inside addAnotherNumber");
			e.printStackTrace();
		}
	}
	
	
	public void addMobile(String firstName, int contactID) {
		LOGGER.info("Enter the Extension Number");
		int mobileEx = in.nextInt();
		LOGGER.info("Enter the mobile number !");
		String mobileNumber = in.next();
		try {
			PreparedStatement preparedStmtMobile = con.prepareStatement(Constants.insertionIntoMobile);
			preparedStmtMobile.setInt(1, contactID);
			preparedStmtMobile.setInt(2, mobileEx);
			preparedStmtMobile.setString(3, mobileNumber);
			executionResult(firstName, preparedStmtMobile.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addEmail(String firstName, int contactID) {
		LOGGER.info("Enter your another email");
		String anotherEmail = in.next();
		try {
			PreparedStatement preparedStmtEmail = con.prepareStatement(Constants.insertIntoEmail);
			preparedStmtEmail.setInt(1, contactID);
			preparedStmtEmail.setString(2, anotherEmail);
			executionResult(firstName, preparedStmtEmail.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addHome(String firstName, int contactID) {
		LOGGER.info("Enter the Home Extension Number !");
		int homeExtension = in.nextInt();
		LOGGER.info("Enter the Office Number ");
		int homeNumberAgain = in.nextInt();
		try {
			PreparedStatement preparedStmtHome = con.prepareStatement(Constants.insertIntoHome);
			preparedStmtHome.setInt(1, contactID);
			preparedStmtHome.setInt(2, homeExtension);
			preparedStmtHome.setInt(3, homeNumberAgain);
			executionResult(firstName, preparedStmtHome.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addOffice(String firstName, int contactID) {
		LOGGER.info("Enter the Office Extension Number !");
		int officeExtension = in.nextInt();
		LOGGER.info("Enter the Office Number ");
		int officeNumberAgain = in.nextInt();
		try {
			PreparedStatement preparedStmtOffice = con.prepareStatement(Constants.insertIntoOffice);
			preparedStmtOffice.setInt(1, contactID);
			preparedStmtOffice.setInt(2, officeExtension);
			preparedStmtOffice.setInt(3, officeNumberAgain);
			executionResult(firstName, preparedStmtOffice.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void executionResult(String firstName, boolean result) {
		if(result == true) {
			LOGGER.info("Contact Details of "+firstName+" Not Saved");
		}else {
			LOGGER.info("Contact Details of "+firstName+" Saved");
		}
	}
}
