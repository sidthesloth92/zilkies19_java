package io.ztech.contactapplication.implementation;

import java.sql.SQLException;

import io.ztech.contactapplication.beans.EmailDetails;
import io.ztech.contactapplication.beans.HomeDetails;
import io.ztech.contactapplication.beans.MobileDetails;
import io.ztech.contactapplication.beans.NameDetails;
import io.ztech.contactapplication.beans.OfficeDetails;

public interface ContactDAO {

	public void addNewName(NameDetails nameDetails) throws SQLException;

	public void addNewOffice(NameDetails nameDetails, OfficeDetails officeDetails) throws SQLException;

	public void addNewMobile(NameDetails nameDetails, MobileDetails mobileDetails) throws SQLException;

	public void addNewHome(NameDetails nameDetails, HomeDetails homeDetails) throws SQLException;

	public void addNewContact(NameDetails nameDetails, EmailDetails emailDetails, HomeDetails homeDetails,
			MobileDetails mobileDetails, OfficeDetails officeDetails) throws SQLException;

	public void enterUpdateName(NameDetails nameDetails, EmailDetails emailDetails) throws SQLException;

	public void updateContact(NameDetails nameDetails, EmailDetails emailDetails) throws SQLException;
	
	public void printOffice(NameDetails nameDetails, OfficeDetails officeDetails) throws SQLException;

	public void editOffice(NameDetails nameDetails, OfficeDetails officeDetails) throws SQLException;
	
	public void updateOffice(NameDetails nameDetails, OfficeDetails officeDetails) throws SQLException;
	
	public void printMobile(NameDetails nameDetails, MobileDetails mobileDetails) throws SQLException;

	public void editMobile(NameDetails nameDetails, MobileDetails mobileDetails) throws SQLException;

	public void updateMobile(NameDetails nameDetails, MobileDetails mobileDetails) throws SQLException;
	
	public void printHome(NameDetails nameDetails, HomeDetails homeDetails) throws SQLException;

	public void editHome(NameDetails nameDetails, HomeDetails homeDetails) throws SQLException;
	
	public void updateHome(NameDetails nameDetails, HomeDetails homeDetails) throws SQLException;
	
	public void printEmail(NameDetails nameDetails, EmailDetails emailDetails) throws SQLException ;

	public void editEmail(NameDetails nameDetails, EmailDetails emailDetails) throws SQLException;
	
	public void updateEmail(NameDetails nameDetails, EmailDetails emailDetails) throws SQLException;

	public void searchByFirstName(NameDetails nameDetails, EmailDetails emailDetails, HomeDetails homeDetails,
			MobileDetails mobileDetails, OfficeDetails officeDetails) throws SQLException;

	public void display(String sort, NameDetails nameDetails, EmailDetails emailDetails) throws SQLException;

	public void insertOffice(NameDetails nameDetails, OfficeDetails officeDetails) throws SQLException;

	public void insertMobile(NameDetails nameDetails, MobileDetails mobileDetails) throws SQLException;

	public void insertHome(NameDetails nameDetails, HomeDetails homeDetails) throws SQLException;

	public void insertEmail(NameDetails nameDetails, EmailDetails emailDetails) throws SQLException;
}
