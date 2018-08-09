package com.zilker.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.constant.consolestrings;
import com.zilker.constant.constantquery;
import com.zilker.dbutils.DBconn;

public class Update {
	public static final Logger logger = Logger.getLogger(Update.class.getName());
	private static Connection myconn = null;
	private static PreparedStatement prep = null;
	private static ResultSet rs = null, rs1 = null;
	static int cid = 0, f = 0, contid = 0;
	static Scanner in = new Scanner(System.in);

	public static void updatemobile(String coucode, String mobilenum, int mobid) {
		try {
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement(constantquery.updatemobile);
			prep.setString(1, coucode);
			prep.setString(2, mobilenum);
			prep.setInt(3, mobid);
			int flag = prep.executeUpdate();
			if (flag == 1) {
				logger.info(consolestrings.updatesuccess);
			} else {
				logger.info(consolestrings.updateerror);
			}
		} catch (Exception e) {
			logger.info(consolestrings.updateerror);
		} finally {
			DBconn.closeConn(myconn, prep, null);
		}
	}

	public static void updateoffice(String exnum, String officenum, int oid) {
		try {
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement(constantquery.updateoffice);
			prep.setInt(1, Integer.parseInt(exnum));
			prep.setInt(2, Integer.parseInt(officenum));
			prep.setInt(3, oid);
			int flag = prep.executeUpdate();
			if (flag == 1) {
				logger.info(consolestrings.updatesuccess);
			} else {
				logger.info(consolestrings.updateerror);
			}
		} catch (Exception e) {
			logger.info(consolestrings.updateerror);
		} finally {
			DBconn.closeConn(myconn, prep, null);
		}
	}

	public static void updatehome(String harea, String hcc, String hnum, int cid) {
		try {
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement(constantquery.updatehome);
			prep.setInt(1, Integer.parseInt(harea));
			prep.setInt(2, Integer.parseInt(hcc));
			prep.setInt(3, Integer.parseInt(hnum));
			prep.setInt(4, cid);
			int flag = prep.executeUpdate();
			if (flag == 1) {
				logger.info(consolestrings.updatesuccess);
			} else {
				logger.info(consolestrings.updateerror);
			}
		} catch (Exception e) {
			logger.info(consolestrings.updateerror);
		}
	}

	public static void updateemail(String nemail, int cid) {
		try {
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement("update email SET email='" + nemail + "' where e_id=" + cid + "");
			int flag = prep.executeUpdate();
			if (flag == 1) {
				logger.info(consolestrings.updatesuccess);
			} else {
				logger.info(consolestrings.updateerror);
			}

		} catch (Exception e) {
			logger.info(consolestrings.updateerror);
		}
	}

	public static void updatefname(String mail, String fname) {
		try {
			f = 0;
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement(constantquery.contid);
			prep.setString(1, mail);
			rs1 = prep.executeQuery();
			while (rs1.next()) {
				f++;
				int cid = rs1.getInt(1);
				prep = myconn.prepareStatement(constantquery.updatefname);
				prep.setString(1, fname);
				prep.setInt(2, cid);
				int flag = prep.executeUpdate();
				if (flag == 1) {
					logger.info(consolestrings.updatesuccess);
				} else {
					logger.info(consolestrings.updateerror);
				}
			}
			if (f == 0) {
				logger.info(consolestrings.invalid);
			}
		} catch (Exception e) {
			logger.info(consolestrings.invalid);
		}
	}

	public static void updatelname(String mail, String lname) {
		try {
			f = 0;
			myconn = DBconn.getConn();
			prep = myconn.prepareStatement(constantquery.contid);
			prep.setString(1, mail);
			ResultSet rs1 = prep.executeQuery();
			while (rs1.next()) {
				f++;
				int cid = rs1.getInt(1);
				prep = myconn.prepareStatement(constantquery.updatelname);
				prep.setString(1, lname);
				prep.setInt(2, cid);
				int flag = prep.executeUpdate();
				if (flag == 1) {
					logger.info(consolestrings.updatesuccess);
				} else {
					logger.info(consolestrings.updateerror);
				}
			}
			if (f == 0) {
				logger.info(consolestrings.invalid);
			}
		} catch (Exception e) {
			logger.info(consolestrings.invalid);
		}
	}
}
