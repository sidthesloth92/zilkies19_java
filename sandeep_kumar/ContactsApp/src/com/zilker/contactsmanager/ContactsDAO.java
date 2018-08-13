package com.zilker.contactsmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import com.zilker.config.*;
import com.zilker.sqlconstants.*;

public class ContactsDAO {
	public static final Logger logger = Logger.getLogger("ContactsManager.class");
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public static boolean insertContact(ContactsManager obj) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_INTO_CONTACT_LIST);
			stmt.setString(1, obj.getFirstName());
			stmt.setString(2, obj.getLastName());
			stmt.executeUpdate();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_MAX_ID);
			rs = stmt.executeQuery();
			int primary = 0;
			if (rs.next()) {
				primary = rs.getInt("max");
			}
			for (String x : obj.getOffice()) {
				stmt = Config.conn.prepareStatement(SqlConstants.INSERT_INTO_NUMBER_LIST);
				stmt.setInt(1, primary);
				stmt.setString(2, "office");
				stmt.setString(3, x);
				stmt.executeUpdate();
			}

			for (String x : obj.getHome()) {
				stmt = Config.conn.prepareStatement(SqlConstants.INSERT_INTO_NUMBER_LIST);
				stmt.setInt(1, primary);
				stmt.setString(2, "home");
				stmt.setString(3, x);
				stmt.executeUpdate();
			}
			for (String x : obj.getMobile()) {
				stmt = Config.conn.prepareStatement(SqlConstants.INSERT_INTO_NUMBER_LIST);
				stmt.setInt(1, primary);
				stmt.setString(2, "mobile");
				stmt.setString(3, x);
				stmt.executeUpdate();
			}
			for (String x : obj.getEmail()) {
				stmt = Config.conn.prepareStatement(SqlConstants.INSERT_INTO_NUMBER_LIST);
				stmt.setInt(1, primary);
				stmt.setString(2, "email");
				stmt.setString(3, x);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return true;
	}

	public static ArrayList<HashMap<String, String>> getDetails(String fname,String query) {
		ArrayList<HashMap<String, String>> list=new ArrayList<>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(query);
			if(fname!=null) {
				stmt.setString(1, fname);
			}
			
			rs = stmt.executeQuery();
			while (rs.next()){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("list_id", Integer.toString(rs.getInt("list_id")));
				map.put("firstname", rs.getString("firstname"));
				map.put("lastname", rs.getString("lastname"));
				map.put("num_id", Integer.toString(rs.getInt("num_id")));
				map.put("type", rs.getString("type"));
				map.put("contactdata", rs.getString("contactdata"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return list;
	}
	
	public static boolean updateFirstName(String updateData, int id) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.UPDATE_FIRSTNAME);
			stmt.setString(1, updateData);
			stmt.setInt(2, id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, null);
		}
		return false;
	}

	public static boolean updateLastName(String updateData, int id) {
		try {
			con=Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.UPDATE_LASTNAME);
			stmt.setString(1, updateData);
			stmt.setInt(2, id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Config.closeConnection(con, stmt, null);
		}
		return false;
	}

	public static boolean updateNumberList(String updateData, int id) {
		try {
			con=Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.UPDATE_NUMBER_LIST);
			stmt.setString(1, updateData);
			stmt.setInt(2, id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Config.closeConnection(con, stmt, null);
		}
		return false;
	}

	public static boolean deleteName(int id) {
		try {
			con=Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_NAME);
			stmt.setInt(1, id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Config.closeConnection(con, stmt, null);
		}
		return false;
	}

	public static boolean deleteNumberList(int id) {
		try {
			con=Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_NUMBER_LIST);
			stmt.setInt(1, id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Config.closeConnection(con, stmt, null);
		}
		return false;
	}

	public static String findTypeById(int id) {
		try {
			con=Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.FIND_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("type");
			}
			return "null";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Config.closeConnection(con, stmt, rs);
		}
		return "";
	}

}
