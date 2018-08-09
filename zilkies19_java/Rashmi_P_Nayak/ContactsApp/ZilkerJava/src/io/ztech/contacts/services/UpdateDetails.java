package io.ztech.contacts.services;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contacts.beans.EmailDetails;
import io.ztech.contacts.beans.HomeDetails;
import io.ztech.contacts.beans.MobileDetails;
import io.ztech.contacts.beans.NumberDetails;
import io.ztech.contacts.beans.OfficeDetails;
import io.ztech.contacts.constants.DisplayConstants;
import io.ztech.contacts.dao.DBUpdate;

//update contact and contact details
public class UpdateDetails {
	static Scanner in = new Scanner(System.in);
	static DBUpdate dbu = new DBUpdate();
	static DisplayConstants d = new DisplayConstants();
	static NumberDetails nd = new NumberDetails();
	static Logger LOGGER = Logger.getLogger(UpdateDetails.class.getName());

	//update details
	public static void updateDetails() {
		try {
			SearchDetails.searchDetails();
			LOGGER.info(d.ENTER_CID);
			int cId = in.nextInt();
			LOGGER.info(d.UPD_CHOICE);
			int choice = in.nextInt();
			switch (choice) {
			case 1:
				updateFirstName(cId);
				break;
			case 2:
				updateLastName(cId);
				break;
			case 3:
				updateOfficeNumber(cId);
				break;
			case 4:
				updateMobileNumber(cId);
				break;
			case 5:
				updateHomeNumber(cId);
				break;
			case 6:
				updateEmailId(cId);
				break;
			default: {
				LOGGER.info(d.INVALID_CHOICE);
				updateDetails();
				return;
			}

			}
		} catch (InputMismatchException e) {
			LOGGER.info(d.INVALID_FORMAT);
			in.nextLine();
			updateDetails();
			return;
		}
	}

	//update first name
	public static void updateFirstName(int cId) {
		LOGGER.info(d.FIRST_NAME);
		String name = in.next();
		dbu.updateFirstName(name, cId);
	}

	//update last name
	public static void updateLastName(int cId) {
		LOGGER.info(d.LAST_NAME);
		String name = in.next();
		dbu.updateLastName(name, cId);
	}

	//update mobile number
	public static void updateMobileNumber(int cId) {
		try {
			LOGGER.info(d.ENTER_ID);
			int id = in.nextInt();
			in.nextLine();
			MobileDetails m = new MobileDetails();
			LOGGER.info(d.MOB_NUM);
			String number = in.nextLine();
			String[] arg = number.split("\\s");
			if (arg.length != 2 || !nd.isValidatedNumber(arg[0]) || !nd.isValidatedNumber(arg[1])) {
				LOGGER.info(d.INVALID_FORMAT);
				updateMobileNumber(cId);
				return;
			} else {
				m.setCountryCode(arg[0]);
				m.setNumber(arg[1]);
				m.setmId(id);
				m.setcId(cId);
				dbu.updateMobile(m);
			}
		} catch (InputMismatchException e) {
			LOGGER.info(d.INVALID_FORMAT);
			in.nextLine();
			updateMobileNumber(cId);
			return;
		}
	}

	//update office number
	public static void updateOfficeNumber(int cId) {
		try {
			LOGGER.info(d.ENTER_ID);
		int id = in.nextInt();
		in.nextLine();
		OfficeDetails o = new OfficeDetails();
		LOGGER.info(d.OFF_NUM);
		String number = in.nextLine();
		String[] arg = number.split("\\s");
		boolean isAllValidated = true;
		for (String s : arg) {
			isAllValidated &= nd.isValidatedNumber(s);
		}
		if (arg.length < 2 || !isAllValidated) {
			LOGGER.info(d.INVALID_FORMAT);
			updateOfficeNumber(cId);
			return;
		} else {
			switch (arg.length) {
			case 2:
				o.setCountryCode(arg[0]);
				o.setNumber(arg[1]);
				break;
			case 3:
				o.setCountryCode(arg[0]);
				o.setAreaCode(arg[1]);
				o.setNumber(arg[2]);
				break;
			case 4:
				o.setExtn(arg[0]);
				o.setCountryCode(arg[1]);
				o.setAreaCode(arg[2]);
				o.setNumber(arg[3]);
				break;

			}
			o.setcId(cId);
			o.setoId(id);
			dbu.updateOffice(o);
		}}
		catch(InputMismatchException e) {
			LOGGER.info(d.INVALID_FORMAT);
			in.nextLine();
			updateOfficeNumber(cId);
			return;
		}
	}

	//update home number
	public static void updateHomeNumber(int cId) {
		try {
			LOGGER.info(d.ENTER_ID);
		int id = in.nextInt();
		in.nextLine();
		HomeDetails h = new HomeDetails();
		LOGGER.info(d.HOME_NUM);
		String number = in.nextLine();
		String[] arg = number.split("\\s");
		if (arg.length != 3 || !nd.isValidatedNumber(arg[0]) || !nd.isValidatedNumber(arg[1])
				|| !nd.isValidatedNumber(arg[2])) {
			LOGGER.info(d.INVALID_FORMAT);
			updateHomeNumber(cId);
			return;
		} else {
			h.setCountryCode(arg[0]);
			h.setAreaCode(arg[1]);
			h.setNumber(arg[2]);
			h.sethId(id);
			h.setcId(cId);
			dbu.updateHome(h);
		}}
		catch(InputMismatchException e) {
			LOGGER.info(d.INVALID_FORMAT);
			in.nextLine();
			updateHomeNumber(cId);
			return;
		}
	}

	//update email id
	public static void updateEmailId(int cId) {
		try {
			LOGGER.info(d.ENTER_ID);
		int id = in.nextInt();
		in.nextLine();
		EmailDetails e = new EmailDetails();
		LOGGER.info(d.EMAIL_ID);
		String email = in.next();
		if (!e.isValidatedEmail(email)) {
			LOGGER.info(d.INVALID_FORMAT);
			updateEmailId(cId);
			return;
		} else {
			e.seteId(id);
			e.setcId(cId);
			e.setEmailId(email);
			dbu.updateEmail(e);
		}}
		catch(InputMismatchException e) {
			LOGGER.info(d.INVALID_FORMAT);
			in.nextLine();
			updateEmailId(cId);
			return;
		}
	}
}
