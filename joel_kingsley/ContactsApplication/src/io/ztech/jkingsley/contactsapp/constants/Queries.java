package io.ztech.jkingsley.contactsapp.constants;

public class Queries {
	
	public static final String INSERT_USER_QUERY = "insert into user values(default,?,?)";
	public static final String INSERT_PHONE_QUERY = "insert into phone values(default,?,?,?)";
	public static final String INSERT_EMAIL_QUERY = "insert into email values(default,?,?)";
	public static final String UPDATE_USER_FIRSTNAME_QUERY= "update user set firstName = ? where id in (?)";
	public static final String UPDATE_USER_LASTNAME_QUERY= "update user set lastName = ? where id in (?)";
	public static final String UPDATE_USER_EMAIL_QUERY= "update email set mail = ? where id in (?) and mail = ?";
	public static final String UPDATE_USER_PHONE_QUERY= "update phone set number = ? where id in (?) and type = ? and number = ?";
	public static final String LIST_ALL_USERS_QUERY = "select * from user;";
	public static final String DISPLAY_USER_QUERY = "select * from user where id in (?)";
	public static final String DISPLAY_USER_PHONE_NUMBERS = "select * from phone where id in (?)";
	public static final String DISPLAY_USER_EMAILS = "select * from email where id in (?)";
}
