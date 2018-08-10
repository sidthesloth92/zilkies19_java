package io.ztech.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.ztech.contacts.beans.EmailDetails;
import io.ztech.contacts.beans.HomeDetails;
import io.ztech.contacts.beans.MobileDetails;
import io.ztech.contacts.beans.OfficeDetails;
import io.ztech.contacts.constants.QueryConstants;
import io.ztech.contacts.dbutils.DBConnection;

//functionalities to update Db
public class DBUpdate {
	
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Connection con = null;
	static DBConnection db = new DBConnection();
	static QueryConstants q = new QueryConstants();

	//update first name of contact
	public static void updateFirstName(String firstName, int cId) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.UPDATEFIRSTNAME);
			ps.setString(1, firstName);
			ps.setInt(2, cId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//update last name of contact
	public static void updateLastName(String lastName, int cId) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.UPDATELASTNAME);
			ps.setString(1, lastName);
			ps.setInt(2, cId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
	}

	//update mobile number of contact
	public static void updateMobile(MobileDetails md) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.UPDATEMOBILENUMBER);
			ps.setString(1, md.getNumber());
			ps.setString(2, md.getCountryCode());
			ps.setInt(3, md.getcId());
			ps.setInt(4, md.getmId());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);

		}
	}
	
	//update office number of contact
	public static void updateOffice(OfficeDetails od) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.UPDATEOFFICENUMBER);
			System.out.println("Updated number:+"+od.getCountryCode()+" "+od.getAreaCode()+" "+od.getNumber()+" "+od.getcId()+" "+od.getoId());
			ps.setString(1, od.getNumber());
			ps.setString(2, od.getExtn());
			ps.setString(3, od.getCountryCode());
			ps.setString(4, od.getAreaCode());
			ps.setInt(5, od.getcId());
			ps.setInt(6, od.getoId());
			ps.execute();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//update home number of contact
	public static void updateHome(HomeDetails hd) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.UPDATEHOMENUMBER);
			ps.setString(1, hd.getNumber());
			ps.setString(2, hd.getCountryCode());
			ps.setString(3, hd.getAreaCode());
			ps.setInt(4, hd.getcId());
			ps.setInt(5, hd.gethId());
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//update email id of contact
	public static void updateEmail(EmailDetails ed) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.UPDATEEMAILID);
			ps.setString(1, ed.getEmailId());
			ps.setInt(2, ed.getcId());
			ps.setInt(3, ed.geteId());
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
}
