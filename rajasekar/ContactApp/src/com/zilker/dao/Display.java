package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.constant.ConsoleStrings;
import com.zilker.constant.ConstantQuery;
import com.zilker.dbutils.DbConnectivity;

public class Display {
	public static final Logger logger = Logger.getLogger(Display.class.getName());
	private static Connection myconn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs = null, rs1 = null;
	static int cid = 0, f = 0, contid = 0;
	static Scanner in = new Scanner(System.in);

	public static void displayInfo(Connection myconn, PreparedStatement prep, ResultSet rs1) {
		try {
			while (rs1.next()) {
				int idd = rs1.getInt(1);
				prep = myconn.prepareStatement(ConstantQuery.DISPLAY_CONTACT);
				prep.setInt(1, idd);
				rs = prep.executeQuery();
				while (rs.next()) {
					System.out.println("Contact Id:" + rs.getInt(1) + " " + "Firstname:" + rs.getString(2) + " "
							+ "LastName:" + rs.getString(3));
				}
				prep = myconn.prepareStatement(ConstantQuery.DISPLAY_EMAIL);
				prep.setInt(1, idd);
				rs = prep.executeQuery();
				while (rs.next()) {
					System.out.println("Email:" + rs.getString(3));
				}
				prep = myconn.prepareStatement(ConstantQuery.DISPLAY_OFFICE);
				prep.setInt(1, idd);
				rs = prep.executeQuery();
				while (rs.next()) {
					System.out.println("ExtensionNumber:" + rs.getInt(3) + " " + "OfficeNumber:" + rs.getInt(4));
				}
				prep = myconn.prepareStatement(ConstantQuery.DISPLAY_HOME);
				prep.setInt(1, idd);
				rs = prep.executeQuery();
				while (rs.next()) {
					System.out.println("AreaCode:" + rs.getInt(3) + " " + "CountryCode:" + rs.getInt(4) + " "
							+ "Mobile:" + rs.getInt(5));
				}
				prep = myconn.prepareStatement(ConstantQuery.DISPLAY_MOBILE);
				prep.setInt(1, idd);
				rs = prep.executeQuery();
				while (rs.next()) {
					System.out.println("CountryCode:" + rs.getString(3) + " " + "Mobile:" + rs.getString(4));
				}
				System.out.println("*********************************************");
			}
		} catch (Exception e) {
			System.out.println(ConsoleStrings.ERRORDISPLAY);
		}
		 finally {
				DbConnectivity.closeConnection(myconn, prep, rs1);
			}
	}

	public static int displayMobile(int cntid) {
		try {
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.DISPLAY_MOBILE);
			prep.setInt(1, cntid);
			rs1 = prep.executeQuery();
			f = 0;
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while (rs1.next()) {
				System.out.printf("%20d%20d%20s%20s\n", rs1.getInt(1), rs1.getInt(2), rs1.getString(3),
						rs1.getString(4));
				f++;
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		} catch (Exception e) {
			System.out.println(ConsoleStrings.ERRORDISPLAY);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, rs1);
		}
		return f;
	}

	public static int displayOffice(int cntid) {
		try {
			myconn = DbConnectivity.getConnection();
			f = 0;
			prep = myconn.prepareStatement(ConstantQuery.DISPLAY_OFFICE);
			prep.setInt(1, cntid);
			rs1 = prep.executeQuery();
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while (rs1.next()) {
				System.out.printf("%20d%20d%20d%20d\n", rs1.getInt(1), rs1.getInt(2), rs1.getInt(3), rs1.getInt(4));
				f++;
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		} catch (Exception e) {
			System.out.println(ConsoleStrings.ERRORDISPLAY);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, rs1);
		}
		return f;
	}

	public static int displayHome(int cid) {
		try {
			f = 0;
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.DISPLAY_HOME);
			prep.setInt(1, cid);
			rs1 = prep.executeQuery();
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while (rs1.next()) {
				System.out.printf("%20d%20d%20d%20d%20d\n", rs1.getInt(1), rs1.getInt(2), rs1.getInt(3), rs1.getInt(4),
						rs1.getInt(5));
				f++;
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		} catch (Exception e) {
			System.out.println(ConsoleStrings.ERRORDISPLAY);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, rs1);
		}
		return f;
	}

	public static int displayEmail(int cid) {
		try {
			f = 0;
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.DISPLAY_EMAIL);
			prep.setInt(1, cid);
			rs1 = prep.executeQuery();
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while (rs1.next()) {
				System.out.printf("%20d%20d%20s\n", rs1.getInt(1), rs1.getInt(2), rs1.getString(3));
				f++;
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		} catch (Exception e) {
			System.out.println(ConsoleStrings.ERRORDISPLAY);
		}
		 finally {
				DbConnectivity.closeConnection(myconn, prep, rs1);
			}
		return f;
	}

}
