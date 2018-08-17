package com.zilker.application.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;


import com.zilker.application.ConnectionConfig;
import com.zilker.application.constants.Constants;

public class ContactDisplay extends ConnectionConfig {
	//Setting up the Connection from ConnectionConfig Class
	private static final Logger LOGGER = Logger.getLogger(ContactDisplay.class.getName());
	static Connection con = getConnection();
	//=======================
	// Sorting By First Name
	//=======================
	public ResultSet sortByFirstName() {
		ResultSet rs = null;
		try {
			java.sql.Statement st = con.createStatement();
			rs = st.executeQuery(Constants.SORT_BY_FIRST_NAME);
		}catch(Exception e) {
			LOGGER.info("Dont Worry An Error Occured ");
			e.printStackTrace();
		}
		return rs;
	}
	//=======================
	// Sorting By Last Name
	//=======================
	public ResultSet sortByLastName() {
		ResultSet rs = null;
		try {
			java.sql.Statement st = con.createStatement();
			rs = st.executeQuery(Constants.SORT_BY_LAST_NAME);
		}catch(Exception e) {
			LOGGER.info("Dont Worry An Error Occured ");
			e.printStackTrace();
		}
		return rs;
	}
		//=============================
		// Displaying the Mobile Number
		//=============================
		public ResultSet displayMobile(int CONT_ID, String firstName, String lastName) {
			ResultSet rsMobile = null;
			try {
				PreparedStatement preparedStmtMobile = con.prepareStatement(Constants.mobileDisplay);
				preparedStmtMobile.setInt(1, CONT_ID);
				rsMobile = preparedStmtMobile.executeQuery();
				LOGGER.info("The Mobile Number that "+firstName+" "+lastName+" has are: ");
				
			}catch(Exception e) {
				LOGGER.info("Sorry An Error Occured with Displaying the Mobile Number !");
				e.printStackTrace();
			}
			return rsMobile;
		}
		
		//===========================
		// Displaying the Home Number
		//===========================
		public ResultSet displayHomeNumber(int CONT_ID, String firstName, String lastName) {
			ResultSet rsHome = null;
			try {
				PreparedStatement preparedStmtHome = con.prepareStatement(Constants.homeDisplay);
				preparedStmtHome.setInt(1, CONT_ID);
				rsHome = preparedStmtHome.executeQuery();
				LOGGER.info("The Home Number that "+firstName+" "+lastName+" has are: ");
				;
			}catch(Exception e) {
				LOGGER.info("Sorry An Error Occured with Displaying the Home Number !");
				e.printStackTrace();
			}
			return rsHome;
		}
		
		//=============================
		// Displaying the Office Number
		//=============================
		public ResultSet displayOfficeNumber(int CONT_ID, String firstName, String lastName) {
			ResultSet rsOffice = null;
			try {
				PreparedStatement preparedStmtOffice = con.prepareStatement(Constants.OFFICE_DISPLAY);
				preparedStmtOffice.setInt(1, CONT_ID);
				rsOffice = preparedStmtOffice.executeQuery();
				LOGGER.info("The Office Number that "+firstName+" "+lastName+" has are: ");
			}catch(Exception e) {
				LOGGER.info("Sorry An Error Occured with Displaying the office Number !");
				e.printStackTrace();
			}
			return rsOffice;
			
		}
		
		//=====================
		// Displaying the Email
		//=====================
		public ResultSet displayEmail(int CONT_ID, String firstName, String lastName) {
			ResultSet rsEmail = null;
			try {
				PreparedStatement preparedStmtEmail = con.prepareStatement(Constants.EMAIL_DISPLAY);
				preparedStmtEmail.setInt(1, CONT_ID);
				rsEmail = preparedStmtEmail.executeQuery();
				LOGGER.info("The Email that "+firstName+" "+lastName+" has are: ");
			}catch(Exception e) {
				LOGGER.info("Sorry An Error Occured with Displaying the Email !");
				e.printStackTrace();
			}
			return rsEmail;
		}
}

