package io.zilker.contact.service;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.contact.constants.Applicationconstants;
import io.zilker.contact.constants.SqlConstants;

public class DeleteMain {
	static Scanner scanner = new Scanner(System.in);
	private static String firstName,lastName;
	private static int deleteOption,choice,uid,recordId;
	//private static Connection con;
	private static PreparedStatement preparedStatement;
	 //private static ResultSet rs;
	public static Logger log = Logger.getLogger(DeleteMain.class.getName());
	
	//delete entire mail
	public static void deleteEntireMail(int userId,Connection con) {
		try {
			preparedStatement = con.prepareStatement(SqlConstants.DELETE_EMAIL);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	//delete entire mobile
	public static void deleteEntireMobile(int userId,Connection con) {
		try {
			preparedStatement = con.prepareStatement(SqlConstants.DELETE_MOBILE);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	//delete entire home
	public static void deleteEntireHome(int userId,Connection con) {
		try {
			preparedStatement = con.prepareStatement(SqlConstants.DELETE_HOME);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	//delete entire office
	public static void deleteEntireOffice(int userId,Connection con) {
		try {
			preparedStatement = con.prepareStatement(SqlConstants.DELETE_OFFICE);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	//delete entire person
	public static void deleteEntirePerson(int userId,Connection con) {
		try {
			preparedStatement = con.prepareStatement(SqlConstants.DELETE_CONTACT);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	//delete the entire tables controller
	public static void deleteEntireControl(int userId,Connection con) {
		try {
			deleteEntireMail(userId,con);
			deleteEntireMobile(userId,con);
			deleteEntireHome(userId,con);
			deleteEntireOffice(userId,con);
			deleteEntirePerson(userId,con);
			con.commit();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	//delete specific mobile
	public static void deleteSpecificMobile(int recordId,Connection con) {
		try {
			preparedStatement = con.prepareStatement(SqlConstants.DELETE_SPECIFIC_MOBILE);
			preparedStatement.setInt(1, recordId);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	
	//delete specific mail
	public static void deleteSpecificMail(int recordId,Connection con) {
		try {
			preparedStatement = con.prepareStatement(SqlConstants.DELETE_SPECIFIC_EMAIL);
			preparedStatement.setInt(1, recordId);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	
	//delete specific home
	public static void deleteSpecificHome(int recordId,Connection con) {
		try {
			preparedStatement = con.prepareStatement(SqlConstants.DELETE_SPECIFIC_HOME);
			preparedStatement.setInt(1, recordId);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	
	//delete specific office
	public static void deleteSpecificOffice(int recordID,Connection con) {
		try {
			preparedStatement = con.prepareStatement(SqlConstants.DELETE_SPECIFIC_OFFICE);
			preparedStatement.setInt(1, recordId);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	
	//choose specific record
	public static void chooseSpecific(int userId,Connection con) {
		try {
			//alert choose 1.mobile 2.mail 3.home 4.office
			log.info(Applicationconstants.DELETE_MAIN_OPTIONS);
			choice = scanner.nextInt();
			switch(choice) {
			case 1:
				DisplayRecords.displayMobileRecords(userId,con);
				//enter ID
				log.info(Applicationconstants.ENTER_ID);
				recordId = scanner.nextInt();
				deleteSpecificMobile(recordId,con);
				break;
			case 2:
				DisplayRecords.displayMailRecords(userId,con);
				//enter ID
				log.info(Applicationconstants.ENTER_ID);
				recordId = scanner.nextInt();
				deleteSpecificMail(recordId,con);
				break;
			case 3:
				DisplayRecords.displayHomeRecords(userId,con);
				//enter ID
				log.info(Applicationconstants.ENTER_ID);
				recordId = scanner.nextInt();
				deleteSpecificHome(recordId,con);
				break;
			case 4:
				DisplayRecords.displayOfficeRecords(userId,con);
				//enter ID
				log.info(Applicationconstants.ENTER_ID);
				recordId = scanner.nextInt();
				deleteSpecificOffice(recordId,con);
				break;
			default:
				break;
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	//delete main options
	public static void deleteMainOptions(Connection con) {
		try {
			//get names
			log.info(Applicationconstants.FIRST_NAME_ENTER);
			firstName = scanner.next();
			log.info(Applicationconstants.LAST_NAME_ENTER);
			lastName = scanner.next();
			con.setAutoCommit(false);
			if(!CheckExists.checkExists(firstName, lastName,con)) {
				log.info(Applicationconstants.USER_NOT_EXISTS);
				//not present 
				return;
			}
			//already exists
			//select option
			
			ListPersons.displayUsers(firstName, lastName,con);
			//prompt to enter id
			log.info(Applicationconstants.ENTER_ID);
			uid = scanner.nextInt();
			log.info(Applicationconstants.DELETE_MAIN_OPTIONS_OPTIONS);
			//1. contact entire 2. specific record
			deleteOption = scanner.nextInt();
			switch(deleteOption) {
			case 1:
				deleteEntireControl(uid,con);
				break;
			case 2:
				chooseSpecific(uid,con);
				break;
			default:
				break;
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	
	//delete main function
/*	public static void main(String [] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DbUtils.getConnection();
			deleteMainOptions(con);
		}
		catch(Exception e) {
			log.info(e.toString());
		}
	}*/

}
