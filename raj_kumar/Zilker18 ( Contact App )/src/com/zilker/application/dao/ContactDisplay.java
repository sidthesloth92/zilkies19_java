package com.zilker.application.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.zilker.application.services.Display;
import com.zilker.application.ConnectionConfig;
import com.zilker.application.constants.Constants;

public class ContactDisplay extends ConnectionConfig {
	//Setting up the Connection from ConnectionConfig Class
	private static final Logger LOGGER = Logger.getLogger(ContactDisplay.class.getName());
	Display display = new Display();
	static Connection con = getConnection();
	//=======================
	// Sorting By First Name
	//=======================
	public void sortByFirstName() {
		try {
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(Constants.sortByFirstName);
			while(rs.next()) {
				display.displayContact(rs);
				int CONT_ID = rs.getInt("CONTACT_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
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
			ResultSet rs = st.executeQuery(Constants.sortByLastName);
			while(rs.next()) {
				display.displayContact(rs);
				int CONT_ID = rs.getInt("CONTACT_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
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
				PreparedStatement preparedStmtMobile = con.prepareStatement(Constants.mobileDisplay);
				preparedStmtMobile.setInt(1, CONT_ID);
				ResultSet rsMobile = preparedStmtMobile.executeQuery();
				LOGGER.info("The Mobile Number that "+firstName+" "+lastName+" has are: ");
				display.displayMobile(rsMobile);
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
				PreparedStatement preparedStmtHome = con.prepareStatement(Constants.homeDisplay);
				preparedStmtHome.setInt(1, CONT_ID);
				ResultSet rsHome = preparedStmtHome.executeQuery();
				LOGGER.info("The Home Number that "+firstName+" "+lastName+" has are: ");
				display.displayHome(rsHome);
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
				PreparedStatement preparedStmtOffice = con.prepareStatement(Constants.officeDisplay);
				preparedStmtOffice.setInt(1, CONT_ID);
				ResultSet rsOffice = preparedStmtOffice.executeQuery();
				LOGGER.info("The Office Number that "+firstName+" "+lastName+" has are: ");
				display.displayOffice(rsOffice);
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
				PreparedStatement preparedStmtEmail = con.prepareStatement(Constants.emailDisplay);
				preparedStmtEmail.setInt(1, CONT_ID);
				ResultSet rsEmail = preparedStmtEmail.executeQuery();
				LOGGER.info("The Email that "+firstName+" "+lastName+" has are: ");
				display.displayEmail(rsEmail);
			}catch(Exception e) {
				LOGGER.info("Sorry An Error Occured with Displaying the Email !");
				e.printStackTrace();
			}
		}
}

