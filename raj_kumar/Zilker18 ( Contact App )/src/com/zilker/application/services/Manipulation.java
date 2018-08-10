package com.zilker.application.services;

import java.sql.ResultSet;
import java.util.logging.Logger;

public class Manipulation {
	private static final Logger LOGGER = Logger.getLogger(Manipulation.class.getName());
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
	}
}
