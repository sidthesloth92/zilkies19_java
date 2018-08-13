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
	public final static String officeDisplay = "SELECT * FROM OFFICE WHERE CONTACT_ID = ?";
	public final static String emailDisplay = "SELECT * FROM EMAIL WHERE CONTACT_ID = ?";
	public final static String sortByFirstName = "SELECT * FROM CONTACT_DETAILS ORDER BY FIRST_NAME";
	public final static String sortByLastName = "SELECT * FROM CONTACT_DETAILS ORDER BY FIRST_NAME";
	public final static String selectByFirstAndLast = "SELECT CONTACT_ID, FIRST_NAME, LAST_NAME FROM CONTACT_DETAILS WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	public final static String updateMobileNumber = "UPDATE MOBILE_NUMBER SET EXTENSION = ?, MOB_NUMBER = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final static String updateHomeNumber = "UPDATE HOME SET AREA_CODE = ?, HOME_NUMBER = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final static String updateOfficeNumber = "UPDATE OFFICE SET OFF_EXT = ?, OFF_NUMBER = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final static String updateEmail = "UPDATE EMAIL SET EMAIL = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final static String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\\\[[0-9]{1,3}\\\\.[0-9]{1,3}\\\\.[0-9]{1,3}\\\\.[0-9]{1,3}\\\\])|(([a-zA-Z\\\\-0-9]+\\\\.)+[a-zA-Z]{2,}))$";
	public final static String mobPattern = "^[789]\\d{9}$";
	public final static String landlinePattern = "^[1-9][0-9]{6}";
	public final static String mobExtension = "^[1-9][0-9]{1}";
	public final static String landlineExtension = "^[1-9][0-9]{2, 3}";
}
