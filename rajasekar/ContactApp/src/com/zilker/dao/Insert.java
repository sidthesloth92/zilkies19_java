package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.beans.ContactDetails;
import com.zilker.constant.ConsoleStrings;
import com.zilker.constant.ConstantQuery;
import com.zilker.dbutils.DbConnectivity;

public class Insert {
	public static final Logger logger = Logger.getLogger(Insert.class.getName());
	private static Connection myconn = null;
	private static PreparedStatement prep = null;
	static int cid = 0, f = 0, contid = 0;
	static Scanner in = new Scanner(System.in);

	public static int insertContact(ContactDetails obj) {
		cid = 0;
		try {
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.MAIL);
			ResultSet rs1 = prep.executeQuery();
			while (rs1.next()) {
				if (obj.getEmail().equals(rs1.getString(3))) {
					logger.info(ConsoleStrings.DUPLICATE);
					return 0;
				}
			}
			prep = myconn.prepareStatement(ConstantQuery.insertContact);
			prep.setString(1, obj.getFname());
			prep.setString(2, obj.getLname());
			prep.executeUpdate();
			prep = myconn.prepareStatement(ConstantQuery.CONTACT_ID);
			rs1 = prep.executeQuery();
			while (rs1.next()) {
				cid = rs1.getInt(1);
			}
		} catch (Exception e) {
			logger.info(ConsoleStrings.ERRORINSERT);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
		return cid;
	}

	public static void insertEmail(int cid, ArrayList<String> al) {
		try {
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.INSERT_EMAIL);
			for (int i = 0; i < al.size(); i++) {
				prep.setInt(1, cid);
				prep.setString(2, al.get(i));
				prep.executeUpdate();
			}
		} catch (Exception e) {
			logger.info(ConsoleStrings.ERRORINSERT);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
	}

	public static void insertOffice(int cid, ArrayList<String> al) {
		try {
			myconn = DbConnectivity.getConnection();
			for (int i = 0; i < al.size() - 1; i += 2) {
				int exten = Integer.parseInt(al.get(i));
				int office = Integer.parseInt(al.get(i + 1));
				PreparedStatement prep = myconn.prepareStatement(ConstantQuery.INSERT_OFFICE);
				prep.setInt(1, cid);
				prep.setInt(2, exten);
				prep.setInt(3, office);
				prep.executeUpdate();
			}
		} catch (Exception e) {
			logger.info(ConsoleStrings.ERRORINSERT);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
	}

	public static void insertHome(int cid, ArrayList<String> al) {
		try {
			myconn = DbConnectivity.getConnection();
			for (int i = 0; i < al.size() - 2; i += 3) {
				int area = Integer.parseInt(al.get(i));
				int cc = Integer.parseInt(al.get(i + 1));
				int home = Integer.parseInt(al.get(i + 2));
				PreparedStatement prep = myconn.prepareStatement(ConstantQuery.INSERT_HOME);
				prep.setInt(1, cid);
				prep.setInt(2, area);
				prep.setInt(3, cc);
				prep.setInt(4, home);
				prep.executeUpdate();
			}
		} catch (Exception e) {
			logger.info(ConsoleStrings.ERRORINSERT);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
	}

	public static void insertMobile(int cid, ArrayList<String> al) {
		try {
			myconn = DbConnectivity.getConnection();
			for (int i = 0; i < al.size() - 1; i += 2) {
				PreparedStatement prep = myconn.prepareStatement(ConstantQuery.INSERT_MOBILE);
				prep.setInt(1, cid);
				prep.setString(2, al.get(i));
				prep.setString(3, al.get(i + 1));
				prep.executeUpdate();
			}
		} catch (Exception e) {
			logger.info(ConsoleStrings.ERRORINSERT);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
	}

}
