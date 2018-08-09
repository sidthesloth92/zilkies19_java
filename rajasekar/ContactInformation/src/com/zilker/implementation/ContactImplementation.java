package com.zilker.implementation;

import com.zilker.beans.*;
import com.zilker.constant.constantquery;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.dbutils.DBconn;
import com.zilker.constant.consolestrings;

public class ContactImplementation implements Contactfunctionality {
	public static final Logger logger = Logger.getLogger(ContactImplementation.class.getName());
	private static Connection myconn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs = null, rs1 = null;
	static int cid = 0, f = 0, contid = 0;
	static Scanner in = new Scanner(System.in);

	public static void sortfname() {
		try {
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement(constantquery.sortfname);
			rs1 = prep.executeQuery();
			System.out.println("--------------------------------------");
			System.out.println("*********************************************");
			Display.displayinfo(myconn, prep, rs1);
			System.out.println("--------------------------------------");
		} catch (Exception e) {
			System.out.println(consolestrings.errordisplay);
		} finally {
			DBconn.closeConn(myconn, prep, rs);
		}
	}

	public static void sortlname() {
		try {
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement(constantquery.sortlname);
			rs1 = prep.executeQuery();
			System.out.println("--------------------------------------");
			System.out.println("*********************************************");
			Display.displayinfo(myconn, prep, rs1);
			System.out.println("--------------------------------------");
		} catch (Exception e) {
			System.out.println(consolestrings.errordisplay);
		} finally {
			DBconn.closeConn(myconn, prep, rs);
		}
	}

	public static int showbase(String fname, String lname) {
		contid = 0;
		f = 0;
		try {
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement(constantquery.contact);
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
			System.out.println(consolestrings.cid);
			contid = in.nextInt();
		} catch (Exception e) {
			System.out.println(consolestrings.errordisplay);
		} finally {
			DBconn.closeConn(myconn, prep, rs1);
		}
		return contid;
	}
}