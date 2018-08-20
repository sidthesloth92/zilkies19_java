package io.zilker.appstore.dao;

import java.sql.*;
import java.util.logging.Logger;

import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.DatabaseQueries;
import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.dbutil.Connections;
import io.zilker.appstore.exceptions.*;

public class AppsDAO {

	private Connection conn;
	private PreparedStatement query;
	private ResultSet result;
	private Connections connections;
	static Logger LOGGER;

	public AppsDAO() {
		LOGGER = Logger.getLogger(AppsDAO.class.getName());
		connections = new Connections();
	}

	public Applications[] populateApps(ResultSet result, Applications[] apps) throws Exception {
		int index = 0;
		try {
			while (result.next()) {
				apps[index] = new Applications();
				apps[index].setAppID(result.getInt("Apps.AppID"));
				apps[index].setUserID(result.getInt("Apps.UserID"));
				apps[index].setAppName(result.getString("Apps.AppName"));
				apps[index].setDescription(result.getString("Apps.Description"));
				apps[index].setStatus(result.getString("Apps.Status"));
				apps[index].setCreatedAt(result.getString("Apps.Created_At"));
				apps[index].setUpdatedAt(result.getString("Apps.Updated_At"));
				apps[index].setCost(result.getInt("Apps.Cost"));
				PreparedStatement categoryQuery = conn
						.prepareStatement(DatabaseQueries.SELECT_CATEGORY_WITH_CATEGORY_ID);
				categoryQuery.setInt(1, result.getInt("Apps.CategoryID"));
				ResultSet categoryResult = categoryQuery.executeQuery();
				categoryResult.next();
				apps[index].getCategory().setCategoryID(result.getInt("Apps.CategoryID"));
				apps[index].getCategory().setCategoryName(categoryResult.getString("CategoryName"));
				PreparedStatement ratingQuery = conn.prepareStatement(DatabaseQueries.SELECT_APP_RATING);
				ratingQuery.setInt(1, result.getInt("Apps.AppID"));
				ResultSet ratingResult = ratingQuery.executeQuery();
				if (ratingResult.next())
					apps[index].setReview(ratingResult.getFloat(1));
				else
					apps[index].setReview(0);
				index++;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		}
		return apps;
	}

	public Applications[] getAllApps() throws Exception {
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_ALL_APPS);
			result = query.executeQuery();
			result.last();
			apps = new Applications[result.getRow()];
			result.beforeFirst();
			apps = populateApps(result, apps);
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return apps;
	}

