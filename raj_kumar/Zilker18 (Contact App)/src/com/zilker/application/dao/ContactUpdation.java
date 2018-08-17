package com.zilker.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.application.ConnectionConfig;
import com.zilker.application.constants.Constants;

public class ContactUpdation extends ConnectionConfig {
	private static final Logger LOGGER = Logger.getLogger(ContactUpdation.class.getName());
	static Connection con = getConnection();
	static Scanner in = new Scanner(System.in);
	ContactCreation contactCreation = new ContactCreation();
	
	public ResultSet displayPresentContact(String firstName, String lastName) {
		ResultSet rs = null;
		try {
			PreparedStatement preparedStmt = con.prepareStatement(Constants.SELECT_BY_FIRST_LAST);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			rs = preparedStmt.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public ResultSet updatingMobile(int CONT_ID, String firstName, String lastName) {
		ResultSet rsMobile = null;
		try {
			PreparedStatement preparedStmtMobile = con.prepareStatement(Constants.mobileDisplay);
			preparedStmtMobile.setInt(1, CONT_ID);
			rsMobile = preparedStmtMobile.executeQuery();
			LOGGER.info("the Mobile Number that "+firstName+" "+lastName+" has are: ");
		}catch(Exception e) {
			LOGGER.info("An Error Occured During Updating Mobile Number");
		}
		return rsMobile;
	}
	
	public void changeMobile(int updatedExtension, String updatedNumber, int CONT_ID, int MOBILE_ID_TO_CHANGE) {
		try {
			PreparedStatement preparedStmtUpdateMob = con.prepareStatement(Constants.UPDATE_MOBILE_NUMBER);
			preparedStmtUpdateMob.setInt(1, updatedExtension);
			preparedStmtUpdateMob.setString(2, updatedNumber);
			preparedStmtUpdateMob.setInt(3, CONT_ID);
			preparedStmtUpdateMob.setInt(4, MOBILE_ID_TO_CHANGE);
			contactCreation.executionResult(String.valueOf(MOBILE_ID_TO_CHANGE), preparedStmtUpdateMob.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//=============================
	// Updating the Home Number
	//=============================
	public ResultSet updatingHome(int CONT_ID, String firstName, String lastName) {
		ResultSet rsMobile = null;
		try {
			PreparedStatement preparedStmtMobile = con.prepareStatement(Constants.homeDisplay);
			preparedStmtMobile.setInt(1, CONT_ID);
			rsMobile = preparedStmtMobile.executeQuery();
			LOGGER.info("the Home Number that "+firstName+" "+lastName+" has are: ");
		}catch(Exception e) {
			LOGGER.info("An Error Occured During Updating Mobile Number");
		}
		return rsMobile;
	}
	
	public void changeHome(int updatedExtension, String updatedNumber, int CONT_ID, int MOBILE_ID_TO_CHANGE) {
		try {
			PreparedStatement preparedStmtUpdateMob = con.prepareStatement(Constants.UPDATE_HOME_NUMBER);
			preparedStmtUpdateMob.setInt(1, updatedExtension);
			preparedStmtUpdateMob.setString(2, updatedNumber);
			preparedStmtUpdateMob.setInt(3, CONT_ID);
			preparedStmtUpdateMob.setInt(4, MOBILE_ID_TO_CHANGE);
			contactCreation.executionResult(String.valueOf(MOBILE_ID_TO_CHANGE), preparedStmtUpdateMob.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//=============================
	// Updating the Office Number
	//=============================
	public ResultSet updatingOffice(int CONT_ID, String firstName, String lastName) {
		ResultSet rsMobile = null;
		try {
			PreparedStatement preparedStmtMobile = con.prepareStatement(Constants.OFFICE_DISPLAY);
			preparedStmtMobile.setInt(1, CONT_ID);
			rsMobile = preparedStmtMobile.executeQuery();
			LOGGER.info("the Office Number that "+firstName+" "+lastName+" has are: ");
		}catch(Exception e) {
			LOGGER.info("An Error Occured During Updating Mobile Number");
		}
		return rsMobile;
	}
	
	public void changeOffice(int updatedExtension, String updatedNumber, int CONT_ID, int MOBILE_ID_TO_CHANGE) {
		try {
			PreparedStatement preparedStmtUpdateMob = con.prepareStatement(Constants.UPDATE_OFFICE_NUM);
			preparedStmtUpdateMob.setInt(1, updatedExtension);
			preparedStmtUpdateMob.setString(2, updatedNumber);
			preparedStmtUpdateMob.setInt(3, CONT_ID);
			preparedStmtUpdateMob.setInt(4, MOBILE_ID_TO_CHANGE);
			contactCreation.executionResult(String.valueOf(MOBILE_ID_TO_CHANGE), preparedStmtUpdateMob.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public ResultSet updatingEmail(int CONT_ID, String firstName, String lastName) {
		ResultSet rsMobile = null;
		try {
			PreparedStatement preparedStmtMobile = con.prepareStatement(Constants.EMAIL_DISPLAY);
			preparedStmtMobile.setInt(1, CONT_ID);
			rsMobile = preparedStmtMobile.executeQuery();
			LOGGER.info("the Email that "+firstName+" "+lastName+" has are: ");
		}catch(Exception e) {
			LOGGER.info("An Error Occured During Updating Mobile Number");
		}
		return rsMobile;
	}
	
	public void changeEmail(String updatedEmail, int CONT_ID, int MOBILE_ID_TO_CHANGE) {
		try {
			PreparedStatement preparedStmtUpdateMob = con.prepareStatement(Constants.UPDATE_EMAIL);
			preparedStmtUpdateMob.setString(1, updatedEmail);
			preparedStmtUpdateMob.setInt(2, CONT_ID);
			preparedStmtUpdateMob.setInt(3, MOBILE_ID_TO_CHANGE);
			contactCreation.executionResult(String.valueOf(MOBILE_ID_TO_CHANGE), preparedStmtUpdateMob.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
