package io.zilker.contacts.services;

import java.util.*;

import io.zilker.contacts.*;
import io.zilker.contacts.constants.*;
import io.zilker.contacts.dao.Check;
import io.zilker.contacts.dao.Deletion;
import io.zilker.contacts.dao.Updation;
import io.zilker.contacts.dao.View;
import io.zilker.contacts.beans.*;

public class EditContact {

	static Scanner scan = new Scanner(System.in);
	static int contactID;
	static String getter;

	public static boolean Edit() {
		String getter = getContactID();
		contactID = -1;
		if (getter.length() > 0)
			contactID = Integer.parseInt(getter);
		if (contactID != -1) {
			getter = null;
			while (getter == null || (getter.length() > 0 && getter.charAt(0) != '7')) {
				if (getter != null && getter.charAt(0) == '1') {
					ContactName name = new ContactName();
					ContactName.getFirstName(name);
					if (Updation.UpdateFirstName(contactID, name.firstName))
						System.out.println(TemplateStrings.fieldUpdated);
				} else if (getter != null && getter.charAt(0) == '2') {
					ContactName name = new ContactName();
					ContactName.getLastName(name);
					if (Updation.UpdateLastName(contactID, name.lastName))
						System.out.println(TemplateStrings.fieldUpdated);
				} else if (getter != null && getter.charAt(0) == '3') {
					getter = getEditOptionUtil();
					if (getter.length() > 0) {
						if (getter.charAt(0) == '1') {
							if (AddMobile())
								System.out.println(TemplateStrings.fieldUpdated);
						} else if (getter.charAt(0) == '2') {
							getter = getMobileID(contactID);
							if (getter.length() > 0) {
								if (AddMobile()) {
									Deletion.deleteMobile(Integer.parseInt(getter));
									System.out.println(TemplateStrings.fieldUpdated);
								}
							}
						} else if (getter.charAt(0) == '3') {
							getter = getMobileID(contactID);
							if (getter.length() > 0) {
								if (Deletion.deleteMobile(Integer.parseInt(getter)))
									System.out.println(TemplateStrings.fieldUpdated);
							}
						}
					}
				} else if (getter != null && getter.charAt(0) == '4') {
					getter = getEditOptionUtil();
					if (getter.length() > 0) {
						if (getter.charAt(0) == '1') {
							if (AddHome())
								System.out.println(TemplateStrings.fieldUpdated);
						} else if (getter.charAt(0) == '2') {
							getter = getHomeID(contactID);
							if (getter.length() > 0) {
								if (AddHome()) {
									Deletion.deleteHome(Integer.parseInt(getter));
									System.out.println(TemplateStrings.fieldUpdated);
								}
							}
						} else if (getter.charAt(0) == '3') {
							getter = getHomeID(contactID);
							if (getter.length() > 0) {
								if (Deletion.deleteHome(Integer.parseInt(getter)))
									System.out.println(TemplateStrings.fieldUpdated);
							}
						}
					}
				} else if (getter != null && getter.charAt(0) == '5') {
					getter = getEditOptionUtil();
					if (getter.length() > 0) {
						if (getter.charAt(0) == '1') {
							if (AddOffice())
								System.out.println(TemplateStrings.fieldUpdated);
						} else if (getter.charAt(0) == '2') {
							getter = getOfficeID(contactID);
							if (getter.length() > 0) {
								if (AddOffice()) {
									Deletion.deleteOffice(Integer.parseInt(getter));
									System.out.println(TemplateStrings.fieldUpdated);
								}
							}
						} else if (getter.charAt(0) == '3') {
							getter = getOfficeID(contactID);
							if (getter.length() > 0) {
								if (Deletion.deleteOffice(Integer.parseInt(getter)))
									System.out.println(TemplateStrings.fieldUpdated);
							}
						}
					}
				} else if (getter != null && getter.charAt(0) == '6') {
					getter = getEditOptionUtil();
					if (getter.length() > 0) {
						if (getter.charAt(0) == '1') {
							if (AddEmail())
								System.out.println(TemplateStrings.fieldUpdated);
						} else if (getter.charAt(0) == '2') {
							getter = getEmailID(contactID);
							if (getter.length() > 0) {
								if (AddEmail()) {
									Deletion.deleteEmail(Integer.parseInt(getter));
									System.out.println(TemplateStrings.fieldUpdated);
								}
							}
						} else if (getter.charAt(0) == '3') {
							getter = getEmailID(contactID);
							if (getter.length() > 0) {
								if (Deletion.deleteEmail(Integer.parseInt(getter)))
									System.out.println(TemplateStrings.fieldUpdated);
							}
						}
					}
				}
				getter = getEditOption();
			}
		}
		return true;
	}

