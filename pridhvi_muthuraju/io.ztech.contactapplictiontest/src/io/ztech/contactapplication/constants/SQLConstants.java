package io.ztech.contactapplication.constants;

public class SQLConstants {

	public static final String SELECT_ALL_CONTACTS = "select * from contact";
	public static final String SELECT_ALL_CONTACTS_FNAME = "select * from contact order by first_name";
	public static final String SELECT_ALL_CONTACTS_LNAME = "select * from contact order by last_name";
	public static final String INSERT_CONTACT = "insert into contact (first_name,last_name)values(?,?)";
	public static final String INSERT_OFFICE = "insert into office (contact_id,office_extension,office_number)values("
			+ " (select contact_id from contact where first_name=?&&last_name=?)," + "?,?)";
	public static final String INSERT_MOBILE = "insert into mobile (contact_id,country_code,mobile_number)values("
			+ " (select contact_id from contact where first_name=?&&last_name=?)," + "?,?)";
	public static final String INSERT_HOME = "insert into home (contact_id,area_code,home_number)values("
			+ " (select contact_id from contact where first_name=?&&last_name=?),?,?)";
	public static final String INSERT_EMAIL = "insert into email (contact_id,email_id)values("
			+ " (select contact_id from contact where first_name=?&&last_name=?),?)";
	public static final String SELECT_BY_NAME = "select * from contact where first_name=?&&last_name=?";
	public static final String SELECT_BY_FIRST_NAME = "select * from contact where first_name=?";
	public static final String ADD_OFFICE = "insert into office (contact_id,office_extension,office_number)values("
			+ " (select contact_id from contact where first_name=?&&last_name=?),?,?)";
	public static final String SELECT_OFFICE = "select * from office where "
			+ "contact_id=(select contact_id from contact where first_name=?&&last_name=?)";
	public static final String UPDATE_OFFICE = "update office set office_extension=?,office_number=? where contact_id="
			+ "(select contact_id from contact where first_name=?&&last_name=?) and office_id=?";
	public static final String UPDATE_MOBILE = "insert into mobile (contact_id,country_code,mobile_number)values("
			+ " (select contact_id from contact where first_name=?&&last_name=?),?,?)";
	public static final String SELECT_BY_ID_MOBILE = "select * from mobile where "
			+ "contact_id=(select contact_id from contact where first_name=?&&last_name=?)";
	public static final String ALTER_MOBILE = "update mobile set country_code=?,mobile_number=? where contact_id="
			+ "(select contact_id from contact where first_name=?&&last_name=?) and mobile_id=?";
	public static final String ALTER_HOME = "insert into home (contact_id,area_code,home_number)values("
			+ " (select contact_id from contact where first_name=?&&last_name=?),?,?)";
	public static final String SELECT_BY_ID_HOME = "select * from home where "
			+ "contact_id=(select contact_id from contact where first_name=?&&last_name=?)";
	public static final String UPDATE_HOME = "update home set area_code=?,home_number=? where contact_id="
			+ "(select contact_id from contact where first_name=?&&last_name=?) and home_id=?";
	public static final String SELECT_BY_ID_EMAIL = "select * from email where "
			+ "contact_id=(select contact_id from contact where first_name=?&&last_name=?)";
	public static final String UPDATE_EMAIL = "update email set email_id=? where contact_id="
			+ "(select contact_id from contact where first_name=?&&last_name=?) and emailid_id=?";
	public static final String SELECT_CONTACTID_CONTACT = "select contact_id from contact where first_name=? && last_name=?";
	public static final String SELECT_ALL_OFFICE = "select * from office where contact_id=?";
	public static final String SELECT_ALL_HOME = "select * from home where contact_id=?";
	public static final String SELECT_ALL_MOBILE = "select * from mobile where contact_id=?";
	public static final String SELECT_ALL_EMAIL = "select * from email where contact_id=?";

}
