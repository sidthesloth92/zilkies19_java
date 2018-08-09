package io.ztech.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;

import io.ztech.contacts.beans.ContactDetails;
import io.ztech.contacts.beans.Contacts;
import io.ztech.contacts.beans.EmailDetails;
import io.ztech.contacts.beans.FirstNameCompare;
import io.ztech.contacts.beans.HomeDetails;
import io.ztech.contacts.beans.LastNameCompare;
import io.ztech.contacts.beans.MobileDetails;
import io.ztech.contacts.beans.OfficeDetails;
import io.ztech.contacts.constants.QueryConstants;
import io.ztech.contacts.dbutils.DBConnection;

//functionalities to view Db details
public class DBView {
	static ResultSet rs;
	static PreparedStatement ps;
	static Connection con;
	static DBConnection db = new DBConnection();
	static QueryConstants q = new QueryConstants();
	
	//view basic contact details
	public static Contacts viewContactDetails(int choice) {
		Contacts c = new Contacts();
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTCONTACTDETAILS);
			rs = ps.executeQuery();
			while (rs.next()) {
				ContactDetails cd = new ContactDetails();
				cd.setcId(rs.getInt("c_id"));
				cd.setFirstName(rs.getString("first_name"));
				cd.setLastName(rs.getString("last_name"));
				c.contact.add(cd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
			if(choice == 1)
				Collections.sort(c.contact, new LastNameCompare());
			else
				Collections.sort(c.contact, new FirstNameCompare());
			return c;

		}
	}

	//view mobile number details
	public static Contacts viewMobileDetails(int cId) {
		Contacts c = new Contacts();
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTMOBNUMBERS);
			ps.setInt(1, cId);
			rs = ps.executeQuery();
			while (rs.next()) {
				MobileDetails md = new MobileDetails();
				md.setCountryCode(rs.getString("country_code"));
				md.setNumber(rs.getString("mob_num"));
				c.mobile.add(md);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
			return c;
		}
	}
	
	//view office number details
	public static Contacts viewOfficeDetails(int cId) {
		Contacts c = new Contacts();
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTOFFNUMBERS);
			ps.setInt(1, cId);
			rs = ps.executeQuery();
			while(rs.next()) {
				OfficeDetails od = new OfficeDetails();
				od.setCountryCode(rs.getString("country_code"));
				od.setAreaCode(rs.getString("area_code"));
				od.setExtn(rs.getString("extn"));
				od.setNumber(rs.getString("off_num"));
				c.office.add(od);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
			return c;
		}
	}
	
	//view home number details
	public static Contacts viewHomeDetails(int cId) {
		Contacts c = new Contacts();
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTHOMENUMBERS);
			ps.setInt(1, cId);
			rs = ps.executeQuery();
			while(rs.next()) {
				HomeDetails hd = new HomeDetails();
				hd.setCountryCode(rs.getString("country_code"));
				hd.setAreaCode(rs.getString("area_code"));
				hd.setNumber(rs.getString("home_num"));
				c.home.add(hd);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
			return c;
		}
	}
	
	//view home number details
	public static Contacts viewEmailDetails(int cId) {
		Contacts c = new Contacts();
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTEMAILIDS);
			ps.setInt(1, cId);
			rs = ps.executeQuery();
			while(rs.next()) {
				EmailDetails ed = new EmailDetails();
				ed.setEmailId(rs.getString("email_id"));
				c.email.add(ed);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection(rs, ps, con);
			return c;
		}
	}

}
