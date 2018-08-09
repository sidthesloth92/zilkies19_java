package com.zilkeesaro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilkeesaro.beans.Details;
import com.zilkeesaro.constants.SqlConstants;
import com.zilkeesaro.constants.Strings;
import com.zilkeesaro.service.ScanInputs;

public class Queries {

	// String first_name,last_name,e_mail,mobile_number,home,office;

	int contact_id = 1;

	Connection conn = null;

	Scanner in = new Scanner(System.in);

	ScanInputs inputs = new ScanInputs();
	
	String msg="";

	public static final Logger logger = Logger.getLogger(Queries.class.getName());

	public void addContact(Details details) throws SQLException {

		conn = DatabaseConfig.getConnection();

		try {
			conn.setAutoCommit(false);

			contact_id = 1;

			PreparedStatement prepareStmt = null;

			prepareStmt = conn.prepareStatement(SqlConstants.SELECT_MAX);

			ResultSet set = prepareStmt.executeQuery();

			while (set.next()) {
				contact_id = contact_id + set.getInt(1);
			}

			prepareStmt = conn.prepareStatement(SqlConstants.ADD_CONTACTS);

			prepareStmt.setInt(1, contact_id);

			prepareStmt.setString(2, details.getFirst_name());

			prepareStmt.setString(3, details.getLast_name());

			prepareStmt.setString(4, details.getE_mail());

			prepareStmt.executeUpdate();

			prepareStmt = conn.prepareStatement(SqlConstants.ADD_PHONE);

			prepareStmt.setInt(1, contact_id);

			prepareStmt.setInt(2, 1);

			prepareStmt.setString(3, details.getMobile_number());

			prepareStmt.executeUpdate();

			prepareStmt = conn.prepareStatement(SqlConstants.ADD_PHONE);

			prepareStmt.setInt(1, contact_id);

			prepareStmt.setInt(2, 2);

			prepareStmt.setString(3, details.getOffice());

			prepareStmt.executeUpdate();

			prepareStmt = conn.prepareStatement(SqlConstants.ADD_PHONE);

			prepareStmt.setInt(1, contact_id);

			prepareStmt.setInt(2, 3);

			prepareStmt.setString(3, details.getHome());

			prepareStmt.executeUpdate();

			conn.commit();

			logger.info("Contact added successfully..!!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			conn.rollback();

		} finally {

			conn.close();

		}

	}

	public void SortList(int option) throws SQLException {

		PreparedStatement prepareStmt = null;

		conn = DatabaseConfig.getConnection();

		try {

			if (option == 0) {
				prepareStmt = conn.prepareStatement(SqlConstants.SORT_BY_ID);
			} else if (option == 1) {
				prepareStmt = conn.prepareStatement(SqlConstants.SORT_BY_FIRST_NAME);
			} else if (option == 2) {
				prepareStmt = conn.prepareStatement(SqlConstants.SORT_BY_LAST_NAME);
			} else if (option == 3) {
				prepareStmt = conn.prepareStatement(SqlConstants.SORT_BY_EMAIL);
			} else {
				prepareStmt = conn.prepareStatement(SqlConstants.SORT_BY_FIRST_NAME);
			}

			ResultSet set = prepareStmt.executeQuery();

			int sno = 1;

			while (set.next()) {

				PreparedStatement individualStatement = conn.prepareStatement(SqlConstants.LIST_BY_ID);

				individualStatement.setInt(1, contact_id);

				ResultSet list = individualStatement.executeQuery();
				
				Display(sno,set,list);

				sno++;
			}
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void Update(int option, Details details) {

		conn = DatabaseConfig.getConnection();

		PreparedStatement prepareStmt = null;

		logger.info(Strings.CONTACT_ID);
		
		//System.out.println("Enter Contact Id..");

		contact_id = in.nextInt();

		try {
			if (option == 0) {

				prepareStmt = conn.prepareStatement(SqlConstants.UPDATE_FIRST_LAST);

				details.setFirst_name(inputs.getFirst_name());

				prepareStmt.setString(1, details.getFirst_name());

				details.setLast_name(inputs.getLast_name());

				prepareStmt.setString(2, details.getLast_name());
				
				prepareStmt.setInt(3, contact_id);
				

			} else if (option == 1) {

				prepareStmt = conn.prepareStatement(SqlConstants.UPDATE_NUMBERS);

				details.setHome(inputs.getHome());

				prepareStmt.setString(1, details.getHome());

				prepareStmt.setInt(2, contact_id);

				prepareStmt.setInt(3, 3);

			} else if (option == 2) {
				
				prepareStmt = conn.prepareStatement(SqlConstants.UPDATE_NUMBERS);

				details.setHome(inputs.getHome());

				prepareStmt.setString(1, details.getOffice());

				prepareStmt.setInt(2, contact_id);

				prepareStmt.setInt(3, 2);
			}else if(option == 3){
				
				prepareStmt = conn.prepareStatement(SqlConstants.UPDATE_NUMBERS);

				details.setHome(inputs.getHome());

				prepareStmt.setString(1, details.getMobile_number());

				prepareStmt.setInt(2, contact_id);

				prepareStmt.setInt(3, 1);
				
			}else {
				
				prepareStmt = conn.prepareStatement(SqlConstants.UPDATE_EMAIL);
				
				details.setE_mail(inputs.getE_mail());

				prepareStmt.setString(1, details.getE_mail());
				
				prepareStmt.setInt(2, contact_id);
				
			}
			
			prepareStmt.executeUpdate();
			
			logger.info(contact_id + Strings.UPDATE_SUCCESS );
			
			//System.out.println(contact_id+" is updated successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void Delete()
	{
		conn=DatabaseConfig.getConnection();
		
		//System.out.println("Enter Contact Id..");
		
		logger.info(Strings.CONTACT_ID);
		
		int contact_id = in.nextInt();

		try {
			
			conn.setAutoCommit(false);

			PreparedStatement preparedStmt=null;
			
			preparedStmt=conn.prepareStatement(SqlConstants.DEL_PHONE);
			
			preparedStmt.setInt(1, contact_id);

			preparedStmt.executeUpdate();
			
			preparedStmt=conn.prepareStatement(SqlConstants.DEL_CONTACT);
			
			preparedStmt.setInt(1,contact_id);
			
			if (preparedStmt.executeUpdate() == 1) {
				
				//System.out.println(contact_id + " is Deleted Successfully");
				
				logger.info(contact_id + Strings.DELETE_SUCCESS);
				
			} else {
				
				//System.err.println(contact_id + " is not present in the Contact Details");
				
				logger.info(contact_id + Strings.DELETE_ERROR);
				
			}
			
			conn.commit();

		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void Display(int sno,ResultSet set,ResultSet list) {
		try {
		
		contact_id = set.getInt("contact_id");

		System.out.println(Strings.NEW_LINE);

		System.out.println(sno + ")");

		System.out.format(Strings.STRING_SPACE, Strings.CID, set.getString("contact_id"));

		System.out.println();

		System.out.format(Strings.STRING_SPACE, Strings.NAME, set.getString("first_name") + " " + set.getString("last_name"));

		System.out.println();
		
		while (list.next()) {

			if (list.getString("phone_type").equals("1")) {
				
				System.out.format(Strings.STRING_SPACE, Strings.MOBILE_NUMBER, list.getString("phone_no"));

			}
			if (list.getString("phone_type").equals("2")) {

				System.out.format(Strings.STRING_SPACE, Strings.OFFICE_NUMBER, list.getString("phone_no"));

			}
			if (list.getString("phone_type").equals("3")) {

				System.out.format(Strings.STRING_SPACE, Strings.HOME_NUMBER, list.getString("phone_no"));

			}
			System.out.println();

		}

		System.out.format(Strings.STRING_SPACE, Strings.E_MAIL, set.getString("email"));

		System.out.println();

		
		}catch(Exception e) {}
	}

}
