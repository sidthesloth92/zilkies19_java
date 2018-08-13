package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.constant.ConsoleStrings;
import com.zilker.constant.ConstantQuery;
import com.zilker.dbutils.DbConnectivity;

public class Update {
	public static final Logger logger = Logger.getLogger(Update.class.getName());
	private static Connection myconn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs = null, rs1 = null;
	static int cid = 0, f = 0, contid = 0;
	static Scanner in = new Scanner(System.in);

	public static void updateMobile(String coucode, String mobilenum, int mobid) {
		try {
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.UPDATE_MOBILE);
			prep.setString(1, coucode);
			prep.setString(2, mobilenum);
			prep.setInt(3, mobid);
			int flag = prep.executeUpdate();
			if (flag == 1) {
				logger.info(ConsoleStrings.UPDATESUCCESS);
			} else {
				logger.info(ConsoleStrings.UPDATEERROR);
			}
		} catch (Exception e) {
			logger.info(ConsoleStrings.UPDATEERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
	}

	public static void updateOffice(String exnum, String officenum, int oid) {
		try {
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.UPDATE_OFFICE);
			prep.setInt(1, Integer.parseInt(exnum));
			prep.setInt(2, Integer.parseInt(officenum));
			prep.setInt(3, oid);
			int flag = prep.executeUpdate();
			if (flag == 1) {
				logger.info(ConsoleStrings.UPDATESUCCESS);
			} else {
				logger.info(ConsoleStrings.UPDATEERROR);
			}
		} catch (Exception e) {
			logger.info(ConsoleStrings.UPDATEERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
	}

	public static void updateHome(String harea, String hcc, String hnum, int cid) {
		try {
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.UPDATEHOME);
			prep.setInt(1, Integer.parseInt(harea));
			prep.setInt(2, Integer.parseInt(hcc));
			prep.setInt(3, Integer.parseInt(hnum));
			prep.setInt(4, cid);
			int flag = prep.executeUpdate();
			if (flag == 1) {
				logger.info(ConsoleStrings.UPDATESUCCESS);
			} else {
				logger.info(ConsoleStrings.UPDATEERROR);
			}
		} catch (Exception e) {
			logger.info(ConsoleStrings.UPDATEERROR);
		}
		finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
	}

	public static void updateEmail(String nemail, int cid) {
		try {
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement("update email SET email='" + nemail + "' where e_id=" + cid + "");
			int flag = prep.executeUpdate();
			if (flag == 1) {
				logger.info(ConsoleStrings.UPDATESUCCESS);
			} else {
				logger.info(ConsoleStrings.UPDATEERROR);
			}

		} catch (Exception e) {
			logger.info(ConsoleStrings.UPDATEERROR);
		}
		finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
	}

	public static void updateFirstName(String mail, String fname) {
		try {
			f = 0;
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.CONTACTID);
			prep.setString(1, mail);
			rs1 = prep.executeQuery();
			while (rs1.next()) {
				f++;
				int cid = rs1.getInt(1);
				prep = myconn.prepareStatement(ConstantQuery.UPDATE_FIRST_NAME);
				prep.setString(1, fname);
				prep.setInt(2, cid);
				int flag = prep.executeUpdate();
				if (flag == 1) {
					logger.info(ConsoleStrings.UPDATESUCCESS);
				} else {
					logger.info(ConsoleStrings.UPDATEERROR);
				}
			}
			if (f == 0) {
				logger.info(ConsoleStrings.INVALID);
			}
		} catch (Exception e) {
			logger.info(ConsoleStrings.INVALID);
		}
		finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
	}

	public static void updateLastName(String mail, String lname) {
		try {
			f = 0;
			myconn = DbConnectivity.getConnection();
			prep = myconn.prepareStatement(ConstantQuery.CONTACTID);
			prep.setString(1, mail);
			ResultSet rs1 = prep.executeQuery();
			while (rs1.next()) {
				f++;
				int cid = rs1.getInt(1);
				prep = myconn.prepareStatement(ConstantQuery.UPDATE_LAST_NAME);
				prep.setString(1, lname);
				prep.setInt(2, cid);
				int flag = prep.executeUpdate();
				if (flag == 1) {
					logger.info(ConsoleStrings.UPDATESUCCESS);
				} else {
					logger.info(ConsoleStrings.UPDATEERROR);
				}
			}
			if (f == 0) {
				logger.info(ConsoleStrings.INVALID);
			}
		} catch (Exception e) {
			logger.info(ConsoleStrings.INVALID);
		}
		finally {
			DbConnectivity.closeConnection(myconn, prep, null);
		}
	}
}
