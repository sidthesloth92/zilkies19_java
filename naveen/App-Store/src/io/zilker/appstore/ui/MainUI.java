package io.zilker.appstore.ui;

import java.util.*;
import java.util.logging.Logger;

import io.zilker.appstore.constants.*;
import io.zilker.appstore.controller.*;
import io.zilker.appstore.beans.*;

public class MainUI {

	Logger LOGGER;
	int option, id;
	String input;
	Scanner scan;
	boolean validation, optionUsed;
	UIValidator validator;
	UserController userController;
	AdminController adminController;
	TesterController testerController;
	AppController appController;

	public MainUI() {
		LOGGER = Logger.getLogger(MainUI.class.getName());
		adminController = new AdminController();
		scan = new Scanner(System.in);
		validator = new UIValidator();
		userController = new UserController();
		testerController = new TesterController();
		appController = new AppController();
	}

	public void mainOptions() {
		while (true) {
			LOGGER.info(TemplateStrings.SHOW_END_USERS);
			LOGGER.info(TemplateStrings.ENTER_CHOICE);
			try {
				option = Integer.parseInt(scan.nextLine().substring(0, 1));
				EndUserOptions endUserOptions = EndUserOptions.values()[option - 1];
				switch (endUserOptions) {
				case USER:
					userOptions();
					break;
				case ADMIN:
					adminOptions();
					break;
				case TESTER:
					testerOptions();
					break;
				case EXIT:
					return;
				}
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			}
		}
	}

	public void userOptions() {
		while (true) {
			User user = new User();
			LOGGER.info(TemplateStrings.USER_LOGIN_OR_REGISTER);
			LOGGER.info(TemplateStrings.ENTER_CHOICE);
			try {
				option = Integer.parseInt(scan.nextLine().substring(0, 1));
				LoginOrRegisterOption loginOrRegisterOption = LoginOrRegisterOption.values()[option - 1];
				switch (loginOrRegisterOption) {
				case LOGIN:
					userLogin(user);
					break;
				case REGISTER:
					userRegister(user);
					break;
				case EXIT:
					return;
				}
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			}
		}
	}

	public void adminOptions() {
		while (true) {
			Administrator admin = new Administrator();
			LOGGER.info(TemplateStrings.ADMIN_LOGIN_OR_REGISTER);
			LOGGER.info(TemplateStrings.ENTER_CHOICE);
			try {
				option = Integer.parseInt(scan.nextLine().substring(0, 1));
				LoginOrRegisterOption loginOrRegisterOption = LoginOrRegisterOption.values()[option - 1];
				switch (loginOrRegisterOption) {
				case LOGIN:
					adminLogin(admin);
					break;
				case REGISTER:
					adminRegister(admin);
					break;
				case EXIT:
					return;
				}
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			}
		}
	}

	public void testerOptions() {
		while (true) {
			Tester tester = new Tester();
			LOGGER.info(TemplateStrings.TESTER_LOGIN_OR_REGISTER);
			LOGGER.info(TemplateStrings.ENTER_CHOICE);
			try {
				option = Integer.parseInt(scan.nextLine().substring(0, 1));
				LoginOrRegisterOption loginOrRegisterOption = LoginOrRegisterOption.values()[option - 1];
				switch (loginOrRegisterOption) {
				case LOGIN:
					testerLogin(tester);
					break;
				case REGISTER:
					testerRegister(tester);
					break;
				case EXIT:
					return;
				}
			} catch (InputMismatchException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			}
		}
	}

	public void userRegister(User user) {
		boolean registerStatus = true;
		while (registerStatus) {
			registerStatus = false;
			input = getFullName();
			if (input.length() > 0) {
				user.setFullName(input);
				input = getUserName();
				if (input.length() > 0) {
					user.setUserName(input);
					input = getPassword();
					user.setPassword(input);
					input = getPrivilege();
					user.setUserPrivilege(input);
					user.setWallet(0);
					try {
						userController.userRegister(user);
						LOGGER.info(TemplateStrings.ACCOUNT_CREATED);
					} catch (Exception e) {
						LOGGER.info(e.getMessage());
						registerStatus = true;
					}
				}
			}
		}
	}

