package io.ztech.jkingsley.employeemanagement.constants;

public class Query {
	public final static String ADD_EMPLOYEE = "insert into " + Fields.TABLE_EMPLOYEE
			+ " values(default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,default)";
	public static final String ADD_PHONE_NUMBER = "insert into " + Fields.TABLE_PHONE + " values(default,?,?,?)";
	public static final String ADD_MAIL_ADDRESS = "insert into " + Fields.TABLE_MAIL + " values(default,?,?,?)";
	public static final String ADD_EMERGENCY_CONTACT = "insert into " + Fields.TABLE_EMERGENCY_CONTACT
			+ " values(default,?,?,?)";
	public static final String ADD_EXPERIENCE = "insert into " + Fields.TABLE_EXPERIENCE + " values(?,?)";
	public static final String LIST_ALL_DESIGNATIONS = "select * from " + Fields.TABLE_DESIGNATION;
	public static final String LIST_ALL_SKILLS = "select * from " + Fields.TABLE_SKILL;
	public static final String FIND_PASSWORD_OF_EMPLOYEE = "select " + Fields.EMPLOYEE_KEY_PASSWORD + " from "
			+ Fields.TABLE_EMPLOYEE + " where " + Fields.EMPLOYEE_KEY_ID + " = ?";
	public static final String FIND_EMERGENCY_CONTACTS_OF_ID = "select * from " + Fields.TABLE_EMERGENCY_CONTACT
			+ " where " + Fields.EMERGENCY_CONTACT_KEY_EMP_ID + " = ?";
	public static final String FIND_MAILS_OF_ID = "select * from " + Fields.TABLE_MAIL + " where "
			+ Fields.MAIL_KEY_EMP_ID + " = ?";
	public static final String FIND_EMPLOYEE_BY_ID = "select * from " + Fields.TABLE_EMPLOYEE + " where "
			+ Fields.EMPLOYEE_KEY_ID + " = ?";
	public static final String FIND_PHONE_NUMBERS_OF_ID = "select * from " + Fields.TABLE_PHONE + " where "
			+ Fields.PHONE_KEY_EMP_ID + " = ?";
	public static final String FIND_TOTAL_EXPERIENCE_OF_ID = "select * from " + Fields.TABLE_EXPERIENCE + " where "
			+ Fields.EXPERIENCE_KEY_EMP_ID + " = ?";
	public static final String UPDATE_PHONE_NUMBER = "update " + Fields.TABLE_PHONE + " set " + Fields.PHONE_KEY_NUMBER + " = ?, "
			+ Fields.PHONE_KEY_TYPE + " = ? where " + Fields.PHONE_KEY_ID + " = ?";
	public static final String UPDATE_EMERGENCY_CONTACT = "update " + Fields.TABLE_EMERGENCY_CONTACT + " set " + Fields.EMERGENCY_CONTACT_KEY_NAME + " = ?, "
			+ Fields.EMERGENCY_CONTACT_KEY_PHONE + " = ? where " + Fields.EMERGENCY_CONTACT_KEY_ID + " = ?";
	public static final String ADD_SKILL = "insert into " + Fields.TABLE_SKILL + " values(default,?)";
	public static final String ADD_PROJECT = "insert into " + Fields.TABLE_PROJECT + " values(default,?,?,?)";
	public static final String UPDATE_EMPLOYEE_STATUS = "update " + Fields.TABLE_EMPLOYEE + " set " + Fields.EMPLOYEE_KEY_EMP_STATUS + " = ? "
			 + " where " + Fields.EMPLOYEE_KEY_ID + " = ?";
	public static final String LIST_ALL_PROJECTS = "select * from " + Fields.TABLE_PROJECT;

}