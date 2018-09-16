package io.zilker.appstore.constants;

public class RegularExpressions {

	public static final String APP_NAME = "[a-zA-Z0-9 ]{1,20}";
	public static final String ID = "[0-9]{1,20}";
	public static final String USER_NAME = "[a-zA-Z0-9]{0,20}";
	public static final String NAME = "[a-zA-Z ]{0,20}";
	public static final String PASSWORD = ".{6,20}";
	public static final String PRIVILEGE = "[yn]{1,1}";
	public static final String COMMENT = ".{1,100}";
	public static final String REVIEW = "[12345]{1,1}";
	public static final String APP_ID = "[0-9]{1,20}";
	public static final String DESCRIPTION = ".{0,100}";
	public static final String COST = "[0-9]{0,8}"; 
	public static final String CATEGORY = "[0-9]{0,3}";
	public static final String YES_NO = "[yn]{1,1}";
	
}
