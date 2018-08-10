package com.zilker.contactsmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.zilker.dbConfig.DbConfig;
import com.zilker.sqlconstants.*;

public class ContactsDAO {
	public static final Logger logger = Logger.getLogger("ContactsManager.class");
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public static void insertContact(ContactsManager obj) {
		try {
			con = DbConfig.getConnection();
			stmt = DbConfig.conn.prepareStatement(SqlConstants.INSERT_INTO_CONTACT_LIST);
			stmt.setString(1, obj.getFname());
			stmt.setString(2, obj.getLname());
			stmt.executeUpdate();
			stmt = DbConfig.conn.prepareStatement(SqlConstants.GET_MAX_ID);
			rs = stmt.executeQuery();
			int primary = 0;
			if (rs.next()) {
				primary = rs.getInt("max");
			}
			for (String x : obj.getOffice()) {
				stmt = DbConfig.conn.prepareStatement(SqlConstants.INSERT_INTO_NUMBER_LIST);
				stmt.setInt(1, primary);
				stmt.setString(2, "office");
				stmt.setString(3, x);
				stmt.executeUpdate();
			}

			for (String x : obj.getHome()) {
				stmt = DbConfig.conn.prepareStatement(SqlConstants.INSERT_INTO_NUMBER_LIST);
				stmt.setInt(1, primary);
				stmt.setString(2, "home");
				stmt.setString(3, x);
				stmt.executeUpdate();
			}
			for (String x : obj.getMobile()) {
				stmt = DbConfig.conn.prepareStatement(SqlConstants.INSERT_INTO_NUMBER_LIST);
				stmt.setInt(1, primary);
				stmt.setString(2, "mobile");
				stmt.setString(3, x);
				stmt.executeUpdate();
			}
			for (String x : obj.getEmail()) {
				stmt = DbConfig.conn.prepareStatement(SqlConstants.INSERT_INTO_NUMBER_LIST);
				stmt.setInt(1, primary);
				stmt.setString(2, "email");
				stmt.setString(3, x);
				stmt.executeUpdate();
			}
			System.out.println("Successfully Added!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConfig.closeConnection(con, stmt, rs);
		}
	}

	public static boolean printDetails(String fname) {
		try {
			int unique = 0;
			con = DbConfig.getConnection();
			stmt = DbConfig.conn.prepareStatement(SqlConstants.GET_ALL_DETAILS);
			stmt.setString(1, fname);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				System.out.println("No Records Found!");
				return false;
			} else {
				do {
					if (unique != rs.getInt("list_id")) {
						unique = rs.getInt("list_id");
						System.out.println("************************************");
						System.out.println("Unique Number: " + unique);

						System.out.println(" First Name: " + rs.getString("firstname"));
						System.out.println("Last Name: " + rs.getString("lastname"));

					}

					if (rs.getString("type").equals("office")) {
						System.out.println("id: " + rs.getString("num_id") + " Office No: " + rs.getString("contactdata"));
					}
					if (rs.getString("type").equals("home")) {
						System.out.println("id: " + rs.getString("num_id") + " Home No: " + rs.getString("contactdata"));
					}
					if (rs.getString("type").equals("mobile")) {
						System.out.println("id: " + rs.getString("num_id") + " Mobile No: " + rs.getString("contactdata"));
					}
					if (rs.getString("type").equals("email")) {
						System.out.println("id: " + rs.getString("num_id") + " Email: " + rs.getString("contactdata"));
					}
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConfig.closeConnection(con, stmt, rs);
		}
		return true;
	}

	public static void updateFirstName(String updateData, int id) {
		try {
			con = DbConfig.getConnection();
			stmt = DbConfig.conn.prepareStatement(SqlConstants.UPDATE_FIRSTNAME);
			stmt.setString(1, updateData);
			stmt.setInt(2, id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Successfully Updated!");
			} else {
				logger.warning("Invalid Data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConfig.closeConnection(con, stmt, null);
		}
	}

	public static void updateLastName(String updateData, int id) {
		try {
			con=DbConfig.getConnection();
			stmt = DbConfig.conn.prepareStatement(SqlConstants.UPDATE_LASTNAME);
			stmt.setString(1, updateData);
			stmt.setInt(2, id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Successfully Updated!");
			} else {
				logger.warning("Invalid Data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DbConfig.closeConnection(con, stmt, null);
		}
	}

	public static void updateNumberList(String updateData, int id) {
		try {
			con=DbConfig.getConnection();
			stmt = DbConfig.conn.prepareStatement(SqlConstants.UPDATE_NUMBER_LIST);
			stmt.setString(1, updateData);
			stmt.setInt(2, id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Successfully Updated!");
			} else {
				logger.warning("Invalid Data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DbConfig.closeConnection(con, stmt, null);
		}
	}

	public static void deleteName(int id) {
		try {
			con=DbConfig.getConnection();
			stmt = DbConfig.conn.prepareStatement(SqlConstants.DELETE_NAME);
			stmt.setInt(1, id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Successfully Deleted!");
			} else {
				logger.warning("Invalid Data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DbConfig.closeConnection(con, stmt, null);
		}
	}

	public static void deleteNumberList(int id) {
		try {
			con=DbConfig.getConnection();
			stmt = DbConfig.conn.prepareStatement(SqlConstants.DELETE_NUMBER_LIST);
			stmt.setInt(1, id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Successfully Deleted!");
			} else {
				logger.warning("Invalid Data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DbConfig.closeConnection(con, stmt, null);
		}
	}

	public static String findTypeById(int id) {
		try {
			con=DbConfig.getConnection();
			stmt = DbConfig.conn.prepareStatement(SqlConstants.FIND_BY_ID);
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
			DbConfig.closeConnection(con, stmt, rs);
		}
		return "";
	}

	public static void sortList(int order) {
		int unique = 0;
		try {
			con=DbConfig.getConnection();
			if (order == 1) {
				stmt = DbConfig.conn.prepareStatement(SqlConstants.SORT_BY_FNAME);
			} else {
				stmt = DbConfig.conn.prepareStatement(SqlConstants.SORT_BY_LNAME);
			}

			rs = stmt.executeQuery();
			if (!rs.next()) {
				System.out.println("No Records Found!");
			} else {
				do {
					if (unique != rs.getInt("list_id")) {
						unique = rs.getInt("list_id");
						System.out.println("************************************");
						System.out.println("Unique Number: " + unique);

						String fname = rs.getString("firstname");
						System.out.println("First Name: " + fname);

						String lname = rs.getString("lastname");
						System.out.println("Last Name: " + lname);
					}

					if (rs.getString("type").equals("office")) {
						String office = rs.getString("contactdata");
						System.out.println("Office No: " + office);
					} else if (rs.getString("type").equals("home")) {
						String home = rs.getString("contactdata");
						System.out.println("Home No: " + home);
					} else if (rs.getString("type").equals("mobile")) {
						String mobile = rs.getString("contactdata");
						System.out.println("Mobile No: " + mobile);
					} else if (rs.getString("type").equals("email")) {
						String email = rs.getString("contactdata");
						System.out.println("email: " + email);
					}
				} while (rs.next());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DbConfig.closeConnection(con, stmt, rs);
		}
	}
}