	public static String getContactID() {
		getter = null;
		while (getter == null || !AppValidator.validateNum(getter) || (AppValidator.validateNum(getter)
				&& getter.length() > 0 && !Check.hasContactID(Integer.parseInt(getter)))) {
			SortByFirstName.Sort();
			System.out.print(TemplateStrings.askContactID);
			getter = scan.nextLine();
		}
		return getter;
	}

	public static String getMobileID(int contactID) {
		getter = null;
		while (getter == null || !AppValidator.validateNum(getter) || (AppValidator.validateNum(getter)
				&& getter.length() > 0 && !Check.hasMobileIDForContactID(contactID, Integer.parseInt(getter)))) {
			View.ShowMobileNumberWithContactID(contactID);
			System.out.print(TemplateStrings.askFieldID);
			getter = scan.nextLine();
		}
		return getter;
	}

	public static String getHomeID(int contactID) {
		getter = null;
		while (getter == null || !AppValidator.validateNum(getter) || (AppValidator.validateNum(getter)
				&& getter.length() > 0 && !Check.hasHomeIDForContactID(contactID, Integer.parseInt(getter)))) {
			View.ShowHomeNumberWithContactID(contactID);
			System.out.print(TemplateStrings.askFieldID);
			getter = scan.nextLine();
		}
		return getter;
	}

	public static String getOfficeID(int contactID) {
		getter = null;
		while (getter == null || !AppValidator.validateNum(getter) || (AppValidator.validateNum(getter)
				&& getter.length() > 0 && !Check.hasOfficeIDForContactID(contactID, Integer.parseInt(getter)))) {
			View.ShowOfficeNumberWithContactID(contactID);
			System.out.print(TemplateStrings.askFieldID);
			getter = scan.nextLine();
		}
		return getter;
	}
	
	public static String getEmailID(int contactID) {
		getter = null;
		while (getter == null || !AppValidator.validateNum(getter) || (AppValidator.validateNum(getter)
				&& getter.length() > 0 && !Check.hasEmailIDForContactID(contactID, Integer.parseInt(getter)))) {
			View.ShowEmailWithContactID(contactID);
			System.out.print(TemplateStrings.askFieldID);
			getter = scan.nextLine();
		}
		return getter;
	}
	
	public static String getEditOption() {
		getter = null;
		while (getter == null || !AppValidator.validateEditOption(getter) && getter.compareTo("") != 0) {
			UserOptions.showEditOptions();
			System.out.print(TemplateStrings.askEditOption);
			getter = scan.nextLine();
		}
		return getter;
	}

	public static String getEditOptionUtil() {
		getter = null;
		while (getter == null || !AppValidator.validateEditOptionUtil(getter) && getter.compareTo("") != 0) {
			UserOptions.showEditOptionsUtil();
			System.out.print(TemplateStrings.askEditOption);
			getter = scan.nextLine();
		}
		return getter;
	}

	public static boolean AddMobile() {
		Mobile mobile = new Mobile();
		Mobile.getMobileNumber(mobile, 'm');
		return AddContact.AddMobileNumber(contactID, mobile.mobile, mobile.countryCode);
	}

	public static boolean AddHome() {
		Landline landlineNum = new Landline();
		Landline.getLandlineNumber(landlineNum, 'h');
		return AddContact.AddHomeNumber(contactID, landlineNum.landline, landlineNum.areaCode);
	}

	public static boolean AddOffice() {
		Office office = new Office();
		Office.getOfficeNumber(office);
		return AddContact.AddOfficeLandlineNumber(contactID, office.officeLandline.landline,
				office.officeLandline.areaCode, office.officeExt_L)
				&& AddContact.AddOfficeMobileNumber(contactID, office.officeMobile.mobile,
						office.officeMobile.countryCode, office.officeExt_M);
	}

	public static boolean AddEmail() {
		Email email = new Email();
		Email.getEmailID(email);
		return AddContact.AddEmail(contactID, email.email);
	}

}
