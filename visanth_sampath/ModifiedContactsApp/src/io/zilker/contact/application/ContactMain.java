package io.zilker.contact.application;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.contact.DAO.DbUtils;
import io.zilker.contact.constants.ApplicationVariables;
import io.zilker.contact.service.AddPerson;
import io.zilker.contact.service.DeleteMain;
import io.zilker.contact.service.DisplayRecords;
import io.zilker.contact.service.UpdatePerson;

public class ContactMain {
	
	private static Scanner sc = new Scanner(System.in);
	private static Connection con;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static int option;
	public static Logger log = Logger.getLogger(ContactMain.class.getName());
	
	
	public static void main(String [] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DbUtils.getConnection();
			log.info(ApplicationVariables.CONTACT_MAIN_CHOICE);
			option = sc.nextInt();
			switch(option) {
			case 1:AddPerson.addMain(con);
				break;
			case 2:DeleteMain.deleteMainOptions(con);
				break;
			case 3:DisplayRecords.displayAllRecordsMain(con);
				break;
			case 4: UpdatePerson.updateMain(con);
				break;
			default:
				break;
			}
			//AddPerson.addMain(con);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			DbUtils.closeConnection(con, pst, rs);
		}
	}
	

}
