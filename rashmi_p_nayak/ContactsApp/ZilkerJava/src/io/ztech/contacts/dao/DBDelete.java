package io.ztech.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.ztech.contacts.constants.QueryConstants;
import io.ztech.contacts.dbutils.DBConnection;

//functionalities to delete from DB
public class DBDelete {
	static ResultSet rs= null;
	static PreparedStatement ps = null;
	static Connection con = null;
	static DBConnection db = new DBConnection();
	static QueryConstants q = new QueryConstants();
	
	//deletes entire contact
	public static void deleteContact(int cId) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.DELETECONTACT);
			ps.setInt(1, cId);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//deletes particular mobile number
	public static void deleteMobile(int cId,int mId) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.DELETEMOBNUMBER);
			ps.setInt(1, cId);
			ps.setInt(2, mId);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//deletes particular office number
	public static void deleteOffice(int cId,int oId) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.DELETEOFFNUMBER);
			ps.setInt(1, cId);
			ps.setInt(2, oId);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//deletes particular home number
	public static void deleteHome(int cId,int hId) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.DELETEHOMENUMBER);
			ps.setInt(1, cId);
			ps.setInt(2, hId);
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//deletes particular email
	public static void deleteEmail(int cId,int eId) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.DELETEEMAILID);
			ps.setInt(1, cId);
			ps.setInt(2, eId);
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
