package com.zilker.application.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.zilker.application.ConnectionConfig;
import com.zilker.application.constants.Constants;

public class ContactDisplay extends ConnectionConfig {
	//Setting up the Connection from ConnectionConfig Class
	private static final Logger LOGGER = Logger.getLogger(ContactDisplay.class.getName());
	Constants constant = new Constants();
	static Connection con = getConnection();
	//=======================
	// Sorting By First Name
	//=======================
	public void sortByFirstName() {
		try {
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(constant.sortByFirstName);
			while(rs.next()) {
				int CONT_ID = rs.getInt("CONTACT_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				LOGGER.info("Name is: " +firstName);
				LOGGER.info(" " +lastName);
				displayMobile(CONT_ID, firstName, lastName);
				displayHomeNumber(CONT_ID, firstName, lastName);
				displayOfficeNumber(CONT_ID, firstName, lastName);
				displayEmail(CONT_ID, firstName, lastName);
			}
		}catch(Exception e) {
			LOGGER.info("Dont Worry An Error Occured ");
			e.printStackTrace();
		}
	}
	
	public void sortByLastName() {
		try {
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(constant.sortByLastName);
			while(rs.next()) {
				int CONT_ID = rs.getInt("CONTACT_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				LOGGER.info("Name is  " +firstName);
				LOGGER.info(" " +lastName);
				displayMobile(CONT_ID, firstName, lastName);
				displayHomeNumber(CONT_ID, firstName, lastName);
				displayOfficeNumber(CONT_ID, firstName, lastName);
				displayEmail(CONT_ID, firstName, lastName);
			}
		}catch(Exception e) {
			LOGGER.info("Dont Worry An Error Occured ");
			e.printStackTrace();
		}
	}
		//=============================
		// Displaying the Mobile Number
		//=============================
		public void displayMobile(int CONT_ID, String firstName, String lastName) {
			try {
				PreparedStatement preparedStmtMobile = con.prepareStatement(constant.mobileDisplay);
				preparedStmtMobile.setInt(1, CONT_ID);
				ResultSet rsMobile = preparedStmtMobile.executeQuery();
				LOGGER.info("The Mobile Number that "+firstName+" "+lastName+" has are: ");
				while(rsMobile.next()) {
					String extNumber = rsMobile.getString("EXTENSION");
					String mobileNumber = rsMobile.getString("MOB_NUMBER");
					LOGGER.info("+"+extNumber);
					LOGGER.info("  " +mobileNumber);
				}
				LOGGER.info("");
			}catch(Exception e) {
				LOGGER.info("Sorry An Error Occured with Displaying the Mobile Number !");
				e.printStackTrace();
			}
		}
		
		//===========================
		// Displaying the Home Number
		//===========================
		public void displayHomeNumber(int CONT_ID, String firstName, String lastName) {
			try {
				PreparedStatement preparedStmtHome = con.prepareStatement(constant.homeDisplay);
				preparedStmtHome.setInt(1, CONT_ID);
				ResultSet rsHome = preparedStmtHome.executeQuery();
				LOGGER.info("The Home Number that "+firstName+" "+lastName+" has are: ");
				while(rsHome.next()) {
					String extNumber = rsHome.getString("AREA_CODE");
					String homeNumber = rsHome.getString("HOME_NUMBER");
					LOGGER.info(extNumber);
					LOGGER.info(" " +homeNumber);
				}
			}catch(Exception e) {
				LOGGER.info("Sorry An Error Occured with Displaying the Home Number !");
				e.printStackTrace();
			}
		}
		
		//=============================
		// Displaying the Office Number
		//=============================
		public void displayOfficeNumber(int CONT_ID, String firstName, String lastName) {
			try {
				PreparedStatement preparedStmtOffice = con.prepareStatement(constant.officeDisplay);
				preparedStmtOffice.setInt(1, CONT_ID);
				ResultSet rsOffice = preparedStmtOffice.executeQuery();
				LOGGER.info("The Office Number that "+firstName+" "+lastName+" has are: ");
				while(rsOffice.next()) {
					String extNumber = rsOffice.getString("OFF_EXT");
					String officeNumber = rsOffice.getString("OFF_NUMBER");
					LOGGER.info(extNumber);
					LOGGER.info(" " +officeNumber);
				}
			}catch(Exception e) {
				LOGGER.info("Sorry An Error Occured with Displaying the office Number !");
				e.printStackTrace();
			}
		}
		
		//=====================
		// Displaying the Email
		//=====================
		public void displayEmail(int CONT_ID, String firstName, String lastName) {
			try {
				PreparedStatement preparedStmtEmail = con.prepareStatement(constant.emailDisplay);
				preparedStmtEmail.setInt(1, CONT_ID);
				ResultSet rsEmail = preparedStmtEmail.executeQuery();
				LOGGER.info("The Email that "+firstName+" "+lastName+" has are: ");
				while(rsEmail.next()) {
					String emailInfo = rsEmail.getString("EMAIL");
					LOGGER.info(emailInfo);
				}
			}catch(Exception e) {
				LOGGER.info("Sorry An Error Occured with Displaying the Email !");
				e.printStackTrace();
			}
		}
}

