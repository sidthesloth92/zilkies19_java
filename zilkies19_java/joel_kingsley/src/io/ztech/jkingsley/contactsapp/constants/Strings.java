package io.ztech.jkingsley.contactsapp.constants;

public class Strings {
	
	public static final String USER_KEY_ID = "id";
	public static final String USER_KEY_FIRSTNAME = "firstName";
	public static final String USER_KEY_LASTNAME = "lastName";
	public static final String PHONE_KEY_ROWID = "rowid";
	public static final String PHONE_KEY_ID = "id";
	public static final String PHONE_KEY_TYPE = "type";
	public static final String PHONE_KEY_NUMBER = "number";
	public static final String EMAIL_KEY_ROWID = "rowid";
	public static final String EMAIL_KEY_ID = "id";
	public static final String EMAIL_KEY_MAIL = "mail";
	
	public static final String PHONE_OFFICE_TYPE = "office";
	public static final String PHONE_HOME_TYPE = "home";
	public static final String PHONE_MOBILE_TYPE = "mobile";
	
	public static final String INSERT_USER_QUERY = "insert into user values(default,?,?)";
	public static final String INSERT_PHONE_QUERY = "insert into phone values(default,?,?,?)";
	public static final String INSERT_EMAIL_QUERY = "insert into email values(default,?,?)";
	public static final String UPDATE_USER_FIRSTNAME_QUERY= "update user set firstName = ? where id in (?)";
	public static final String UPDATE_USER_LASTNAME_QUERY= "update user set lastName = ? where id in (?)";
	public static final String UPDATE_USER_EMAIL_QUERY= "update email set mail = ? where id in (?)";
	public static final String UPDATE_USER_PHONE_QUERY= "update phone set number = ? where id in (?) and type = '?'";
	public static final String LIST_ALL_USERS_QUERY = "select * from user;";
	public static final String DISPLAY_USER_QUERY = "select * from user where id in (?)";
	public static final String DISPLAY_USER_PHONE_NUMBERS = "select * from phone where id in (?)";
	public static final String DISPLAY_USER_EMAILS = "select * from email where id in (?)";
}
