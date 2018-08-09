package io.zilker.contacts.services;

import java.util.*;
import io.zilker.contacts.dao.*;
import io.zilker.contacts.beans.*;

public class AddContact {

	public static boolean AddCompleteContact() {
		Contact contact = new Contact();
		contact.getValues();
		int contactID = Insertion.addName(contact.name.firstName, contact.name.lastName);
		AddMobileNumber(contactID, contact.mobile.mobile, contact.mobile.countryCode);
		AddHomeNumber(contactID, contact.home.landline, contact.home.areaCode);
		AddOfficeLandlineNumber(contactID, contact.office.officeLandline.landline, contact.office.officeLandline.areaCode, contact.office.officeExt_L);
		AddOfficeMobileNumber(contactID, contact.office.officeMobile.mobile, contact.office.officeMobile.countryCode, contact.office.officeExt_M);
		return true;
	}

	public static boolean AddMobileNumber(int contactID, ArrayList<String> mobile, ArrayList<String> countryCode) {
		for (int i = 0; i < mobile.size(); i++) {
			int country = Insertion.addCountryCode(countryCode.get(i));
			Insertion.addMobile(contactID, mobile.get(i), country);
		}
		return true;
	}

	public static boolean AddHomeNumber(int contactID, ArrayList<String> home, ArrayList<String> areaCode) {
		for (int i = 0; i < home.size(); i++) {
			int area = Insertion.addAreaCode(areaCode.get(i));
			Insertion.addHome(contactID, home.get(i), area);
		}
		return true;
	}
	
	public static boolean AddOfficeLandlineNumber(int contactID, ArrayList<String> officeLandline, ArrayList<String> officeAreaCode, ArrayList<String> officeExtNumber_L) {
		for (int i = 0; i < officeLandline.size(); i++) {
			int area = Insertion.addAreaCode(officeAreaCode.get(i));
			Insertion.addOffice(contactID, officeLandline.get(i), area, officeExtNumber_L.get(i), 'l');
		}
		return true;
	}
	
	public static boolean AddOfficeMobileNumber(int contactID, ArrayList<String> officeMobile, ArrayList<String> officeCountryCode, ArrayList<String> officeExtNumber_M) {
		for (int i = 0; i < officeMobile.size(); i++) {
			int area = Insertion.addCountryCode(officeCountryCode.get(i));
			Insertion.addOffice(contactID, officeMobile.get(i), area, officeExtNumber_M.get(i), 'm');
		}
		return true;
	}
	
	public static boolean AddEmail(int contactID, ArrayList<String> email) {
		for (int i = 0; i < email.size(); i++) {
			Insertion.addEmail(contactID, email.get(i));
		}
		return true;
	}
	
}
