package io.ztech.contactapp.constants;

public class SQLQueryStringConstants {
	public static String INSERT_CONTACT = "insert into contact(firstName, lastName) values (?,?);";
	public static String INSERT_MOBILE = "insert into mobile(country_code, mob_no, contact_id) values (?,?,?);";
	public static String INSERT_OFFICE = "insert into office(ext_no, off_no, contact_id) values (?,?);";
	public static String INSERT_HOME = "insert into home(country_code, area_code, home_no, contact_id) values (?,?,?,?);";
	public static String INSERT_EMAIL = "insert into email(email_id, contact_id) values (?,?);";

	public static String GET_CONTACT_ID_WITH_NAME = "select contact_id from contact c where c.firstName=? and c.lastname=?";
	public static String GET_CONTACTS_WITH_FIRSTNAME = "select * from contact c where c.firstName=?";

	public static String EDIT_CONTACT = "update contact set firstName = ?, lastName =? where contact_id=?";
	public static String EDIT_MOBILE = "update mobile set country_code = ?, mob_no =? where contact_id=? and mobile_id =?";
	public static String EDIT_OFFICE = "update office set ext_no = ?, off_no =? where contact_id=? and office_id =?";
	public static String EDIT_HOME = "update home set country_code = ?, area_code=?, home_no =? where contact_id=? and home_id =?";
	public static String EDIT_EMAIL = "update email set email_id = ? where contact_id=? and mail_id_num =?";

	public static String SORT_CONTACTS_WITH_FIRST_NAME = "select * from contact order by firstName;";
	public static String SORT_CONTACTS_WITH_LAST_NAME = "select * from contact order by lastName;";

	public static String DISPLAY_CONTACT = "select * from contact where contact_id = ?";
	public static String DISPLAY_MOBILE = "select mobile_id, country_code, mob_no from mobile where contact_id = ?";
	public static String DISPLAY_OFFICE = "select office_id,ext_no, off_no from office where contact_id = ?";
	public static String DISPLAY_HOME = "select home_id,country_code, area_code, home_no from home where contact_id = ?";
	public static String DISPLAY_EMAIL = "select mail_id_num,email_id from email where contact_id = ?";
}
