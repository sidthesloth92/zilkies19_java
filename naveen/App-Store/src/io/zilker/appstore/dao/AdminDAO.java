package io.zilker.appstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import io.zilker.appstore.beans.Administrator;
import io.zilker.appstore.constants.DatabaseQueries;
import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.dbutil.Connections;
import io.zilker.appstore.exceptions.*;

public class AdminDAO {

	private Connection conn;
	private PreparedStatement query;
	private ResultSet result;
	private Connections connections;
	static Logger LOGGER;

	public AdminDAO() {
		LOGGER = Logger.getLogger(AdminDAO.class.getName());
		connections = new Connections();
	}

	public boolean checkAdminUserName(Administrator admin) throws Exception{
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_ADMIN_WITH_USERNAME);
			query.setString(1, admin.getUserName());
			result = query.executeQuery();
			if (!result.next()) {
				return false;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return true;
	}

	public boolean hasAdminReferenceText(String text) throws Exception{
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_REFERENCE_WITH_SECRET_TEXT);
			query.setString(1, text);
			result = query.executeQuery();
			if (!(result.next() && result.getInt("PrivilegeLevel") == 1)) {
				return false;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return true;
	}

	public boolean insertAdmin(Administrator admin) throws Exception{
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.INSERT_ADMIN);
			query.setString(1, admin.getUserName());
			query.setString(2, admin.getPassword());
			int status = query.executeUpdate();
			if (status >= 0) {
				return true;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return false;
	}

	public Administrator getAdmin(Administrator admin) throws Exception{
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_ADMIN_WITH_USERNAME_AND_PASSWORD);
			query.setString(1, admin.getUserName());
			query.setString(2, admin.getPassword());
			result = query.executeQuery();
			if (result.next()) {
				admin.setAdminID(result.getInt("AdminID"));
				return admin;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return null;
	}

	public boolean updateUserName(Administrator admin) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_ADMIN_USERNAME);
			query.setString(1, admin.getUserName());
			query.setInt(2, admin.getAdminID());
			int status = query.executeUpdate();
			if (status >= 0) {
				return true;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION + Errors.UPDATE_FAILED);
		} finally {
			connections.close(conn);
		}
		return false;
	}

	public boolean updatePassword(Administrator admin) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_ADMIN_PASSWORD);
			query.setString(1, admin.getPassword());
			query.setInt(2, admin.getAdminID());
			int status = query.executeUpdate();
			if (status >= 0) {
				return true;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION + Errors.UPDATE_FAILED);
		} finally {
			connections.close(conn);
		}
		return false;
	}

}
