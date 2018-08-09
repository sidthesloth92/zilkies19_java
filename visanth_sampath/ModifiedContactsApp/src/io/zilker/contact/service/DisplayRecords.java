package io.zilker.contact.service;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import io.zilker.contact.constants.ApplicationVariables;
import io.zilker.contact.constants.SqlVariables;

public class DisplayRecords {
	//private static Connection con;
	public static Logger log = Logger.getLogger(DisplayRecords.class.getName()); 
	 
	
	//displays mobile records
	public static void displayMobileRecords(int userId,Connection con) {
		try {
			PreparedStatement pst;
			ResultSet rs;
			pst = con.prepareStatement(SqlVariables.SELECT_FROM_MOBILE);
			pst.setInt(1, userId);
			rs = pst.executeQuery();
			// 1.mobile no 2.country code 3.table_id
			while(rs.next()) {
				log.info(rs.getString("mobile_no")+" "+rs.getString("country_code")+" "+ rs.getInt("table_id"));
			}
		}
		catch(Exception e) {
			log.info(e.toString());
		}
	}
	
	//displays mail records
	public static void displayMailRecords(int userId,Connection con) {
		try {
			PreparedStatement pst;
			ResultSet rs;
			pst = con.prepareStatement(SqlVariables.SELECT_FROM_EMAIL);
			pst.setInt(1, userId);
			rs = pst.executeQuery();
			// 1.mail id  2.table_id
			while(rs.next()) {
				log.info(rs.getString("mail")+" "+ rs.getInt("table_id"));
			}
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		
	}
	
	//displays home reocrds
	public static void displayHomeRecords(int userId,Connection con) {
		try {
			PreparedStatement pst;
			ResultSet rs;
			pst = con.prepareStatement(SqlVariables.SELECT_FROM_HOME);
			pst.setInt(1, userId);
			rs = pst.executeQuery();
			// 1.home no 2.country code 3.area code 4.table_id
			while(rs.next()) {
				log.info(rs.getString("home_no")+" "+rs.getString("country_code")+" "+rs.getString("area_code")+" "+ rs.getInt("table_id"));
			}
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		
	}
	
	//displays office records
	public static void displayOfficeRecords(int userId,Connection con) {
		try {
			PreparedStatement pst;
			ResultSet rs;
			pst = con.prepareStatement(SqlVariables.SELECT_FROM_OFFICE);
			pst.setInt(1, userId);
			rs = pst.executeQuery();
			// 1.mobile no 2.extension 3.table_id
			while(rs.next()) {
				log.info(rs.getString("off_number")+" "+rs.getString("extension")+" "+ rs.getInt("table_id"));
			}
		}
		catch(Exception e) {
			log.info(e.toString());
		}
		
	}
	
	//display records
	public static void displayAllRecordsMain(Connection con) {
		try {
			try {
				PreparedStatement pst;
				ResultSet rs;
				pst = con.prepareStatement(SqlVariables.SELECT_ALL_ROWS);
				rs = pst.executeQuery();
				while(rs.next()) {
					int tmpid = rs.getInt("id");
					log.info(tmpid+" " +rs.getString("first_name") +" "+ rs.getString("last_name"));
					displayMobileRecords(tmpid, con);
					displayMailRecords(tmpid, con);
					displayHomeRecords(tmpid, con);
					displayOfficeRecords(tmpid, con);
					log.info(ApplicationVariables.PATTERN);
				}
			}
			catch (Exception e) {
				log.info(e.toString());
			}
		}
		catch(Exception e) {
			log.info(e.toString());
		}
	}
	
	//test main function
	/*public static void main(String [] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DbUtils.getConnection();
			displayAllRecordsMain(con);
		}
		catch(Exception e) {
			log.info(e.toString());
		}
	}*/

}
