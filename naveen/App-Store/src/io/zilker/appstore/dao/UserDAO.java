package io.zilker.appstore.dao;

import java.sql.*;
import java.util.logging.Logger;

import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.*;
import io.zilker.appstore.dbutil.*;
import io.zilker.appstore.exceptions.*;

public class UserDAO {

	private Connection conn;
	private PreparedStatement query;
	private ResultSet result;
	private Connections connections;
	private AppsDAO appsDAO;
	static Logger LOGGER;

	public UserDAO() {
		LOGGER = Logger.getLogger(UserDAO.class.getName());
		appsDAO = new AppsDAO();
		connections = new Connections();
	}

	public boolean checkUserName(User user) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_USER_WITH_USERNAME);
			query.setString(1, user.getUserName());
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

	public boolean insertUser(User user) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.INSERT_USER);
			query.setString(1, user.getUserName());
			query.setString(2, user.getPassword());
			query.setInt(3, user.getWallet());
			query.setString(4, user.getUserPrivilege());
			query.setString(5, user.getFullName());
			int status = query.executeUpdate();
			if (status > 0) {
				return true;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return false;
	}

	public User getUser(User user) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_USER_WITH_USERNAME_AND_PASSWORD);
			query.setString(1, user.getUserName());
			query.setString(2, user.getPassword());
			result = query.executeQuery();
			if (result.next()) {
				user.setFullName(result.getString("FullName"));
				user.setUserID(result.getInt("UserID"));
				user.setWallet(result.getInt("Wallet"));
				user.setUserPrivilege(result.getString("UserPrivilege"));
				user.setWishList(appsDAO.getWishList(user));
				user.setDownloadedApps(appsDAO.getDownloads(user));
				return user;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return null;
	}

	public boolean hasApp(User user, Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.USER_HAS_APP);
			query.setInt(1, user.getUserID());
			query.setInt(2, app.getAppID());
			result = query.executeQuery();
			if (result.next())
				return true;
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return false;
	}

	public boolean updateUserName(User user) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_USERNAME);
			query.setString(1, user.getUserName());
			query.setInt(2, user.getUserID());
			int status = query.executeUpdate();
			if (status >= 0) {
				return true;
			}
		} catch (Exception e) {
			throw new BusinessException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return false;
	}

	public boolean updatePassword(User user) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_PASSWORD);
			query.setString(1, user.getPassword());
			query.setInt(2, user.getUserID());
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

	public boolean updateUserPrivilege(User user) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_USER_PRIVILEGE);
			query.setString(1, user.getUserPrivilege());
			query.setInt(2, user.getUserID());
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

	public float averageUserReviews(User user) throws Exception {
		float rating = 0;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_USER_RATING);
			query.setInt(1, user.getUserID());
			result = query.executeQuery();
			if (result.next())
				rating = result.getFloat(1);
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return rating;
	}

	public int userPublishedAppCount(User user) throws Exception {
		int apps = 0;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_APPS_WITH_USER_ID);
			query.setInt(1, user.getUserID());
			result = query.executeQuery();
			result.last();
			apps = result.getRow();
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return apps;
	}
	
	
}
