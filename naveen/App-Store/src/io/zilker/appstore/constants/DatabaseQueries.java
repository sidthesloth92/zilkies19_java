package io.zilker.appstore.constants;

public class DatabaseQueries {

	public static final String SELECT_USER_WITH_USERNAME = "SELECT * FROM User WHERE UserName = ?;";
	public static final String SELECT_ADMIN_WITH_USERNAME = "SELECT * FROM Admin WHERE UserName = ?;";
	public static final String SELECT_TESTER_WITH_USERNAME = "SELECT * FROM Tester WHERE UserName = ?;";
	public static final String INSERT_USER = "INSERT INTO User(UserName, Password, Wallet, UserPrivilege, FullName) VALUES (?,MD5(?),?,?,?)";
	public static final String INSERT_ADMIN = "INSERT INTO Admin(UserName, Password) VALUES (?, MD5(?));";
	public static final String INSERT_TESTER = "INSERT INTO Tester(UserName, Password) VALUES (?, MD5(?));";
	public static final String SELECT_USER_WITH_USERNAME_AND_PASSWORD = "SELECT * FROM User WHERE UserName = ? AND Password = MD5(?);";
	public static final String SELECT_ADMIN_WITH_USERNAME_AND_PASSWORD = "SELECT * FROM Admin WHERE UserName = ? AND Password = MD5(?);";
	public static final String SELECT_TESTER_WITH_USERNAME_AND_PASSWORD = "SELECT * FROM Tester WHERE UserName = ? AND Password = MD5(?);";
	public static final String SELECT_REFERENCE_WITH_SECRET_TEXT = "SELECT * FROM Reference WHERE SecretText = ?";
	public static final String SELECT_ALL_APPS = "SELECT * FROM Apps WHERE Status = 'p';";
	public static final String SELECT_CATEGORY_WITH_CATEGORY_ID = "SELECT * FROM Category WHERE CategoryID = ?;";
	public static final String INSERT_DOWNLOADS = "INSERT INTO Downloads (AppID, UserID) VALUES (?, ?);";
	public static final String INSERT_WISHLIST = "INSERT INTO WishList (UserID, AppID) VALUES (?, ?);";
	public static final String VIEW_WISHLIST = "SELECT * FROM Apps INNER JOIN WishList ON Apps.AppID = WishList.AppID WHERE WishList.UserID = ?;";
	public static final String HAS_APP_IN_WISHLIST = "SELECT * FROM WishList WHERE UserID = ? AND AppID = ?;";
	public static final String HAS_APP_IN_DOWNLOADS = "SELECT * FROM Downloads WHERE UserID = ? AND AppID = ?;";
	public static final String SELECT_APPS_FROM_DOWNLOADS = "SELECT * FROM Apps INNER JOIN Downloads ON Apps.AppID = Downloads.AppID WHERE Downloads.UserID = ?;";
	public static final String USER_HAS_APP = "SELECT * FROM Downloads WHERE UserID = ? AND AppID = ?;";
	public static final String REPORT_APP = "UPDATE Downloads SET Reported = 1 WHERE AppID = ? AND UserID = ?";
	public static final String SELECT_REPORTED_BY_APPID_AND_USERID = "SELECT * FROM Downloads WHERE AppID = ? AND UserID = ? AND Reported = 1;";
	public static final String INSERT_COMMENT = "INSERT INTO Comments (UserID, AppID, CommentText) VALUES (?,?,?);";
	public static final String INSERT_REVIEW = "UPDATE Downloads SET Rating = ? WHERE UserID = ? AND AppID = ?";
	public static final String UPDATE_USERNAME = "UPDATE User SET UserName = ? WHERE UserID = ?";
	public static final String UPDATE_PASSWORD = "UPDATE User SET Password = MD5(?) WHERE UserID = ?";
	public static final String UPDATE_ADMIN_USERNAME = "UPDATE Admin SET UserName = ? WHERE AdminID = ?";
	public static final String UPDATE_ADMIN_PASSWORD = "UPDATE Admin SET Password = MD5(?) WHERE AdminID = ?";
	public static final String UPDATE_TESTER_USERNAME = "UPDATE Tester SET UserName = ? WHERE TesterID = ?";
	public static final String UPDATE_TESTER_PASSWORD = "UPDATE Tester SET Password = MD5(?) WHERE TesterID = ?";
	public static final String UPDATE_USER_PRIVILEGE = "UPDATE User SET UserPrivilege = ? WHERE UserID = ?";
	public static final String SELECT_APP_RATING = "SELECT AVG(Rating) FROM Downloads WHERE AppID = ?;";
	public static final String DELETE_FROM_WISHLIST = "DELETE FROM WishList WHERE UserID = ? AND AppID = ?";
	public static final String INSERT_APP = "INSERT INTO Apps (UserID, AppName, Description, Status, CategoryID, Cost) VALUES (?,?,?,?,?,?);";
	public static final String GET_CATEGORY = "SELECT * FROM Category;";
	public static final String SELECT_PUBLISHED_APP = "SELECT * FROM Apps WHERE AppID = ? AND Status = ?";
	public static final String SELECT_MY_APPS = "SELECT * FROM Apps WHERE UserID = ?";
	public static final String SELECT_UNPUBLISHED_APPS = "SELECT * FROM Apps WHERE Status = 'u'";
	public static final String SELECT_REPORTED_APPS = "SELECT Apps.AppID, Apps.UserID, Apps.AppName, Apps.Description, Apps.Status, Apps.Cost, Apps.CategoryID, Apps.Created_At, Apps.Updated_At FROM Downloads INNER JOIN Apps ON Apps.AppID = Downloads.AppID WHERE Downloads.Reported = 1 GROUP BY Downloads.AppID HAVING COUNT(*) > 0;";
	public static final String IS_REPORTED_APP = "SELECT Apps.AppID, Apps.UserID, Apps.AppName, Apps.Description, Apps.Status, Apps.Cost, Apps.CategoryID, Apps.Created_At, Apps.Updated_At FROM Downloads INNER JOIN Apps ON Apps.AppID = Downloads.AppID WHERE Downloads.Reported = 1 AND Downloads.AppID = ? GROUP BY Downloads.AppID HAVING COUNT(*) > 0;";
	public static final String IS_UNPUBLISHED_APP = "SELECT * FROM Apps WHERE Status = 'u' AND AppID = ?";
	public static final String IS_TESTER_VERIFIED_APP = "SELECT * FROM Apps WHERE Status = 't' AND AppID = ?";
	public static final String SELECT_USER_RATING = "SELECT AVG(Rating) FROM Downloads WHERE UserID = ?;";
	public static final String SELECT_APPS_WITH_USER_ID = "SELECT * FROM Apps WHERE UserID = ? AND Status = 'p'";
	public static final String UPDATE_APP_TO_TESTER_VERIFIED = "UPDATE Apps SET Status = 't' WHERE AppID = ?";
	public static final String UPDATE_APP_REJECTED = "UPDATE Apps SET Status = 'r' WHERE AppID = ?";
	public static final String UPDATE_APP_TO_ADMIN_VERIFIED = "UPDATE Apps SET Status = 'p' WHERE AppID = ?";
	public static final String SELECT_TESTER_VERIFIED_APPS = "SELECT * FROM Apps WHERE Status = 't'";
	public static final String SELECT_APPS_WITH_APPNAME = "SELECT * FROM Apps WHERE AppName = ? AND Status = 'p'";
	public static final String SELECT_APPS_WITH_APPNAME_SAME_CATEGORY = "SELECT * FROM Apps WHERE AppName = ? AND Status = 'p' AND CategoryID = ?";
	public static final String SELECT_APPS_WITH_DESCRIPTION = "SELECT * FROM Apps WHERE Description = ? AND Status = 'p'";
	public static final String SET_REPORTS_TO_ZERO = "UPDATE Downloads SET Reported = 0 WHERE AppID = ?";
	
}
