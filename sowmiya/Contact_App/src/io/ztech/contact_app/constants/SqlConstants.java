package io.ztech.contact_app.constants;

public class SqlConstants {
	public static String INSERT_NAME = "insert into contact (first_name,last_name) values(?,?)";
	public static String INSERT_MOBILE_NUMBER = "insert into mobile_number (contact_id,country_code,mobile_number)values(?,?,?)";
	public static String INSERT_OFFICE_NUMBER = "insert into office_number (contact_id,extension_number,office_number) values(?,?,?)";
	public static String INSERT_HOME_NUMBER = "insert into home_number(contact_id,area_code,home_number) values(?,?,?)";
	public static String INSERT_EMAIL = "insert into email(contact_id,mail) values(?,?)";
	public static String GET_RECENT_CONTACTID = "select max(contact_id) from contact";
	public static String SORT_FIRST_NAME = "select * from contact order by first_name";
	public static String SORT_LAST_NAME = "select * from contact order by last_name";
	public static String VIEW_MOBILE_NUMBER = "select * from mobile_number where contact_id=?";
	public static String VIEW_HOME_NUMBER = "select * from home_number where contact_id=?";
	public static String VIEW_OFFICE_NUMBER = "select * from office_number where contact_id=?";
	public static String VIEW_EMAIL = "select * from email where contact_id=?";
	public static String VIEW_SPECIFIC_CONTACT = "select * from contact where first_name=? and last_name=?";
	public static String EDIT_LAST_NAME = "update contact set last_name=? where contact_id=?";
	public static final String EDIT_MOBILE_NUMBER = "update mobile_number set mobile_number=?, country_code=? where mobile_id=?";
	public static final String EDIT_OFFICE_NUMBER = "update office_number set office_number=?,extension_number=? where office_numberId=?";
	public static final String EDIT_HOME_NUMBER = "update home_number set home_number=?, area_code=? where home_id=?";
	public static final String EDIT_EMAIL = "update email set mail=? where email_id=?";

}
