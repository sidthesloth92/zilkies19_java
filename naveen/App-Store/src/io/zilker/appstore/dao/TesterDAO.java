package io.zilker.appstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import io.zilker.appstore.beans.Tester;
import io.zilker.appstore.constants.DatabaseQueries;
import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.dbutil.Connections;
import io.zilker.appstore.exceptions.*;

public class TesterDAO {

	private Connection conn;
	private PreparedStatement query;
	private ResultSet result;
	private Connections connections;
	static Logger LOGGER;

	public TesterDAO() {
		LOGGER = Logger.getLogger(TesterDAO.class.getName());
		connections = new Connections();
	}

	public boolean checkTesterUserName(Tester tester) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_TESTER_WITH_USERNAME);
			query.setString(1, tester.getUserName());
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

	public boolean hasTesterReferenceText(String text) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_REFERENCE_WITH_SECRET_TEXT);
			query.setString(1, text);
			result = query.executeQuery();
			if (result.next() && result.getInt("PrivilegeLevel") == 2) {
				return true;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return false;
	}

	public boolean insertTester(Tester tester) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.INSERT_TESTER);
			query.setString(1, tester.getUserName());
			query.setString(2, tester.getPassword());
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

	public Tester getTester(Tester tester) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_TESTER_WITH_USERNAME_AND_PASSWORD);
			query.setString(1, tester.getUserName());
			query.setString(2, tester.getPassword());
			result = query.executeQuery();
			if (result.next()) {
				tester.setTesterID(result.getInt("TesterID"));
				return tester;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return null;
	}

	public boolean updateUserName(Tester tester) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_TESTER_USERNAME);
			query.setString(1, tester.getUserName());
			query.setInt(2, tester.getTesterID());
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

	public boolean updatePassword(Tester tester) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_PASSWORD);
			query.setString(1, tester.getPassword());
			query.setInt(2, tester.getTesterID());
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

}
