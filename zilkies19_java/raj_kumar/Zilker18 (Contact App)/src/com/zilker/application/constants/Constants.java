package com.zilker.application.constants;

public class Constants {
	public final String insertionIntoContactDetail = "INSERT INTO CONTACT_DETAILS (FIRST_NAME, LAST_NAME) VALUES (?, ?)";
	public final String gettingID = "SELECT MAX(CONTACT_ID) FROM CONTACT_DETAILS";
	public final String insertionIntoMobile = "INSERT INTO MOBILE_NUMBER (CONTACT_ID, EXTENSION, MOB_NUMBER) VALUES (?, ?, ?)";
	public final String insertIntoHome = "INSERT INTO HOME (CONTACT_ID, AREA_CODE, HOME_NUMBER) VALUES (?, ?, ?)";
	public final String insertIntoOffice = "INSERT INTO OFFICE (CONTACT_ID, OFF_EXT, OFF_NUMBER) VALUES (?, ?, ?)";
	public final String insertIntoEmail = "INSERT INTO EMAIL (CONTACT_ID, EMAIL) VALUES (?, ?)";
	public final String searchingWithFirstName = "SELECT CONTACT_ID, FIRST_NAME, LAST_NAME FROM CONTACT_DETAILS WHERE FIRST_NAME LIKE ?";
	public final String mobileDisplay = "SELECT * FROM MOBILE_NUMBER WHERE CONTACT_ID = ?";
	public final String homeDisplay = "SELECT * FROM HOME WHERE CONTACT_ID = ?";
	public final String officeDisplay = "SELECT * FROM OFFICE WHERE CONTACT_ID = ?";
	public final String emailDisplay = "SELECT * FROM EMAIL WHERE CONTACT_ID = ?";
	public final String sortByFirstName = "SELECT * FROM CONTACT_DETAILS ORDER BY FIRST_NAME";
	public final String sortByLastName = "SELECT * FROM CONTACT_DETAILS ORDER BY FIRST_NAME DESC";
	public final String selectByFirstAndLast = "SELECT CONTACT_ID, FIRST_NAME, LAST_NAME FROM CONTACT_DETAILS WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	public final String updateMobileNumber = "UPDATE MOBILE_NUMBER SET EXTENSION = ?, MOB_NUMBER = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final String updateHomeNumber = "UPDATE HOME SET AREA_CODE = ?, HOME_NUMBER = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final String updateOfficeNumber = "UPDATE OFFICE SET OFF_EXT = ?, OFF_NUMBER = ? WHERE CONTACT_ID = ? AND ID = ?";
	public final String updateEmail = "UPDATE EMAIL SET EMAIL = ? WHERE CONTACT_ID = ? AND ID = ?";
	
	public void addAnotherNumberPrint() {
		System.out.println("Enter What type of Information you want to add");
		System.out.println("1 .Mobile Number");
		System.out.println("2. Office Number");
		System.out.println("3. Home Number");
		System.out.println("4. Email");
	}
	public void optionAlreadyExists() {
		System.out.println("A Contact with the name already exists !");
		System.out.println("1. Try a New Name ");
		System.out.println("2. Add Another Number or Email");
	}
	public void displayMenu() {
		System.out.println("===========================");
		System.out.println("1. Creation");
		System.out.println("2. Update Contact");
		System.out.println("3. Sort Contact By First Name");
		System.out.println("4. Sort Contact By Last Name");
		System.out.println("5. Exit");
		System.out.println("============================");
	}
}
