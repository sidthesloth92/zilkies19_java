package com.zilker.dao;

import com.zilker.beans.*;
import com.zilker.constant.ConstantQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.dbutils.DbConnectivity;
import com.zilker.constant.ConsoleStrings;

public class Sorting {
	public static final Logger logger = Logger.getLogger(Sorting.class.getName());
	private static Connection myconn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs1 = null;
	static int cid = 0, f = 0, contid = 0;
	static Scanner in = new Scanner(System.in);

	public static void sortFirstName() {
		try {
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.SORT_FIRST_NAME);
			rs1 = prep.executeQuery();
			System.out.println("--------------------------------------");
			System.out.println("*********************************************");
			Display.displayInfo(myconn, prep, rs1);
			System.out.println("--------------------------------------");
		} catch (Exception e) {
			System.out.println(ConsoleStrings.ERRORDISPLAY);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, rs1);
		}
	}

	public static void sortLastName() {
		try {
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.SORT_LAST_NAME);
			rs1 = prep.executeQuery();
			System.out.println("--------------------------------------");
			System.out.println("*********************************************");
			Display.displayInfo(myconn, prep, rs1);
			System.out.println("--------------------------------------");
		} catch (Exception e) {
			System.out.println(ConsoleStrings.ERRORDISPLAY);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, rs1);
		}
	}

	public static int showBase(String fname, String lname) {
		contid = 0;
		f = 0;
		try {
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.CONTACT);
			prep.setString(1, fname);
			prep.setString(2, lname);
			rs1 = prep.executeQuery();
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while (rs1.next()) {
				System.out.println(rs1.getInt(1) + " " + rs1.getString(2));
				f++;
			}
			if (f < 1) {
				return 0;
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(ConsoleStrings.CONTACT_ID);
			contid = in.nextInt();
		} catch (Exception e) {
			System.out.println(ConsoleStrings.ERRORDISPLAY);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, rs1);
		}
		return contid;
	}
}