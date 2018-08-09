package io.ztech.contactapp.beans;

import java.util.ArrayList;

public class ContactObjectStorage {
	static ArrayList<Contact> contactList;

	public static void init() {
		contactList = new ArrayList<Contact>();
	}

	public static void addContact(Contact c) {
		contactList.add(c);
	}

	public static Contact retrieveContact(int con_id) {
		for (Contact c : contactList) {
			if (c.contact_id == con_id) {
				return c;
			}
		}
		return null;
	}
}
	/*

	public static void alterMobile(String countryCodeMobile, String mobileNumber, int con_id, int mob_id) {
		for (Contact c : contactList) {
			if (c.contact_id == con_id) {
				for (MobileNumber m : c.getPhone().mobileNumber) {
					if (m.getMobile_id() == mob_id) {
						m.setMobileNumber(countryCodeMobile, mobileNumber);
					}
				}
			}
		}

	}

	public static void alterOffice(String extNum, String officeNumber, int con_id, int office_id) {
		for (Contact c : contactList) {
			if (c.contact_id == con_id) {
				for (OfficeNumber o : c.getPhone().officeNumber) {
					if (o.getOffice_id() == office_id) {
						o.setOfficeNumber(extNum, officeNumber);
					}
				}
			}
		}

	}

	public static void alterHome(String countryCodeHome, String areaCode, String homeNumber, int con_id, int home_id) {
		for (Contact c : contactList) {
			if (c.contact_id == con_id) {
				for (HomeNumber h : c.getPhone().homeNumber) {
					if (h.getHome_id() == home_id) {
						h.setHomeNumber(areaCode, countryCodeHome, homeNumber);
					}
				}
			}
		}

	}

	public static void alterEmail(String mailID, int con_id, int email_id_num) {
		for (Contact c : contactList) {
			if (c.contact_id == con_id) {
				for (String s : c.getEmailAddress()) {
					
				}
			}
		}

	}

}
*/