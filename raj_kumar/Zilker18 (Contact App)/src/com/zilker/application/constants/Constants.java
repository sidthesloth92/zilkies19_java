package com.zilker.application.constants;

public class Constants {
	public final static String insertionIntoContactDetail = "INSERT INTO CONTACT_DETAILS (FIRST_NAME, LAST_NAME) VALUES (?, ?)";
	public final static String gettingID = "SELECT MAX(CONTACT_ID) FROM CONTACT_DETAILS";
	public final static String insertionIntoMobile = "INSERT INTO MOBILE_NUMBER (CONTACT_ID, EXTENSION, MOB_NUMBER) VALUES (?, ?, ?)";
	public final static String insertIntoHome = "INSERT INTO HOME (CONTACT_ID, AREA_CODE, HOME_NUMBER) VALUES (?, ?, ?)";
	public final static String insertIntoOffice = "INSERT INTO OFFICE (CONTACT_ID, OFF_EXT, OFF_NUMBER) VALUES (?, ?, ?)";
	public final static String insertIntoEmail = "INSERT INTO EMAIL (CONTACT_ID, EMAIL) VALUES (?, ?)";
	public final static String searchingWithFirstName = "SELECT CONTACT_ID, FIRST_NAME, LAST_NAME FROM CONTACT_DETAILS WHERE FIRST_NAME LIKE ?";
	public final static String mobileDisplay = "SELECT * FROM MOBILE_NUMBER WHERE CONTACT_ID = ?";
	public final static String homeDisplay = "SELECT * FROM HOME WHERE CONTACT_ID = ?";
	public final static String OFFICE_DISPLAY = "SELECT * FROM OFFICE WHERE CONTACT_ID = ?";
	public final static String EMAIL_DISPLAY = "SELECT * FROM EMAIL WHERE CONTACT_ID = ?";
	public final static String SORT_BY_FIRST_NAME = "SELECT * FROM CONTACT_DETAILS ORDER BY FIRST_NAME";
	public final static String SORT_BY_LAST_NAME = "SELECT * FROM CONTACT_DETAILS ORDER BY FIRST_NAME";
	public final static String SELECT_BY_FIRST_LAST = "SELECT CONTACT_ID, FIRST_NAME, LAST_NAME FROM CONTACT_DETAILS WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	public final static String UPDATE_MOBILE_NUMBER = "UPDATE MOBILE_NUMBER SET EXTENSION = ?, MOB_NUMBER = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final static String UPDATE_HOME_NUMBER = "UPDATE HOME SET AREA_CODE = ?, HOME_NUMBER = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final static String UPDATE_OFFICE_NUM = "UPDATE OFFICE SET OFF_EXT = ?, OFF_NUMBER = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final static String UPDATE_EMAIL = "UPDATE EMAIL SET EMAIL = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final static String E_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
	public final static String MOB_PATTERN = "^[789]\\d{9}$";
	public final static String LANDLINE_PATTERN = "^[1-9][0-9]{6}";
	public final static String MOBILE_EX = "^[1-9][0-9]{1}";
	public final static String LAND_LINE_EX = "^[1-9][0-9]{2,3}";
	public final static String NAME_REG_EX = "^[A-Za-z0-9]+";
}
