package io.zilker.appstore.controller;

import io.zilker.appstore.beans.*;
import io.zilker.appstore.exceptions.*;
import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.constants.RegularExpressions;
import io.zilker.appstore.delegates.*;

public class AppController {

	ControllerValidator validator;
	AppDelegates appDelegates;

	public AppController() {
		validator = new ControllerValidator();
		appDelegates = new AppDelegates();
	}

	public Applications[] getAllApps() throws Exception {
		return appDelegates.getAllApps();
	}

	public boolean downloadApp(Applications app, User user) throws Exception {
		validator.checkAppID(app);
		validator.checkID(user.getUserID());
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		return appDelegates.downloadApp(app, user);
	}

	public boolean isReportedApp(Applications app) throws Exception {
		return appDelegates.isReportedApp(app);
	}
	
	public void addAppToWishList(Applications app, User user) throws Exception {
		validator.checkAppID(app);
		validator.checkID(user.getUserID());
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		appDelegates.addAppToWishList(app, user);
	}

	public Applications[] getWishList(User user) throws Exception {
		validator.checkID(user.getUserID());
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		return appDelegates.getWishList(user);
	}

	public Applications[] getDownloads(User user) throws Exception {
		validator.checkID(user.getUserID());
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		return appDelegates.getDownloads(user);
	}

	public void reportApp(User user, Applications app) throws Exception {
		validator.checkAppID(app);
		validator.checkID(user.getUserID());
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		appDelegates.reportApp(user, app);
	}

	public void addComment(Comments comment) throws Exception {
		if (!validator.isValidated(String.valueOf(comment.getAppID()), RegularExpressions.APP_ID))
			throw new BusinessException(Errors.NO_SUCH_APP);
		validator.checkID(comment.getUserID());
		appDelegates.addComment(comment);
	}

	public void addReview(User user, Applications app, int review) throws Exception {
		validator.checkAppID(app);
		validator.checkID(user.getUserID());
		validator.checkUserName(user.getUserName());
		validator.checkPassword(user.getPassword());
		appDelegates.addReview(user, app, review);
	}

	public Category[] getCategory(User user) throws Exception {
		return appDelegates.getCategory(user);
	}

	public void addApps(User user, Applications app) throws Exception {
		appDelegates.addApps(user, app);
	}

	public Applications[] getMyApps(User user) throws Exception {
		return appDelegates.getMyApps(user);
	}

	public Applications[] getUnpublishedApps(Tester tester) throws Exception {
		return appDelegates.getUnpublishedApps(tester);
	}
	
	public Applications[] getReportedApps(Tester tester) throws Exception {
		return appDelegates.getReportedApps(tester);
	}
	
	public Applications[] getTesterVerifiedApps(Administrator admin) throws Exception {
		return appDelegates.getTesterVerifiedApps(admin);
	}

	public boolean isUnpublishedApp(Applications app) throws Exception {
		return appDelegates.isUnpublishedApp(app);
	}

	public boolean isTesterVerifiedApp(Applications app) throws Exception {
		return appDelegates.isTesterVerifiedApp(app);
	}
	
	public void updateRejectedApps(Applications app) throws Exception {
		appDelegates.updateRejectedApps(app);
	}
	
	public void updateTesterApprovedApps(Applications app) throws Exception {
		appDelegates.updateTesterApprovedApps(app);
	}
	
	public void updateAdminApprovedApps(Applications app) throws Exception {
		appDelegates.updateAdminApprovedApps(app);
	}
	
	public int findAppsWithAppName(Applications app) throws Exception {
		return appDelegates.findAppsWithAppName(app);
	}
	
	public int findAppsWithAppNameSameCategory(Applications app) throws Exception {
		return appDelegates.findAppsWithAppNameSameCategory(app);
	}
	
	public int findAppsWithDescription(Applications app) throws Exception {
		return appDelegates.findAppsWithDescription(app);
	}
	
}
