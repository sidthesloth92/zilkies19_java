package com.zilker.application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.zilker.application.dao.ContactDisplay;


public class Display {
	private static final Logger LOGGER = Logger.getLogger(Display.class.getName());
	ContactDisplay contactDisplay = new ContactDisplay();
	public void dsiplaySortFirstName() {
		ResultSet rs =contactDisplay.sortByFirstName();
		try {
			while(rs.next()) {
				displayContact(rs);
				int CONT_ID = rs.getInt("CONTACT_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				displayMobile(contactDisplay.displayMobile(CONT_ID, firstName, lastName));
				displayHome(contactDisplay.displayHomeNumber(CONT_ID, firstName, lastName));
				displayOffice(contactDisplay.displayOfficeNumber(CONT_ID, firstName, lastName));
				displayEmail(contactDisplay.displayEmail(CONT_ID, firstName, lastName));
			}
		}catch(Exception e) {
			
		}
	}
	public void dsiplaySortLastName() {
		ResultSet rs =contactDisplay.sortByLastName();
		try {
			while(rs.next()) {
				displayContact(rs);
				int CONT_ID = rs.getInt("CONTACT_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				displayMobile(contactDisplay.displayMobile(CONT_ID, firstName, lastName));
				displayHome(contactDisplay.displayHomeNumber(CONT_ID, firstName, lastName));
				displayOffice(contactDisplay.displayOfficeNumber(CONT_ID, firstName, lastName));
				displayEmail(contactDisplay.displayEmail(CONT_ID, firstName, lastName));
			}
		}catch(Exception e) {
			
		}
	}
	public void displayContact(ResultSet rs) {
		try {
			String firstName = rs.getString("FIRST_NAME");
			String lastName = rs.getString("LAST_NAME");
			LOGGER.info("Name is: " +firstName);
			LOGGER.info(" " +lastName);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayMobile(ResultSet rs) {
		try {
			while(rs.next()) {
				String extNumber = rs.getString("EXTENSION");
				String mobileNumber = rs.getString("MOB_NUMBER");
				LOGGER.info("+"+extNumber);
				LOGGER.info("  " +mobileNumber);
			}
		}catch(Exception e) {
			
		}
	}
	
	public void displayHome(ResultSet rs) {
		try {
			while(rs.next()) {
				String extNumber = rs.getString("AREA_CODE");
				String homeNumber = rs.getString("HOME_NUMBER");
				LOGGER.info(extNumber);
				LOGGER.info(" " +homeNumber);
			}
		}catch(Exception e) {
			
		}
	}
	
	public void displayOffice(ResultSet rs) {
		try {
			while(rs.next()) {
				String extNumber = rs.getString("OFF_EXT");
				String officeNumber = rs.getString("OFF_NUMBER");
				LOGGER.info(extNumber);
				LOGGER.info(" " +officeNumber);
			}
		}catch(Exception e) {
			
		}
	}
	
	public void displayEmail(ResultSet rs) {
		try {
			while(rs.next()) {
				String emailInfo = rs.getString("EMAIL");
				LOGGER.info(emailInfo);
			}
		}catch(Exception e) {
			
		}
	}
}
