package com.zilker.application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


public class Display {
	private static final Logger LOGGER = Logger.getLogger(Display.class.getName());
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
