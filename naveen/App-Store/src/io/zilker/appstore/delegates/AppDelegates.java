package io.zilker.appstore.delegates;

import io.zilker.appstore.dao.*;
import io.zilker.appstore.exceptions.*;
import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.Errors;

public class AppDelegates {

	private AppsDAO appsDAO;
	private UserDAO userDAO;

	public AppDelegates() {
		appsDAO = new AppsDAO();
		userDAO = new UserDAO();
	}

	public Applications[] getAllApps() throws Exception {
		return appsDAO.getAllApps();
	}

	public boolean downloadApp(Applications app, User user) throws Exception {
		if (userDAO.getUser(user) == null)
			throw new BusinessException(Errors.INVALID_USERNAME_PASSWORD);
		if (!appsDAO.isPublishedApp(app))
			throw new BusinessException(Errors.NO_SUCH_APP);
		appsDAO.deleteAppFromWishList(user, app);
		return appsDAO.downloadApp(app, user);
	}

	public void addAppToWishList(Applications app, User user) throws Exception {
		if (userDAO.getUser(user) == null)
			throw new BusinessException(Errors.INVALID_USERNAME_PASSWORD);
		if(appsDAO.hasAppInDownloads(app, user))
			throw new BusinessException(Errors.ALREADY_DOWNLOADED_APP);
		appsDAO.addAppToWishList(app, user);
	}

	public Applications[] getWishList(User user) throws Exception {
		if (userDAO.getUser(user) == null)
			throw new BusinessException(Errors.INVALID_USERNAME_PASSWORD);
		return appsDAO.getWishList(user);
	}

	public Applications[] getDownloads(User user) throws Exception {
		if (userDAO.getUser(user) == null)
			throw new BusinessException(Errors.INVALID_USERNAME_PASSWORD);
		return appsDAO.getDownloads(user);
	}

	public void reportApp(User user, Applications app) throws Exception {
		if (userDAO.hasApp(user, app)) {
			appsDAO.reportApp(app, user);
		} else {
			throw new BusinessException(Errors.NO_SUCH_APP);
		}
	}

	public void addReview(User user, Applications app, int review) throws Exception {
		if (userDAO.getUser(user) == null)
			throw new BusinessException(Errors.INVALID_USERNAME_PASSWORD);
		if (userDAO.hasApp(user, app)) {
			appsDAO.addReview(user, app, review);
		} else {
			throw new BusinessException(Errors.NO_SUCH_APP);
		}
	}

	public void addComment(Comments comment) throws Exception {
		User user = new User();
		user.setUserID(comment.getUserID());
		Applications app = new Applications();
		app.setAppID(comment.getAppID());
		if (userDAO.hasApp(user, app)) {
			appsDAO.addComment(comment);
		} else {
			throw new BusinessException(Errors.NO_SUCH_APP);
		}
	}

	public Category[] getCategory(User user) throws Exception {
		return appsDAO.getCategory();
	}

	public void addApps(User user, Applications app) throws Exception {
		appsDAO.addApps(user, app);
	}

	public Applications[] getMyApps(User user) throws Exception {
		return appsDAO.getMyApps(user);
	}

	public Applications[] getUnpublishedApps(Tester tester) throws Exception {
		return appsDAO.getUnpublishedApps();
	}

	public Applications[] getReportedApps(Tester tester) throws Exception {
		return appsDAO.getReportedApps();
	}

	public Applications[] getTesterVerifiedApps(Administrator admin) throws Exception {
		return appsDAO.getTesterVerifiedApps();
	}

	public boolean isUnpublishedApp(Applications app) throws Exception {
		return appsDAO.isUnpublishedApps(app);
	}

	public boolean isTesterVerifiedApp(Applications app) throws Exception {
		return appsDAO.isTesterVerifiedApps(app);
	}

	public void updateRejectedApps(Applications app) throws Exception {
		appsDAO.updateRejectedApp(app);
	}

	public void updateTesterApprovedApps(Applications app) throws Exception {
		appsDAO.updateTesterApproved(app);
		appsDAO.setReportCountToZero(app);
	}

	public void updateAdminApprovedApps(Applications app) throws Exception {
		appsDAO.updateAdminApproved(app);
	}

	public int findAppsWithAppName(Applications app) throws Exception {
		return appsDAO.findAppsWithAppName(app);
	}

	public int findAppsWithAppNameSameCategory(Applications app) throws Exception {
		return appsDAO.findAppsWithAppNameSameCategory(app);
	}

	public int findAppsWithDescription(Applications app) throws Exception {
		return appsDAO.findAppsWithDescription(app);
	}

	public boolean isReportedApp(Applications app) throws Exception {
		return appsDAO.isReportedApp(app);
	}

}
