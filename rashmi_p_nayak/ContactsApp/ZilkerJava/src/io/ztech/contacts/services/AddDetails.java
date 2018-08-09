package io.ztech.contacts.services;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contacts.beans.ContactDetails;
import io.ztech.contacts.beans.EmailDetails;
import io.ztech.contacts.beans.HomeDetails;
import io.ztech.contacts.beans.MobileDetails;
import io.ztech.contacts.beans.NumberDetails;
import io.ztech.contacts.beans.OfficeDetails;
import io.ztech.contacts.constants.DisplayConstants;
import io.ztech.contacts.dao.DBInsertion;

//Add contact and contact details
public class AddDetails {
	static Scanner in = new Scanner(System.in);
	static Logger LOGGER = Logger.getLogger(AddDetails.class.getName());
	static DisplayConstants d = new DisplayConstants();
	static DBInsertion dbi = new DBInsertion();
	static NumberDetails nd = new NumberDetails();

	// add basic contact details
	public static void addContactDetails() {
		ContactDetails cd = new ContactDetails();
		LOGGER.info(d.FIRST_NAME);
		cd.setFirstName(in.next());
		LOGGER.info(d.LAST_NAME);
		cd.setLastName(in.next());
		dbi.insertIntoContactDetails(cd);
	}

	// add mobile number details
	public static void addMobileDetails(int cId) {
		MobileDetails md = new MobileDetails();
		LOGGER.info(d.MOB_NUM);
		String number = in.nextLine();
		String[] arg = number.split("\\s");
		if (arg.length != 2 || !nd.isValidatedNumber(arg[0]) || !nd.isValidatedNumber(arg[1])) {
			LOGGER.info(d.INVALID_FORMAT);
			addMobileDetails(cId);
			return;
		} else {
			md.setCountryCode(arg[0]);
			md.setNumber(arg[1]);
			md.setmId(dbi.getRecentMId(cId));
			md.setcId(cId);
			dbi.insertIntoMobileDetails(md);
		}

	}

	// add office number details
	public static void addOfficeDetils(int cId) {
		OfficeDetails od = new OfficeDetails();
		LOGGER.info(d.OFF_NUM);
		String number = in.nextLine();
		String[] arg = number.split("\\s");
		boolean isAllValidated = true;
		for (String s : arg) {
			isAllValidated &= nd.isValidatedNumber(s);
		}
		if (arg.length < 2 || !isAllValidated) {
			LOGGER.info(d.INVALID_FORMAT);
			addOfficeDetils(cId);
			return;
		} else {
			switch (arg.length) {
			case 2:
				od.setCountryCode(arg[0]);
				od.setNumber(arg[1]);
				break;
			case 3:
				od.setCountryCode(arg[0]);
				od.setAreaCode(arg[1]);
				od.setNumber(arg[2]);
				break;
			case 4:
				od.setExtn(arg[0]);
				od.setCountryCode(arg[1]);
				od.setAreaCode(arg[2]);
				od.setNumber(arg[3]);
				break;

			}
			od.setcId(cId);
			od.setoId(dbi.getRecentMId(cId));
			dbi.insertIntoOfficeDetails(od);
		}

	}

	// add home number details
	public static void addHomeDetails(int cId) {
		HomeDetails hd = new HomeDetails();
		LOGGER.info(d.HOME_NUM);
		String number = in.nextLine();
		String[] arg = number.split("\\s");
		if (arg.length != 3 || !nd.isValidatedNumber(arg[0]) || !nd.isValidatedNumber(arg[1])
				|| !nd.isValidatedNumber(arg[2])) {
			LOGGER.info(d.INVALID_FORMAT);
			addHomeDetails(cId);
			return;
		} else {
			hd.setCountryCode(arg[0]);
			hd.setAreaCode(arg[1]);
			hd.setNumber(arg[2]);
			hd.setcId(cId);
			hd.sethId(dbi.getRecentHId(cId));
			dbi.insertIntoHomeDetails(hd);
		}
	}

	// add email details
	public static void addEmailDetails(int cId) {
		EmailDetails ed = new EmailDetails();
		LOGGER.info(d.EMAIL_ID);
		String email = in.nextLine();
		if (!ed.isValidatedEmail(email)) {
			LOGGER.info(d.INVALID_FORMAT);
			addEmailDetails(cId);
			return;
		} else {
			ed.setcId(cId);
			ed.seteId(dbi.getRecentEId(cId));
			ed.setEmailId(email);
			dbi.insertIntoEmailDetails(ed);
		}

	}

	// add an entire contact
	public static void addEntireContact() {
		char choice;
		addContactDetails();
		int cId = dbi.getRecentCId();
		LOGGER.info(d.ADD_MOB_NUM);
		choice = in.next().charAt(0);
		in.nextLine();
		if (choice == 'y' || choice == 'Y')
			addMobileDetails(cId);
		LOGGER.info(d.ADD_OFF_NUM);
		choice = in.next().charAt(0);
		in.nextLine();
		if (choice == 'y' || choice == 'Y')
			addOfficeDetils(cId);
		LOGGER.info(d.ADD_HOME_NUM);
		choice = in.next().charAt(0);
		in.nextLine();
		if (choice == 'y' || choice == 'Y')
			addHomeDetails(cId);
		LOGGER.info(d.ADD_EMAIL);
		choice = in.next().charAt(0);
		in.nextLine();
		if (choice == 'y' || choice == 'Y')
			addEmailDetails(cId);
	}

	// add a particular attribute
	public static void addAttr() {
		try {
			SearchDetails.searchDetails();
			LOGGER.info(d.ENTER_CID);
			int cId = in.nextInt();
			LOGGER.info(d.ADD_CHOICE);
			int choice = in.nextInt();
			in.nextLine();
			switch (choice) {
			case 1:
				addOfficeDetils(cId);
				break;
			case 2:
				addMobileDetails(cId);
				break;
			case 3:
				addHomeDetails(cId);
				break;
			case 4:
				addEmailDetails(cId);
				break;
			default: {
				LOGGER.info(d.INVALID_CHOICE);
				addAttr();
				return;
			}
			}
		} catch (InputMismatchException e) {
			LOGGER.info(d.INVALID_FORMAT);
			in.nextLine();
			addAttr();
			return;

		}
	}
}
