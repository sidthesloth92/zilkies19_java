package com.zilker.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.beans.ContactDetails;
import com.zilker.constant.consolestrings;
import com.zilker.constant.constantquery;
import com.zilker.dbutils.DBconn;

public class Insert {
	public static final Logger logger = Logger.getLogger(Insert.class.getName());
	private static Connection myconn = null;
	private static PreparedStatement prep = null;
	static int cid = 0, f = 0, contid = 0;
	static Scanner in = new Scanner(System.in);

	public static int insertcontact(ContactDetails obj) {
		cid = 0;
		try {
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement(constantquery.mail);
			ResultSet rs1 = prep.executeQuery();
			while (rs1.next()) {
				if (obj.getEmail().equals(rs1.getString(3))) {
					logger.info(consolestrings.dup);
					return 0;
				}
			}
			prep = myconn.prepareStatement(constantquery.insertcontact);
			prep.setString(1, obj.getFname());
			prep.setString(2, obj.getLname());
			prep.executeUpdate();
			prep = myconn.prepareStatement(constantquery.cid);
			rs1 = prep.executeQuery();
			while (rs1.next()) {
				cid = rs1.getInt(1);
			}
		} catch (Exception e) {
			logger.info(consolestrings.errorinsert);
		} finally {
			DBconn.closeConn(myconn, prep, null);
		}
		return cid;
	}

	public static void insertemail(int cid, ArrayList<String> al) {
		try {
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement(constantquery.insertemail);
			for (int i = 0; i < al.size(); i++) {
				prep.setInt(1, cid);
				prep.setString(2, al.get(i));
				prep.executeUpdate();
			}
		} catch (Exception e) {
			logger.info(consolestrings.errorinsert);
		} finally {
			DBconn.closeConn(myconn, prep, null);
		}
	}

	public static void insertoffice(int cid, ArrayList<String> al) {
		try {
			myconn = DBconn.getConn();
			for (int i = 0; i < al.size() - 1; i += 2) {
				int exten = Integer.parseInt(al.get(i));
				int office = Integer.parseInt(al.get(i + 1));
				PreparedStatement prep = myconn.prepareStatement(constantquery.insertoffice);
				prep.setInt(1, cid);
				prep.setInt(2, exten);
				prep.setInt(3, office);
				prep.executeUpdate();
			}
		} catch (Exception e) {
			logger.info(consolestrings.errorinsert);
		} finally {
			DBconn.closeConn(myconn, prep, null);
		}
	}

	public static void inserthome(int cid, ArrayList<String> al) {
		try {
			myconn = DBconn.getConn();
			for (int i = 0; i < al.size() - 2; i += 3) {
				int area = Integer.parseInt(al.get(i));
				int cc = Integer.parseInt(al.get(i + 1));
				int home = Integer.parseInt(al.get(i + 2));
				PreparedStatement prep = myconn.prepareStatement(constantquery.inserthome);
				prep.setInt(1, cid);
				prep.setInt(2, area);
				prep.setInt(3, cc);
				prep.setInt(4, home);
				prep.executeUpdate();
			}
		} catch (Exception e) {
			logger.info(consolestrings.errorinsert);
		} finally {
			DBconn.closeConn(myconn, prep, null);
		}
	}

	public static void insertmobile(int cid, ArrayList<String> al) {
		try {
			myconn = DBconn.getConn();
			for (int i = 0; i < al.size() - 1; i += 2) {
				PreparedStatement prep = myconn.prepareStatement(constantquery.insertmobile);
				prep.setInt(1, cid);
				prep.setString(2, al.get(i));
				prep.setString(3, al.get(i + 1));
				prep.executeUpdate();
			}
		} catch (Exception e) {
			logger.info(consolestrings.errorinsert);
		} finally {
			DBconn.closeConn(myconn, prep, null);
		}
	}

}
