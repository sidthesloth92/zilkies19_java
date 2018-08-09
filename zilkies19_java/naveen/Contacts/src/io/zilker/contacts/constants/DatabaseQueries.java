package io.zilker.contacts.constants;

public class DatabaseQueries {

	public static final String insertName = "INSERT INTO Contact_Name (First_Name, Last_Name) VALUES (?, ?);";
	public static final String getContactFromName = "SELECT * FROM Contact_Name WHERE First_Name=? AND Last_Name=? ORDER BY Contact_ID DESC;";
	public static final String searchCountryCode = "SELECT * FROM Country_Match WHERE Country_Code = ?;";
	public static final String insertCountryCode = "INSERT INTO Country_Match (Country_Code) VALUES (?);";
	public static final String searchAreaCode = "SELECT * FROM Area_Match WHERE Area_Code = ?;";
	public static final String insertAreaCode = "INSERT INTO Area_Match (Area_Code) VALUES (?);";
	public static final String insertMobileNumber = "INSERT INTO Mobile (Contact_ID, M_Number, Country_ID) VALUES (?,?,?);";
	public static final String insertHomeNumber = "INSERT INTO Home (Contact_ID, H_Number, Area_ID) VALUES (?,?,?);";
	public static final String insertOfficeMobile = "INSERT INTO Office (Contact_ID, Off_M_Number, Country_ID, Extension_Number) VALUES (?,?,?,?);";
	public static final String insertOfficeLandline = "INSERT INTO Office (Contact_ID, Off_L_Number, Area_ID, Extension_Number) VALUES (?,?,?,?);";
	public static final String insertemail = "INSERT INTO E_Mail (Contact_ID, EmailID) VALUES (?,?);";
	public static final String deleteContact = "DELETE FROM Contact_Name WHERE Contact_ID = ?;";
	public static final String checkContactID = "SELECT * FROM Contact_Name WHERE Contact_ID = ?;";
	public static final String sortByFirstName = "SELECT * FROM Contact_Name ORDER BY First_Name";
	public static final String sortByLastName = "SELECT * FROM Contact_Name ORDER BY Last_Name";
	public static final String getMobileByContactID = "SELECT * FROM Mobile WHERE Contact_ID = ?;";
	public static final String getCountryCodeWithCountryID = "SELECT * FROM Country_Match WHERE Country_ID=?;";
	public static final String getHomeByContactID = "SELECT * FROM Home WHERE Contact_ID = ?;";
	public static final String getAreaCodeWithAreaID = "SELECT * FROM Area_Match WHERE Area_ID=?;";
	public static final String getOfficeByContactID = "SELECT * FROM Office WHERE Contact_ID = ?;";
	public static final String getEmailByContactID = "SELECT * FROM E_Mail WHERE Contact_ID = ?;";
	public static final String updateFirstName = "UPDATE Contact_Name SET First_Name = ? WHERE Contact_ID = ?;";
	public static final String updateLastName = "UPDATE Contact_Name SET Last_Name = ? WHERE Contact_ID = ?;";
	public static final String searchMobileIDAndContactID = "SELECT * FROM Mobile WHERE Contact_ID = ? AND Mobile_ID = ?;";
	public static final String searchHomeIDAndContactID = "SELECT * FROM Home WHERE Contact_ID = ? AND Home_ID = ?;";
	public static final String searchOfficeIDAndContactID = "SELECT * FROM Office WHERE Contact_ID = ? AND Office_ID = ?;";
	public static final String searchEmailIDAndContactID = "SELECT * FROM E_Mail WHERE Contact_ID = ? AND E_ID = ?;";
	public static final String searchMobileWithContactID = "SELECT * FROM Mobile WHERE Contact_ID = ?";
	public static final String searchHomeWithContactID = "SELECT * FROM Home WHERE Contact_ID = ?";
	public static final String searchOfficeWithContactID = "SELECT * FROM Office WHERE Contact_ID = ?";
	public static final String searchEmailWithContactID = "SELECT * FROM E_Mail WHERE Contact_ID = ?";
	public static final String deleteMobileWithMobileID = "DELETE FROM Mobile WHERE Mobile_ID = ?;";
	public static final String deleteHomeWithHomeID = "DELETE FROM Home WHERE Home_ID = ?;";
	public static final String deleteOfficeWithOfficeID = "DELETE FROM Office WHERE Office_ID = ?;";
	public static final String deleteEmailWithEmailID = "DELETE FROM E_Mail WHERE E_ID = ?;";
	
}