	public boolean hasAppInDownloads(Applications app, User user) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.HAS_APP_IN_DOWNLOADS);
			query.setInt(1, user.getUserID());
			query.setInt(2, app.getAppID());
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

	public boolean isPublishedApp(Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_PUBLISHED_APP);
			query.setInt(1, app.getAppID());
			query.setString(2, "p");
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

	public boolean isReportedApp(Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.IS_REPORTED_APP);
			query.setInt(1, app.getAppID());
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

	public boolean downloadApp(Applications app, User user) throws Exception {
		try {
			if (!hasAppInDownloads(app, user)) {
				conn = connections.open();
				query = conn.prepareStatement(DatabaseQueries.INSERT_DOWNLOADS);
				query.setInt(1, app.getAppID());
				query.setInt(2, user.getUserID());
				int status = query.executeUpdate();
				if (status >= 0)
					return true;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return false;
	}

	public boolean hasAppInWishList(Applications app, User user) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.HAS_APP_IN_WISHLIST);
			query.setInt(1, user.getUserID());
			query.setInt(2, app.getAppID());
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

	public void addAppToWishList(Applications app, User user) throws Exception {
		try {
			if (!hasAppInWishList(app, user)) {
				conn = connections.open();
				query = conn.prepareStatement(DatabaseQueries.INSERT_WISHLIST);
				query.setInt(1, user.getUserID());
				query.setInt(2, app.getAppID());
				int status = query.executeUpdate();
				if (status < 0)
					throw new BusinessException(Errors.NO_SUCH_APP);
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
	}

	public Applications[] getWishList(User user) throws Exception {
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.VIEW_WISHLIST);
			query.setInt(1, user.getUserID());
			result = query.executeQuery();
			result.last();
			apps = new Applications[result.getRow()];
			result.beforeFirst();
			apps = populateApps(result, apps);
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return apps;
	}

	public Applications[] getDownloads(User user) throws Exception {
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_APPS_FROM_DOWNLOADS);
			query.setInt(1, user.getUserID());
			result = query.executeQuery();
			result.last();
			apps = new Applications[result.getRow()];
			result.beforeFirst();
			apps = populateApps(result, apps);
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return apps;
	}

	public void reportApp(Applications app, User user) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_REPORTED_BY_APPID_AND_USERID);
			query.setInt(1, app.getAppID());
			query.setInt(2, user.getUserID());
			result = query.executeQuery();
			if (result.next()) {
				throw new BusinessException(Errors.APP_ALREADY_REPORTED);
			}
			query = conn.prepareStatement(DatabaseQueries.REPORT_APP);
			query.setInt(1, app.getAppID());
			query.setInt(2, user.getUserID());
			int status = query.executeUpdate();
			if (status < 0)
				throw new SystemException(Errors.SQL_EXCEPTION);
		} catch (BusinessException e) {
			throw new BusinessException(Errors.APP_ALREADY_REPORTED);
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
	}

	public void addComment(Comments comment) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.INSERT_COMMENT);
			query.setInt(1, comment.getUserID());
			query.setInt(2, comment.getAppID());
			query.setString(3, comment.getCommentText());
			int status = query.executeUpdate();
			if (status < 0)
				throw new SystemException(Errors.SQL_EXCEPTION);
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
	}

	public void addReview(User user, Applications app, int review) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.INSERT_REVIEW);
			query.setInt(1, review);
			query.setInt(2, user.getUserID());
			query.setInt(3, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0)
				throw new SystemException(Errors.SQL_EXCEPTION);
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
	}

	public void deleteAppFromWishList(User user, Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.DELETE_FROM_WISHLIST);
			query.setInt(1, user.getUserID());
			query.setInt(2, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.SQL_EXCEPTION);
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
	}

	public void addApps(User user, Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.INSERT_APP);
			query.setInt(1, user.getUserID());
			query.setString(2, app.getAppName());
			query.setString(3, app.getDescription());
			query.setString(4, "u");
			query.setInt(5, app.getCategory().getCategoryID());
			query.setInt(6, app.getCost());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.SQL_EXCEPTION);
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
	}

	public Category[] getCategory() throws Exception {
		Category[] category = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.GET_CATEGORY);
			result = query.executeQuery();
			result.last();
			category = new Category[result.getRow()];
			result.beforeFirst();
			int index = 0;
			while (result.next()) {
				category[index] = new Category();
				category[index].setCategoryID(result.getInt("CategoryID"));
				category[index].setCategoryName(result.getString("CategoryName"));
				index++;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return category;
	}

	public Applications[] getMyApps(User user) throws Exception {
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_MY_APPS);
			query.setInt(1, user.getUserID());
			result = query.executeQuery();
			result.last();
			if (result.getRow() > 0) {
				apps = new Applications[result.getRow()];
				result.beforeFirst();
				apps = populateApps(result, apps);
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return apps;
	}

	public Applications[] getUnpublishedApps() throws Exception {
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_UNPUBLISHED_APPS);
			result = query.executeQuery();
			result.last();
			if (result.getRow() > 0) {
				apps = new Applications[result.getRow()];
				result.beforeFirst();
				apps = populateApps(result, apps);
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return apps;
	}

	public Applications[] getReportedApps() throws Exception {
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_REPORTED_APPS);
			result = query.executeQuery();
			result.last();
			if (result.getRow() > 0) {
				apps = new Applications[result.getRow()];
				result.beforeFirst();
				apps = populateApps(result, apps);
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return apps;
	}

	public Applications[] getTesterVerifiedApps() throws Exception {
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_TESTER_VERIFIED_APPS);
			result = query.executeQuery();
			result.last();
			if (result.getRow() > 0) {
				apps = new Applications[result.getRow()];
				result.beforeFirst();
				apps = populateApps(result, apps);
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return apps;
	}

	public boolean isUnpublishedApps(Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.IS_UNPUBLISHED_APP);
			query.setInt(1, app.getAppID());
			result = query.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return false;
	}

	public boolean isTesterVerifiedApps(Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.IS_TESTER_VERIFIED_APP);
			query.setInt(1, app.getAppID());
			result = query.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return false;
	}

	public void updateRejectedApp(Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_APP_REJECTED);
			query.setInt(1, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.DATABASE_ERR);
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
	}

	public void updateTesterApproved(Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_APP_TO_TESTER_VERIFIED);
			query.setInt(1, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.DATABASE_ERR);
			}
		} catch (SystemException e) {
			throw new SystemException(Errors.DATABASE_ERR);
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
	}

	public int findAppsWithAppName(Applications app) throws Exception {
		int total = 0;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_APPS_WITH_APPNAME);
			query.setString(1, app.getAppName());
			result = query.executeQuery();
			result.last();
			total = result.getRow();
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return total;
	}

	public int findAppsWithAppNameSameCategory(Applications app) throws Exception {
		int total = 0;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_APPS_WITH_APPNAME_SAME_CATEGORY);
			query.setString(1, app.getAppName());
			query.setInt(2, app.getCategory().getCategoryID());
			result = query.executeQuery();
			result.last();
			total = result.getRow();
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return total;
	}

	public int findAppsWithDescription(Applications app) throws Exception {
		int total = 0;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_APPS_WITH_APPNAME);
			query.setString(1, app.getDescription());
			result = query.executeQuery();
			result.last();
			total = result.getRow();
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
		return total;
	}

	public void updateAdminApproved(Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_APP_TO_ADMIN_VERIFIED);
			query.setInt(1, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.DATABASE_ERR);
			}
		} catch (SystemException e) {
			throw new SystemException(Errors.DATABASE_ERR);
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
	}

	public void setReportCountToZero(Applications app) throws Exception {
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SET_REPORTS_TO_ZERO);
			query.setInt(1, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.DATABASE_ERR);
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
		}
	}

}
