package io.ztech.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.ztech.contacts.beans.Contacts;
import io.ztech.contacts.beans.EmailDetails;
import io.ztech.contacts.beans.HomeDetails;
import io.ztech.contacts.beans.MobileDetails;
import io.ztech.contacts.beans.OfficeDetails;
import io.ztech.contacts.beans.ContactDetails;
import io.ztech.contacts.constants.QueryConstants;
import io.ztech.contacts.dbutils.DBConnection;

//functionalities to search in Db
public class DBSearch {
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Connection con = null;
	static DBConnection db = new DBConnection();
	static QueryConstants q = new QueryConstants();
 
	//search for basic contact
	public Contacts searchfromContacts(String arg) {
		arg = arg + "%";
		Contacts c = new Contacts();
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTCONTACTS);
			ps.setString(1, arg);
			ps.setString(2, arg);
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
			return c;
		}
	}

	//search for entire contact details
	public Contacts searchFromDb(String arg) {
		arg = arg + "%";
		Contacts c = new Contacts();
		try {
			con = db.getConnection();
			ps = con.prepareStatement(q.SELECTMOBILEDETAILS);
			ps.setString(1, arg);
			ps.setString(2, arg);
			ps.setString(3, arg);
			rs = ps.executeQuery();
			while (rs.next()) {
				ContactDetails cd = new ContactDetails();
				cd.setcId(rs.getInt("c_id"));
				cd.setFirstName(rs.getString("first_name"));
				cd.setLastName(rs.getString("last_name"));
				c.contact.add(cd);

				MobileDetails md = new MobileDetails();
				md.setcId(rs.getInt("c_id"));
				md.setmId(rs.getInt("m_id"));
				md.setNumber(rs.getString("mob_num"));
				md.setCountryCode(rs.getString("country_code"));
				c.mobile.add(md);
			}

			ps = con.prepareStatement(q.SELECTOFFICEDETAILS);
			ps.setString(1, arg);
			ps.setString(2, arg);
			ps.setString(3, arg);
			rs = ps.executeQuery();
			while (rs.next()) {
				ContactDetails cd = new ContactDetails();
				cd.setcId(rs.getInt("c_id"));
				cd.setFirstName(rs.getString("first_name"));
				cd.setLastName(rs.getString("last_name"));
				c.contact.add(cd);

				OfficeDetails od = new OfficeDetails();
				od.setcId(rs.getInt("c_id"));
				od.setoId(rs.getInt("o_id"));
				od.setExtn(rs.getString("extn"));
				od.setCountryCode(rs.getString("country_code"));
				od.setAreaCode(rs.getString("area_code"));
				od.setNumber(rs.getString("off_num"));
				c.office.add(od);
			}

			ps = con.prepareStatement(q.SELECTHOMEDETAILS);
			ps.setString(1, arg);
			ps.setString(2, arg);
			ps.setString(3, arg);
			rs = ps.executeQuery();
			while (rs.next()) {
				ContactDetails cd = new ContactDetails();
				cd.setcId(rs.getInt("c_id"));
				cd.setFirstName(rs.getString("first_name"));
				cd.setLastName(rs.getString("last_name"));
				c.contact.add(cd);

				HomeDetails hd = new HomeDetails();
				hd.setcId(rs.getInt("c_id"));
				hd.sethId(rs.getInt("h_id"));
				hd.setCountryCode(rs.getString("country_code"));
				hd.setAreaCode(rs.getString("area_code"));
				hd.setNumber(rs.getString("home_num"));
				c.home.add(hd);
			}

			ps = con.prepareStatement(q.SELECTEMAILDETAILS);
			ps.setString(1, arg);
			ps.setString(2, arg);
			ps.setString(3, arg);
			rs = ps.executeQuery();
			while (rs.next()) {
				ContactDetails cd = new ContactDetails();
				cd.setcId(rs.getInt("c_id"));
				cd.setFirstName(rs.getString("first_name"));
				cd.setLastName(rs.getString("last_name"));
				c.contact.add(cd);

				EmailDetails ed = new EmailDetails();
				ed.setcId(rs.getInt("c_id"));
				ed.seteId(rs.getInt("e_id"));
				ed.setEmailId(rs.getString("email_id"));
				c.email.add(ed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
			return c;

		}
	}
}
