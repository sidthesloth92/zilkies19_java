package io.zilker.contact.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import io.zilker.contact.constants.ApplicationVariables;
import io.zilker.contact.constants.SqlVariables;

public class AddPerson {
	public static Scanner sc = new Scanner(System.in);
	private static int choice,option,li;
	private static String firstName,lastName,mobileNumber,countryCode,email,homeNumber,homeCountryCode,areaCode,officeNumber,extension;
	public static Connection con;
	public static PreparedStatement pst;
	public static ResultSet rs;
	public static Logger log = Logger.getLogger(AddPerson.class.getName());
	
	//add personal details
	public static void addPersonal(Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.INSERT_NAMES);
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			pst.executeUpdate();
		}
		catch(Exception e) {
			
		}
		
	}
	
	//add Mobile details
	public static void addMobileWithoutCollections(int lid,Connection con) {
		try {		
			//till 1 add mobile
			
			do {
				log.info("Enter 1 to add Mobile other key to exit\n");
				option = sc.nextInt();
				if(option == 1) {
					log.info(ApplicationVariables.ENTER_MOBILE_NUMBER);
					mobileNumber = sc.next();
					if(mobileNumber.length()!=10) {
						log.info("Not valid");
						return;
					}
					else {
						log.info(ApplicationVariables.ENTER_COUNTRY_CODE);
						countryCode = sc.next();
						pst = con.prepareStatement(SqlVariables.INSERT_MOBILE);
						pst.setInt(1, lid);
						pst.setString(2, mobileNumber);
						pst.setString(3, countryCode);
						pst.executeUpdate();
					}
				}
			}while(option == 1);
		}
		catch(Exception e) {
			log.info(e.toString());
		}
	}
	
	//add Mail Ids
	public static void addMail(int lid,Connection con) {
		try {
			//till 1 add Mail
			
			do {
				log.info("Enter 1 to add mail else to exit\n");
				option = sc.nextInt();
				if(option ==1 ) {
					log.info("Enter email");
					email = sc.next();
					String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
					Pattern pat = Pattern.compile(emailRegex);
					boolean checkMail = pat.matcher(email).matches();
					if(checkMail) {
						pst = con.prepareStatement(SqlVariables.INSERT_EMAIL);
						pst.setInt(1, lid);
						pst.setString(2, email);
						pst.executeUpdate();
					}
					else {
						log.info(ApplicationVariables.WRONG_MAIL);
					}
				}
			}while(option == 1);
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		
	}
	//collections test
	public static void addMobile(int lid,Connection con) {
		try {
			ArrayList<String> mobileNumber = new ArrayList <String> ();
			ArrayList <String> mobileCountryCode = new ArrayList<String> ();
			do {
				log.info("Enter 1 to add mobile else exit");
				option = sc.nextInt();
				if(option == 1) {
					log.info(ApplicationVariables.ENTER_MOBILE_NUMBER);
					String mobileFirst = sc.next();
					if(mobileFirst.length() != 10) {
						log.info(ApplicationVariables.WRONG_MOBILE);
						continue;
					}
					mobileNumber.add(mobileFirst);
					log.info(ApplicationVariables.ENTER_COUNTRY_CODE);
					mobileCountryCode.add(sc.next());
				}
			}while(option == 1);
			for(int i=0;i<mobileNumber.size();i++) {
				pst = con.prepareStatement(SqlVariables.INSERT_MOBILE);
				pst.setInt(1, lid);
				pst.setString(2, mobileNumber.get(i));
				pst.setString(3, mobileCountryCode.get(i));
				pst.executeUpdate();
			}
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		
	}
	
	
	
	//add Home Numbers
	public static void addHome(int lid,Connection con) {
		try {
			do {
				log.info("Enter 1 to add home else exit");
				option = sc.nextInt();
				if(option == 1) {
					log.info(ApplicationVariables.Enter_HOME_NUMBER);
					homeNumber = sc.next();
					log.info(ApplicationVariables.ENTER_COUNTRY_CODE);
					homeCountryCode = sc.next();
					log.info(ApplicationVariables.ENTER_AREA_CODE);
					areaCode = sc.next();
					pst = con.prepareStatement(SqlVariables.INSERT_HOME);
					pst.setInt(1, li);
					pst.setString(2, homeCountryCode);
					pst.setString(3, homeNumber);
					pst.setString(4, areaCode);
					pst.executeUpdate();
				}
			}while(option == 1);
			
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		
	}
	
	//add office Numbers
	public static void addOffice(int lid,Connection con) {
		try {
			do {
				log.info(ApplicationVariables.OFFICE_OPTIONS);
				option = sc.nextInt();
				if(option == 1) {
					log.info(ApplicationVariables.ENTER_OFFICE);
					officeNumber = sc.next();
					log.info(ApplicationVariables.ENTER_EXTENSION);
					extension = sc.next();
					pst = con.prepareStatement(SqlVariables.INSERT_OFFICE);
					pst.setInt(1, li);
					pst.setString(2, officeNumber);
					pst.setString(3, extension);
					pst.executeUpdate();
				}
				
			}while(option ==1);
		}
		catch(Exception e){
			log.info(e.toString());
		}
		
	}
	//existing user
	public static void addOnExists(int li,Connection con) {
		log.info(ApplicationVariables.ADD_ON_EXISTS);
		option = sc.nextInt();
		switch(option) {
		case 1:addMobile(li,con);
			break;
		case 2:addMail(li,con);
			break;
		case 3:addHome(li,con);
			break;
		case 4: addOffice(li,con);
			break;
		default:
			break;
		}
	}
	
	
	//add person main
	public static void addMain(Connection con) {
		try {
			{
				choice=0;
			}
			log.info(ApplicationVariables.FIRST_NAME_ENTER);
			firstName = sc.next();
			log.info(ApplicationVariables.LAST_NAME_ENTER);
			lastName = sc.next();
			
			con.setAutoCommit(false);
			if(CheckExists.checkExists(firstName, lastName,con)) {
				//already exists
				//select option
				log.info(ApplicationVariables.ADD_USER_EXISTS);
				choice = sc.nextInt();
				if(choice == 2) {
					ListPersons.displayUsers(firstName, lastName,con);
					log.info("Enter id");
					//prompt to enter id
					option = sc.nextInt();
					//for update user
					addOnExists(option , con);
				}
			}
			if(choice !=2) {
				addPersonal(con);
				li = LastInserted.lastInsertedId(con);
				addMobile(li,con);
				addMail(li,con);
				addHome(li,con);
				addOffice(li,con);
			}
			con.commit();
			
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		finally {
		}
	}
	
	//main function 
	/*public static void main(String [] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DbUtils.getConnection();
			addMobile(19,con);
		}
		catch(Exception e) {
			log.info(e.toString());
		}
	}*/

}
