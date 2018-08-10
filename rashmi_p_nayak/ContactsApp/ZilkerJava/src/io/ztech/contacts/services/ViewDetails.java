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
import io.ztech.contacts.dao.DBView;

//view contact and contact details
public class ViewDetails {
	static Scanner in = new Scanner(System.in);
	static DisplayConstants d = new DisplayConstants();
	static DBView dbv = new DBView();
	static Logger LOGGER = Logger.getLogger(ViewDetails.class.getName());
	
	public static void viewDetails() {
		try {
			int choice;
			Contacts ct;
			LOGGER.info(d.SORT_MSG);
			choice = in.nextInt();
			ct = dbv.viewContactDetails(choice);
			LOGGER.info(d.CONTACT_HEAD);
			for (ContactDetails c : ct.contact) {
				System.out.printf("%-4d  %-10s  %-10s\n",c.getcId(),c.getFirstName(),c.getLastName());

			}
			LOGGER.info(d.ENTER_CID);
			choice = in.nextInt();

			ct = dbv.viewMobileDetails(choice);
			if (!ct.mobile.isEmpty()) {
				LOGGER.info(d.MOB_HEAD);
				for (MobileDetails m : ct.mobile) {
					System.out.println(m.getCountryCode() + " " + m.getNumber());
				}
			}

			ct = dbv.viewHomeDetails(choice);
			if (!ct.home.isEmpty()) {
				LOGGER.info(d.HOME_HEAD);
				for (HomeDetails h : ct.home) {
					System.out.println(h.getCountryCode() + " " + h.getAreaCode() + " " + h.getNumber());
				}
			}

			ct = dbv.viewOfficeDetails(choice);
			if (!ct.office.isEmpty()) {
				LOGGER.info(d.OFF_HEAD);
				for (OfficeDetails o : ct.office) {
					System.out.println(
							o.getExtn() + " " + o.getCountryCode() + " " + o.getAreaCode() + " " + o.getNumber());
				}
			}

			ct = dbv.viewEmailDetails(choice);
			if (!ct.email.isEmpty()) {
				LOGGER.info(d.EMAIL_HEAD);
				for (EmailDetails e : ct.email) {
					System.out.println(e.getEmailId());
				}
			}

			if (ct.email.isEmpty() && ct.home.isEmpty() && ct.mobile.isEmpty() && ct.office.isEmpty())
				LOGGER.info(d.EMPTY_RESULT);

		} catch (InputMismatchException e) {
			in.nextLine();
			LOGGER.info(d.INVALID_CHOICE);
			viewDetails();
			return;
		}

	}
}