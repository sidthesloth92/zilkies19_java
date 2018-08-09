package io.ztech.contacts.services;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contacts.beans.ContactDetails;
import io.ztech.contacts.beans.Contacts;
import io.ztech.contacts.beans.EmailDetails;
import io.ztech.contacts.beans.HomeDetails;
import io.ztech.contacts.beans.MobileDetails;
import io.ztech.contacts.beans.OfficeDetails;
import io.ztech.contacts.constants.DisplayConstants;
import io.ztech.contacts.dao.DBSearch;
import io.ztech.contacts.dao.DBView;

//search contact and contact details
public class SearchDetails {
	static Scanner in = new Scanner(System.in);
	static DisplayConstants d = new DisplayConstants();
	static DBSearch dbs = new DBSearch();
	static DBView dbv = new DBView();
	static Logger LOGGER = Logger.getLogger(SearchDetails.class.getName());
	
	//search for particular contact details
	public static void searchDetails() {
		try {
		String str;
		LOGGER.info(d.SEARCH_PARAM);
		str = in.next();
		Contacts ct = dbs.searchfromContacts(str);
		
		if(!ct.contact.isEmpty()) {
			LOGGER.info(d.CONTACT_HEAD);
			for(ContactDetails c : ct.contact) {
				System.out.printf("%-4d  %-10s  %-10s\n",c.getcId(),c.getFirstName(),c.getLastName());
				
			}
		}
		
		ct = dbs.searchFromDb(str);

		
		if (!ct.mobile.isEmpty()) {
			LOGGER.info(d.MOB_RES_HEAD);
			while (!ct.mobile.isEmpty()) {
				ContactDetails c = ct.contact.remove(0);
				MobileDetails m = ct.mobile.remove(0);
				System.out.printf("%-4d  %-10s  %-10s  %-4d  +%s %s\n",c.getcId(),c.getFirstName(),c.getLastName(),m.getmId(),m.getCountryCode(),m.getNumber());
			}
		}

		if (!ct.office.isEmpty()) {
			LOGGER.info(d.OFF_RES_HEAD);
			while (!ct.office.isEmpty()) {
				ContactDetails c = ct.contact.remove(0);
				OfficeDetails o = ct.office.remove(0);
				System.out.printf("%-4d  %-10s  %-10s  %-4d  +%s %s %s, %s\n",c.getcId(),c.getFirstName(),c.getLastName(),o.getoId(),o.getCountryCode(),o.getAreaCode(),o.getNumber(),o.getExtn());
			}
		}

		if (!ct.home.isEmpty()) {
			LOGGER.info(d.HOME_RES_HEAD);
			while (!ct.home.isEmpty()) {
				ContactDetails c = ct.contact.remove(0);
				HomeDetails h = ct.home.remove(0);
				System.out.printf("%-4d  %-10s  %-10s  %-4d  +%s %s %s\n",c.getcId(),c.getFirstName(),c.getLastName(),h.gethId(),h.getCountryCode(),h.getAreaCode(),h.getNumber());
			}
		}

		if (!ct.email.isEmpty()) {
			LOGGER.info(d.EMAIL_RES_HEAD);
			while (!ct.email.isEmpty()) {
				ContactDetails c = ct.contact.remove(0);
				EmailDetails e = ct.email.remove(0);
				System.out.printf("%-4d  %-10s  %-10s  %-4d  %s\n",c.getcId(),c.getFirstName(),c.getLastName(),e.geteId(),e.getEmailId());
			}
		}}
		catch(InputMismatchException e) {
			LOGGER.info(d.INVALID_FORMAT);
			in.nextLine();
			searchDetails();
			return;
		}

	}
}
