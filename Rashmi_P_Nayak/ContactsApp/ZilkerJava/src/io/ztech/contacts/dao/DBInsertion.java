package io.ztech.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.contacts.beans.*;
import io.ztech.contacts.dbutils.DBConnection;
import io.ztech.contacts.constants.QueryConstants;;

//functionalities to insert into Db
public class DBInsertion {
	
	static ResultSet rs= null;
	static PreparedStatement ps = null;
	static Connection con = null;
	static DBConnection db = new DBConnection();
	static QueryConstants q = new QueryConstants();
	
	//insert into contact_details
	public void insertIntoContactDetails(ContactDetails c) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.INSERTCONTACTDETAILS);
			ps.setString(1,c.getFirstName());
			ps.setString(2, c.getLastName());
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//get the c_id of most recently inserted tuple
	public int getRecentCId() {
		int cId = -1;
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTRECENTCID);
			rs = ps.executeQuery();
			if(rs.next())
				cId = rs.getInt(1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
			return cId;
		}
	}
	
	//get the m_id of most recently inserted tuple
	public int getRecentMId(int cId) {
		int mID = 1;
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTMID);
			ps.setInt(1, cId);
			rs = ps.executeQuery();
			if(rs.next())
				mID = rs.getInt(1)+1;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
			return mID;
		}
		
	}
	
	//insert into mobile_details
	public void insertIntoMobileDetails(MobileDetails m) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.INSERTMOBILEDETAILS);
			ps.setInt(1, m.getcId());
			ps.setInt(2, m.getmId());
			ps.setString(3, m.getNumber());
			ps.setString(4, m.getCountryCode());
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//get the h_id of most recently inserted tuple
	public int getRecentHId(int cId) {
		int hId = 1;
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTHID);
			ps.setInt(1, cId);
			rs = ps.executeQuery();
			if(rs.next())
				hId = rs.getInt(1)+1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
			return hId;
		}
	}
	
	//insert into home_details
	public void insertIntoHomeDetails(HomeDetails h) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.INSERTHOMEDETAILS);
			ps.setInt(1,h.getcId());
			ps.setInt(2, h.gethId());
			ps.setString(3,h.getNumber());
			ps.setString(4, h.getCountryCode());
			ps.setString(5, h.getAreaCode());
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//get the o_id of most recently inserted tuple
	public int getRecentOId(int cId) {
		int oId = 1;
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTOID);
			ps.setInt(1, cId);
			rs = ps.executeQuery();
			if(rs.next())
				oId = rs.getInt(1)+1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
			return oId;
		}
	}
	//insert into office_details
	public void insertIntoOfficeDetails(OfficeDetails o) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.INSERTOFFICEDETAILS);
			ps.setInt(1, o.getcId());
			ps.setInt(2, o.getoId());
			ps.setString(3, o.getNumber());
			ps.setString(4, o.getExtn());
			ps.setString(5, o.getCountryCode());
			ps.setString(6, o.getAreaCode());
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
		}
	}
	
	//get the e_id of most recently inserted tuple
	public int getRecentEId(int cId) {
		int eId = 1;
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTEID);
			ps.setInt(1, cId);
			rs = ps.executeQuery();
			if(rs.next())
				eId = rs.getInt(1)+1;
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
			return eId;
		}
	}
	
	//insert into email_details
	public void insertIntoEmailDetails(EmailDetails em) {
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.INSERTEMAILDETAILS);
			ps.setInt(1, em.getcId());
			ps.setInt(2,em.geteId());
			ps.setString(3,em.getEmailId());
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
