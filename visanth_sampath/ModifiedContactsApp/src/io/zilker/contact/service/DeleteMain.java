package io.zilker.contact.service;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.contact.constants.ApplicationVariables;
import io.zilker.contact.constants.SqlVariables;

public class DeleteMain {
	static Scanner sc = new Scanner(System.in);
	private static String firstName,lastName;
	private static int deleteOption,choice,uid,recordId;
	//private static Connection con;
	private static PreparedStatement pst;
	 //private static ResultSet rs;
	public static Logger log = Logger.getLogger(DeleteMain.class.getName());
	
	//delete entire mail
	public static void deleteEntireMail(int userId,Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.DELETE_EMAIL);
			pst.setInt(1, userId);
			pst.executeUpdate();
		}
		catch(Exception e) {
			
		}
	}
	
	//delete entire mobile
	public static void deleteEntireMobile(int userId,Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.DELETE_MOBILE);
			pst.setInt(1, userId);
			pst.executeUpdate();
		}
		catch(Exception e) {
			
		}
	}
	
	//delete entire home
	public static void deleteEntireHome(int userId,Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.DELETE_HOME);
			pst.setInt(1, userId);
			pst.executeUpdate();
		}
		catch(Exception e) {
			
		}
	}
	//delete entire office
	public static void deleteEntireOffice(int userId,Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.DELETE_OFFICE);
			pst.setInt(1, userId);
			pst.executeUpdate();
		}
		catch(Exception e) {
			
		}
	}
	
	//delete entire person
	public static void deleteEntirePerson(int userId,Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.DELETE_CONTACT);
			pst.setInt(1, userId);
			pst.executeUpdate();
		}
		catch(Exception e) {
			
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
			log.info(e.toString());
		}
		
	}
	//delete specific mobile
	public static void deleteSpecificMobile(int recordId,Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.DELETE_SPECIFIC_MOBILE);
			pst.setInt(1, recordId);
			pst.executeUpdate();
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		
	}
	
	//delete specific mail
	public static void deleteSpecificMail(int recordId,Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.DELETE_SPECIFIC_EMAIL);
			pst.setInt(1, recordId);
			pst.executeUpdate();
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		
	}
	
	//delete specific home
	public static void deleteSpecificHome(int recordId,Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.DELETE_SPECIFIC_HOME);
			pst.setInt(1, recordId);
			pst.executeUpdate();
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		
	}
	
	//delete specific office
	public static void deleteSpecificOffice(int recordID,Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.DELETE_SPECIFIC_OFFICE);
			pst.setInt(1, recordId);
			pst.executeUpdate();
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		
	}
	
	//choose specific record
	public static void chooseSpecific(int userId,Connection con) {
		try {
			//alert choose 1.mobile 2.mail 3.home 4.office
			log.info(ApplicationVariables.DELETE_MAIN_OPTIONS);
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				DisplayRecords.displayMobileRecords(userId,con);
				//enter ID
				log.info(ApplicationVariables.ENTER_ID);
				recordId = sc.nextInt();
				deleteSpecificMobile(recordId,con);
				break;
			case 2:
				DisplayRecords.displayMailRecords(userId,con);
				//enter ID
				log.info(ApplicationVariables.ENTER_ID);
				recordId = sc.nextInt();
				deleteSpecificMail(recordId,con);
				break;
			case 3:
				DisplayRecords.displayHomeRecords(userId,con);
				//enter ID
				log.info(ApplicationVariables.ENTER_ID);
				recordId = sc.nextInt();
				deleteSpecificHome(recordId,con);
				break;
			case 4:
				DisplayRecords.displayOfficeRecords(userId,con);
				//enter ID
				log.info(ApplicationVariables.ENTER_ID);
				recordId = sc.nextInt();
				deleteSpecificOffice(recordId,con);
				break;
			default:
				break;
			}
		}
		catch(Exception e) {
			log.info(e.toString());
		}
	}
	
	//delete main options
	public static void deleteMainOptions(Connection con) {
		try {
			//get names
			log.info(ApplicationVariables.FIRST_NAME_ENTER);
			firstName = sc.next();
			log.info(ApplicationVariables.LAST_NAME_ENTER);
			lastName = sc.next();
			con.setAutoCommit(false);
			if(!CheckExists.checkExists(firstName, lastName,con)) {
				log.info(ApplicationVariables.USER_NOT_EXISTS);
				//not present 
				return;
			}
			//already exists
			//select option
			
			ListPersons.displayUsers(firstName, lastName,con);
			//prompt to enter id
			log.info(ApplicationVariables.ENTER_ID);
			uid = sc.nextInt();
			log.info(ApplicationVariables.DELETE_MAIN_OPTIONS_OPTIONS);
			//1. contact entire 2. specific record
			deleteOption = sc.nextInt();
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
			log.info(e.toString());
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