	public void adminRegister(Administrator admin) {
		boolean registerStatus = true;
		while (registerStatus) {
			registerStatus = false;
			input = getUserName();
			if (input.length() > 0) {
				admin.setUserName(input);
				input = getPassword();
				admin.setPassword(input);
				try {
					adminController.hasReferenceText(getSecretText());
					adminController.adminRegister(admin);
					LOGGER.info(TemplateStrings.ACCOUNT_CREATED);
				} catch (Exception e) {
					LOGGER.info(e.getMessage());
					registerStatus = true;
				}
			}
		}
	}

	public void testerRegister(Tester tester) {
		boolean registerStatus = true;
		while (registerStatus) {
			registerStatus = false;
			input = getUserName();
			if (input.length() > 0) {
				tester.setUserName(input);
				input = getPassword();
				tester.setPassword(input);
				try {
					testerController.hasReferenceText(getSecretText());
					testerController.testerRegister(tester);
					LOGGER.info(TemplateStrings.ACCOUNT_CREATED);
				} catch (Exception e) {
					LOGGER.info(e.getMessage());
					registerStatus = true;
				}
			}
		}
	}

	public void userLogin(User user) {
		input = getUserName();
		if (input.length() > 0) {
			user.setUserName(input);
			input = getPasswordOnce();
			user.setPassword(input);
			try {
				user = userController.userLogin(user);
				userMenu(user);
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}
	}

	public void testerLogin(Tester tester) {
		input = getUserName();
		if (input.length() > 0) {
			tester.setUserName(input);
			input = getPasswordOnce();
			tester.setPassword(input);
			try {
				tester = testerController.testerLogin(tester);
				testerOptions(tester);
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}
	}

	public void adminLogin(Administrator admin) {
		input = getUserName();
		if (input.length() > 0) {
			admin.setUserName(input);
			input = getPasswordOnce();
			admin.setPassword(input);
			try {
				admin = adminController.adminLogin(admin);
				adminOptions(admin);
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}
	}

	public void userMenu(User user) {
		input = null;
		while (true) {
			LOGGER.info(TemplateStrings.SHOW_USER_OPTIONS);
			LOGGER.info(TemplateStrings.ENTER_CHOICE);
			input = scan.nextLine();
			try {
				option = Integer.parseInt(input);
				UserMenuOptions userMenu = UserMenuOptions.values()[option - 1];
				switch (userMenu) {
				case VIEW_ALL_APPS:
					viewAllApps();
					break;
				case VIEW_DOWNLOADED_APPS:
					viewDownloads(user);
					break;
				case DOWNLOAD_APPS:
					downloadApps(user);
					break;
				case VIEW_WISHLIST:
					viewWishList(user);
					break;
				case ADD_APPS_TO_WISHLIST:
					addAppToWishList(user);
					break;
				case USER_SPECIFIC_OPTIONS:
					if (user.getUserPrivilege().compareTo("d") == 0) {
						devSpecificOptions(user);
					} else
						userSpecificOptions(user);
					break;
				case EXIT:
					return;
				}
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			}
		}
	}

	public String getFullName() {
		input = null;
		while (input == null || !validator.isValidated(input, RegularExpressions.NAME, Errors.WRONG_INPUT)) {
			LOGGER.info(TemplateStrings.ASK_FULL_NAME);
			input = scan.nextLine();
		}
		return input;
	}

	public String getUserName() {
		input = null;
		while (input == null || !validator.isValidated(input, RegularExpressions.USER_NAME, Errors.WRONG_INPUT)) {
			LOGGER.info(TemplateStrings.ASK_USER_NAME);
			input = scan.nextLine();
		}
		return input;
	}

	public String getNewUserName() {
		input = null;
		while (input == null || !validator.isValidated(input, RegularExpressions.USER_NAME, Errors.WRONG_INPUT)) {
			LOGGER.info(TemplateStrings.ASK_NEW_USERNAME);
			input = scan.nextLine();
		}
		return input;
	}

	public String getPasswordOnce() {
		input = null;
		validation = false;
		while (input == null || !validation) {
			LOGGER.info(TemplateStrings.ASK_PASSWORD);
			input = scan.nextLine();
			validation = validator.isValidated(input, RegularExpressions.PASSWORD, Errors.PASSWORD_LENGTH);
		}
		return input;
	}

	public String getCurrentPasswordOnce() {
		input = null;
		validation = false;
		while (input == null || !validation) {
			LOGGER.info(TemplateStrings.ASK_CURRENT_PASSWORD);
			input = scan.nextLine();
			validation = validator.isValidated(input, RegularExpressions.PASSWORD, Errors.PASSWORD_LENGTH);
		}
		return input;
	}

	public String getPassword() {
		input = null;
		validation = false;
		while (input == null || !validation) {
			LOGGER.info(TemplateStrings.ASK_PASSWORD);
			input = scan.nextLine();
			validation = validator.isValidated(input, RegularExpressions.PASSWORD, Errors.PASSWORD_LENGTH);
			if (validation) {
				LOGGER.info(TemplateStrings.ASK_PASSWORD_AGAIN);
				if (input.compareTo(scan.nextLine()) != 0) {
					LOGGER.info(Errors.PASSWORD_MISMATCH);
					validation = false;
				}
			}
		}
		return input;
	}

	public String getNewPassword() {
		input = null;
		validation = false;
		while (input == null || !validation) {
			LOGGER.info(TemplateStrings.ASK_NEW_PASSWORD);
			input = scan.nextLine();
			validation = validator.isValidated(input, RegularExpressions.PASSWORD, Errors.PASSWORD_LENGTH);
			if (validation) {
				LOGGER.info(TemplateStrings.ASK_PASSWORD_AGAIN);
				if (input.compareTo(scan.nextLine()) != 0) {
					LOGGER.info(Errors.PASSWORD_MISMATCH);
					validation = false;
				}
			}
		}
		return input;
	}

	public String getSecretText() {
		input = null;
		while (input == null || input.length() == 0) {
			LOGGER.info(TemplateStrings.ASK_SECRET_TEXT);
			input = scan.nextLine();
		}
		return input;
	}

	public String getPrivilege() {
		input = null;
		while (input == null || !validator.isValidated(input, RegularExpressions.PRIVILEGE, Errors.WRONG_INPUT)) {
			LOGGER.info(TemplateStrings.ASK_PRIVILEGE);
			input = scan.nextLine();
		}
		return input.compareTo("y") == 0 ? "d" : "u";
	}

	public void viewAllApps() {
		Applications[] apps;
		try {
			apps = appController.getAllApps();
			printApps(apps);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void printApps(Applications[] apps) {
		LOGGER.info("\n");
		for (int i = 0; i < apps.length; i++) {
			LOGGER.info(TemplateStrings.APP_ID + apps[i].getAppID());
			LOGGER.info(TemplateStrings.APP_NAME + apps[i].getAppName());
			LOGGER.info(TemplateStrings.APP_DESCRIPTION + apps[i].getDescription());
			LOGGER.info(TemplateStrings.APP_CATEGORY + apps[i].getCategory());
			String review = apps[i].getReview() == 0 ? "No one rated this App" : String.valueOf(apps[i].getReview());
			LOGGER.info(TemplateStrings.APP_RATING + review);
			LOGGER.info("\n");
		}
	}

	public void downloadApps(User user) {
		optionUsed = false;
		Applications app = new Applications();
		while (!optionUsed) {
			viewAllApps();
			try {
				id = getAppID();
				app.setAppID(id);
				appController.downloadApp(app, user);
				LOGGER.info(TemplateStrings.APP_DOWNLOADED);
				optionUsed = true;
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}
	}

	public void addAppToWishList(User user) {
		optionUsed = false;
		Applications app = new Applications();
		while (!optionUsed) {
			viewAllApps();
			try {
				id = getAppID();
				app.setAppID(id);
				appController.addAppToWishList(app, user);
				LOGGER.info(TemplateStrings.APP_ADDED_TO_WISHLIST);
				optionUsed = true;
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}
	}

	public void viewWishList(User user) {
		try {
			user.setWishList(appController.getWishList(user));
			if (user.getWishList().length == 0) {
				LOGGER.info(TemplateStrings.NO_APPS_IN_WISHLIST);
				return;
			}
			printApps(user.getWishList());
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void viewDownloads(User user) {
		try {
			user.setDownloadedApps(appController.getDownloads(user));
			if (user.getDownloadedApps().length == 0) {
				LOGGER.info(TemplateStrings.NO_APPS_IN_DOWNLOADS);
				return;
			}
			while (true) {
				printApps(user.getDownloadedApps());
				LOGGER.info(TemplateStrings.DOWNLOADED_APP_OPTIONS);
				LOGGER.info(TemplateStrings.ENTER_CHOICE);
				input = scan.nextLine();
				try {
					option = Integer.parseInt(input);
					DownloadedAppOptions downloadedAppOptions = DownloadedAppOptions.values()[option - 1];
					switch (downloadedAppOptions) {
					case REVIEW:
						review(user);
						break;
					case COMMENT:
						addComment(user);
						break;
					case REPORT:
						report(user);
						break;
					case EXIT:
						return;
					}
				} catch (NumberFormatException e) {
					LOGGER.info(Errors.WRONG_FORMAT);
				} catch (IndexOutOfBoundsException e) {
					LOGGER.info(Errors.WRONG_INPUT);
				}
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void report(User user) {
		optionUsed = false;
		while (!optionUsed) {
			try {
				optionUsed = true;
				option = getAppID();
				Applications app = new Applications();
				app.setAppID(option);
				appController.reportApp(user, app);
				LOGGER.info(TemplateStrings.APP_REPORTED);
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}
	}

	public void addComment(User user) {
		optionUsed = false;
		while (!optionUsed) {
			try {
				id = getAppID();
				Comments comment = new Comments();
				comment.setUserID(user.getUserID());
				comment.setAppID(id);
				validation = false;
				while (!validation) {
					LOGGER.info(TemplateStrings.ENTER_COMMENT);
					input = scan.nextLine();
					validation = validator.isValidated(input, RegularExpressions.COMMENT, Errors.COMMENT_LENGTH_ERR);
				}
				comment.setCommentText(input);
				appController.addComment(comment);
				LOGGER.info(TemplateStrings.COMMENT_ADDED);
				optionUsed = true;
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}
	}

	public void review(User user) {
		optionUsed = false;
		while (!optionUsed) {
			try {
				id = getAppID();
				Applications app = new Applications();
				app.setAppID(id);
				validation = false;
				while (!validation) {
					LOGGER.info(TemplateStrings.ENTER_REVIEW);
					input = scan.nextLine();
					validation = validator.isValidated(input, RegularExpressions.REVIEW, Errors.WRONG_INPUT);
				}
				int review = Integer.parseInt(input);
				appController.addReview(user, app, review);
				LOGGER.info(TemplateStrings.REVIEWED_APP);
				optionUsed = true;
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}
	}

	public int getAppID() {
		validation = false;
		while (!validation) {
			try {
				LOGGER.info(TemplateStrings.ASK_APP_ID);
				input = scan.nextLine();
				id = Integer.parseInt(input);
				validation = true;
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			}
		}
		return id;
	}

	public void userSpecificOptions(User user) {
		while (true) {
			LOGGER.info(TemplateStrings.USER_SPECIFIC_OPTIONS);
			LOGGER.info(TemplateStrings.ENTER_CHOICE);
			input = scan.nextLine();
			try {
				option = Integer.parseInt(input);
				UserSpecificOptions userSpecificOptions = UserSpecificOptions.values()[option - 1];
				switch (userSpecificOptions) {
				case CHANGE_USERNAME:
					changeUserName(user);
					break;
				case CHANGE_PASSWORD:
					changeUserPassword(user);
					break;
				case CHANGE_ACCOUNT_TYPE:
					changeUserPrivilege(user);
					break;
				case EXIT:
					return;
				}
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			}
		}
	}

	public void devSpecificOptions(User user) {
		while (true) {
			LOGGER.info(TemplateStrings.DEV_SPECIFIC_OPTIONS);
			LOGGER.info(TemplateStrings.ENTER_CHOICE);
			input = scan.nextLine();
			try {
				option = Integer.parseInt(input);
				DevSpecificOptions devSpecificOptions = DevSpecificOptions.values()[option - 1];
				switch (devSpecificOptions) {
				case CHANGE_USERNAME:
					changeUserName(user);
					break;
				case CHANGE_PASSWORD:
					changeUserPassword(user);
					break;
				case UPDATE_APPS:
					break;
				case PUBLISH_APPS:
					publishApps(user);
					break;
				case VIEW_MY_APPS:
					getMyApps(user);
					break;
				case EXIT:
					return;
				}
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			}
		}
	}

	public void changeUserName(User user) {
		input = null;
		optionUsed = false;
		while (!optionUsed) {
			while (input == null || input.length() == 0) {
				input = getNewUserName();
				if (input.length() == 0)
					LOGGER.info(Errors.FIELD_SHORT);
			}
			if (input.compareTo(user.getUserName()) == 0) {
				LOGGER.info(TemplateStrings.SAME_USERNAME);
				optionUsed = true;
			} else {
				user.setUserName(input);
				try {
					userController.changeUserName(user);
					LOGGER.info(TemplateStrings.USERNAME_UPDATED);
					optionUsed = true;
				} catch (Exception e) {
					LOGGER.info(e.getMessage());
				}
			}
		}
	}

	public void changeUserPrivilege(User user) {
		optionUsed = false;
		while (!optionUsed) {
			try {
				user.setUserPrivilege(getPrivilege());
				userController.changeUserPrivilege(user);
				optionUsed = true;
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}
	}

	public void changeAdminUserName(Administrator admin) {
		input = null;
		optionUsed = false;
		while (!optionUsed) {
			while (input == null || input.length() == 0) {
				input = getNewUserName();
				if (input.length() == 0)
					LOGGER.info(Errors.FIELD_SHORT);
			}
			if (input.compareTo(admin.getUserName()) == 0) {
				LOGGER.info(TemplateStrings.SAME_USERNAME);
				optionUsed = true;
			} else {
				admin.setUserName(input);
				try {
					adminController.changeUserName(admin);
					LOGGER.info(TemplateStrings.USERNAME_UPDATED);
					optionUsed = true;
				} catch (Exception e) {
					LOGGER.info(e.getMessage());
				}
			}
		}
	}

	public void changeTesterUserName(Tester tester) {
		input = null;
		optionUsed = false;
		while (!optionUsed) {
			while (input == null || input.length() == 0) {
				input = getNewUserName();
				if (input.length() == 0)
					LOGGER.info(Errors.FIELD_SHORT);
			}
			if (input.compareTo(tester.getUserName()) == 0) {
				LOGGER.info(TemplateStrings.SAME_USERNAME);
				optionUsed = true;
			} else {
				tester.setUserName(input);
				try {
					testerController.changeUserName(tester);
					LOGGER.info(TemplateStrings.USERNAME_UPDATED);
					optionUsed = true;
				} catch (Exception e) {
					LOGGER.info(e.getMessage());
				}
			}
		}
	}

	public void changeUserPassword(User user) {
		if (getCurrentPasswordOnce().compareTo(user.getPassword()) != 0) {
			LOGGER.info(Errors.INVALID_PASSWORD);
			return;
		}
		input = getNewPassword();
		user.setPassword(input);
		try {
			userController.changePassword(user);
			LOGGER.info(TemplateStrings.PASSWORD_UPDATED);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void changeAdminPassword(Administrator admin) {
		if (getCurrentPasswordOnce().compareTo(admin.getPassword()) != 0) {
			LOGGER.info(Errors.INVALID_PASSWORD);
			return;
		}
		input = getNewPassword();
		admin.setPassword(input);
		try {
			adminController.changePassword(admin);
			LOGGER.info(TemplateStrings.PASSWORD_UPDATED);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void changeTesterPassword(Tester tester) {
		if (getCurrentPasswordOnce().compareTo(tester.getPassword()) != 0) {
			LOGGER.info(Errors.INVALID_PASSWORD);
			return;
		}
		input = getNewPassword();
		tester.setPassword(input);
		try {
			testerController.changePassword(tester);
			LOGGER.info(TemplateStrings.PASSWORD_UPDATED);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void publishApps(User user) {
		Applications apps = new Applications();
		apps.setAppName(getAppName());
		apps.setDescription(getAppDescription());
		apps.setCost(getAppCost());
		try {
			Category[] categories = appController.getCategory(user);
			printCategories(categories);
			apps.getCategory().setCategoryID(getCategoryID());
			appController.addApps(user, apps);
			LOGGER.info(TemplateStrings.APP_SUBMITTED);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	public int getCategoryID() {
		input = null;
		while (input == null
				|| !validator.isValidated(input, RegularExpressions.CATEGORY, Errors.CATEGORY_ID_INVALID)) {
			LOGGER.info(TemplateStrings.CHOOSE_CATEGORY);
			input = scan.nextLine();
		}
		return Integer.parseInt(input);
	}

	public String getAppName() {
		input = null;
		while (input == null || !validator.isValidated(input, RegularExpressions.APP_NAME, Errors.INVALID_APP_NAME)) {
			LOGGER.info(TemplateStrings.ASK_APP_NAME);
			input = scan.nextLine();
		}
		return input;
	}

	public String getAppDescription() {
		input = null;
		while (input == null
				|| !validator.isValidated(input, RegularExpressions.DESCRIPTION, Errors.INVALID_APP_DESCRIPTION)) {
			LOGGER.info(TemplateStrings.ASK_APP_DESCRIPTION);
			input = scan.nextLine();
		}
		return input;
	}

	public int getAppCost() {
		input = null;
		while (input == null || !validator.isValidated(input, RegularExpressions.COST, Errors.COST_ERROR)
				|| input.length() == 0) {
			LOGGER.info(TemplateStrings.ASK_APP_COST);
			input = scan.nextLine();
		}
		return Integer.parseInt(input);
	}

	public void printCategories(Category[] categories) {
		for (int i = 0; i < categories.length; i++) {
			LOGGER.info(TemplateStrings.CATEGORY_ID + categories[i].getCategoryID());
			LOGGER.info(TemplateStrings.CATEGORY_NAME + categories[i].getCategoryName());
		}
	}

	public void getMyApps(User user) {
		try {
			Applications[] apps = appController.getMyApps(user);
			for (int i = 0; i < apps.length; i++) {
				LOGGER.info(TemplateStrings.APP_NAME + apps[i].getAppName());
				String status = "";
				if (apps[i].getStatus().compareTo("u") == 0)
					status = TemplateStrings.APP_YET_TO_BE_TESTED;
				else if (apps[i].getStatus().compareTo("p") == 0)
					status = TemplateStrings.PUBLISHED;
				else if (apps[i].getStatus().compareTo("r") == 0)
					status = TemplateStrings.REJECTED;
				else if (apps[i].getStatus().compareTo("t") == 0)
					status = TemplateStrings.APP_TESTED;
				else
					status = TemplateStrings.UNKNOWN;
				LOGGER.info(TemplateStrings.APP_STATUS + status);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void testerOptions(Tester tester) {
		input = null;
		while (true) {
			LOGGER.info(TemplateStrings.TESTER_OPTIONS);
			LOGGER.info(TemplateStrings.ENTER_CHOICE);
			input = scan.nextLine();
			try {
				option = Integer.parseInt(input);
				TesterOptions testerOption = TesterOptions.values()[option - 1];
				switch (testerOption) {
				case REVIEW_NEWLY_PUBLISHED_APPS:
					reviewApps(tester, 'n');
					break;
				case REVIEW_REPORTED_APPS:
					reviewApps(tester, 'r');
					break;
				case CHANGE_USERNAME:
					changeTesterUserName(tester);
					break;
				case CHANGE_PASSWORD:
					changeTesterPassword(tester);
					break;
				case EXIT:
					return;
				}
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			}
		}
	}

	public void reviewApps(Tester tester, char rejected) {
		while (true) {
			try {
				Applications[] unpublishedApps;
				if (rejected == 'n') {
					unpublishedApps = appController.getUnpublishedApps(tester);
				} else {
					unpublishedApps = appController.getReportedApps(tester);
				}
				if (unpublishedApps == null) {
					LOGGER.info(TemplateStrings.NO_PENDING_APPS_TO_REVIEW);
					return;
				}
				printUnpublishedApps(unpublishedApps);
				LOGGER.info(TemplateStrings.APP_ID_TO_REVIEW);
				input = scan.nextLine();
				if (input.length() == 0)
					return;
				id = Integer.parseInt(input);
				Applications app = new Applications();
				app.setAppID(id);
				for (int i = 0; i < unpublishedApps.length; i++) {
					if (app.getAppID() == unpublishedApps[i].getAppID())
						app = unpublishedApps[i];
				}
				boolean validApp;
				if (rejected == 'r')
					validApp = appController.isReportedApp(app);
				else
					validApp = appController.isUnpublishedApp(app);
				if (validApp) {
					optionUsed = false;
					while (!optionUsed) {
						LOGGER.info(TemplateStrings.TESTER_TESTING_OPTIONS);
						LOGGER.info(TemplateStrings.ENTER_CHOICE);
						input = scan.nextLine();
						option = Integer.parseInt(input);
						TestingOptions testingOptions = TestingOptions.values()[option - 1];
						switch (testingOptions) {
						case USER_PROFILE:
							printUserProfile(app);
							break;
						case FIND_APP_DUPLICATES:
							findAppDuplicates(app);
							break;
						case ACCEPT_REJECT_APPS:
							acceptRejectApps(tester, app);
							break;
						case EXIT:
							optionUsed = true;
							break;
						}
					}
				} else {
					LOGGER.info(Errors.INVALID_APP_ID);
				}
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			} catch (Exception e) {
				LOGGER.info(Errors.DATABASE_ERR);
			}
		}
	}

	public void findAppDuplicates(Applications app) {
		try {
			int duplicateAppNames = appController.findAppsWithAppName(app);
			int duplicateDescription = appController.findAppsWithDescription(app);
			int duplicateAppNamesSameCategory = appController.findAppsWithAppNameSameCategory(app);
			LOGGER.info(TemplateStrings.SAME_APP_NAME + duplicateAppNames);
			LOGGER.info(TemplateStrings.SAME_APP_NAME_SAME_CATEGORY + duplicateAppNamesSameCategory);
			LOGGER.info(TemplateStrings.SAME_DESCRIPTION + duplicateDescription);
		} catch (Exception e) {
			LOGGER.info(Errors.DATABASE_ERR);
		}
	}

	public void acceptRejectApps(Tester tester, Applications app) {
		char option = approvePermission();
		try {
			if (option == 'y') {
				appController.updateTesterApprovedApps(app);
				LOGGER.info(TemplateStrings.APP_APPROVED);
			} else {
				appController.updateRejectedApps(app);
				LOGGER.info(TemplateStrings.APP_REJECTED);
			}
		} catch (Exception e) {
			LOGGER.info(Errors.DATABASE_ERR);
		}
	}

	public void acceptRejectAppsByAdmin(Administrator admin, Applications app) {
		char option = approvePermission();
		try {
			if (option == 'y') {
				appController.updateAdminApprovedApps(app);
				LOGGER.info(TemplateStrings.APP_APPROVED);
			} else {
				appController.updateRejectedApps(app);
				LOGGER.info(TemplateStrings.APP_REJECTED);
			}
		} catch (Exception e) {
			LOGGER.info(Errors.DATABASE_ERR);
		}
	}

	public void printUserProfile(Applications app) {
		try {
			User user = new User();
			user.setUserID(app.getUserID());
			float rating = userController.getUserRating(user);
			int totalApps = userController.getUserPublishedAppCount(user);
			LOGGER.info("\n");
			LOGGER.info(TemplateStrings.USER_ID + app.getUserID());
			LOGGER.info(TemplateStrings.AVERAGE_APP_RATING + rating);
			LOGGER.info(TemplateStrings.APP_COUNT + totalApps);
			LOGGER.info("\n");
		} catch (Exception e) {
			LOGGER.info(Errors.DATABASE_ERR);
		}
	}

	public void printUnpublishedApps(Applications[] apps) {
		LOGGER.info("\n");
		if (apps != null) {
			for (int i = 0; i < apps.length; i++) {
				LOGGER.info(TemplateStrings.APP_ID + apps[i].getAppID());
				LOGGER.info(TemplateStrings.APP_NAME + apps[i].getAppName());
				LOGGER.info(TemplateStrings.APP_DESCRIPTION + apps[i].getDescription());
				LOGGER.info(TemplateStrings.APP_CATEGORY + apps[i].getCategory().getCategoryName());
				LOGGER.info(TemplateStrings.USER_ID + apps[i].getUserID());
				LOGGER.info("\n");
			}
		}
	}

	public char approvePermission() {
		input = null;
		while (input == null || !validator.isValidated(input, RegularExpressions.YES_NO, Errors.WRONG_INPUT)) {
			LOGGER.info(TemplateStrings.WISH_TO_APPROVE);
			input = scan.nextLine();
		}
		return input.charAt(0);
	}

	public void adminOptions(Administrator admin) {
		input = null;
		while (true) {
			LOGGER.info(TemplateStrings.ADMIN_OPTIONS);
			LOGGER.info(TemplateStrings.ENTER_CHOICE);
			input = scan.nextLine();
			try {
				option = Integer.parseInt(input);
				AdminOptions adminOptions = AdminOptions.values()[option - 1];
				switch (adminOptions) {
				case REVIEW_TESTED_APPS:
					reviewTestedApps(admin);
					break;
				case CHANGE_USERNAME:
					changeAdminUserName(admin);
					break;
				case CHANGE_PASSWORD:
					changeAdminPassword(admin);
					break;
				case EXIT:
					return;
				}
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			}
		}
	}

	public void reviewTestedApps(Administrator admin) {
		while (true) {
			try {
				Applications[] testerVerifiedApps = appController.getTesterVerifiedApps(admin);
				if (testerVerifiedApps == null) {
					LOGGER.info(TemplateStrings.NO_PENDING_APPS_TO_REVIEW);
					return;
				}
				printUnpublishedApps(testerVerifiedApps);
				LOGGER.info(TemplateStrings.APP_ID_TO_REVIEW);
				input = scan.nextLine();
				if (input.length() == 0)
					return;
				id = Integer.parseInt(input);
				Applications app = new Applications();
				app.setAppID(id);
				for (int i = 0; i < testerVerifiedApps.length; i++) {
					if (app.getAppID() == testerVerifiedApps[i].getAppID())
						app = testerVerifiedApps[i];
				}
				if (appController.isTesterVerifiedApp(app)) {
					optionUsed = false;
					while (!optionUsed) {
						LOGGER.info(TemplateStrings.ADMIN_PUBLISHING_OPTIONS);
						LOGGER.info(TemplateStrings.ENTER_CHOICE);
						input = scan.nextLine();
						option = Integer.parseInt(input);
						AdminAppCheckingOptions appCheckOptions = AdminAppCheckingOptions.values()[option - 1];
						switch (appCheckOptions) {
						case USER_PROFILE:
							printUserProfile(app);
							break;
						case ACCEPT_REJECT_APPS:
							acceptRejectAppsByAdmin(admin, app);
							break;
						case EXIT:
							optionUsed = true;
							break;
						}
					}
				} else {
					LOGGER.info(Errors.INVALID_APP_ID);
				}
			} catch (NumberFormatException e) {
				LOGGER.info(Errors.WRONG_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.info(Errors.WRONG_INPUT);
			} catch (Exception e) {
				LOGGER.info(Errors.DATABASE_ERR);
			}
		}
	}

}
