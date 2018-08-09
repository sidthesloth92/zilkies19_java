package io.ztech.contacts.services;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contacts.constants.DisplayConstants;
import io.ztech.contacts.dao.DBDelete;

//delete contact and contact details
public class DeleteDetails {
	static DisplayConstants d = new DisplayConstants();
	static Scanner in = new Scanner(System.in);
	static DBDelete dbd = new DBDelete();
	static Logger LOGGER = Logger.getLogger(DeleteDetails.class.getName());
	//delete contact details
	public static void deleteDetails() {
		try {
		SearchDetails.searchDetails();
		LOGGER.info(d.ENTER_CID);
		int cId = in.nextInt();
		LOGGER.info(d.DEL_OPTION);
		int choice = in.nextInt();
		switch (choice) {
		case 1:
			deleteContact(cId);
			break;
		case 2:
			deleteAttr(cId);
			break;
		default: {
			LOGGER.info(d.INVALID_CHOICE);
			deleteDetails();
			return;
		}
		}}
		catch(InputMismatchException e) {
			LOGGER.info(d.INVALID_FORMAT);
			in.nextLine();
			deleteDetails();
			return;
		}
	}

	//delete entire contact
	public static void deleteContact(int cId) {
		dbd.deleteContact(cId);
	}

	//delete particular attribute
	public static void deleteAttr(int cId) {
		try {
			LOGGER.info(d.DEL_CHOICE);
		int choice = in.nextInt();
		LOGGER.info(d.ENTER_ID);
		int id = in.nextInt();
		switch (choice) {
		case 1:
			dbd.deleteMobile(cId, id);
			break;
		case 2:
			dbd.deleteHome(cId, id);
			break;
		case 3:
			dbd.deleteOffice(cId, id);
			break;
		case 4:
			dbd.deleteEmail(cId, id);
			break;
		default: {
			LOGGER.info(d.INVALID_CHOICE);
			deleteAttr(cId);
			return;
		}
		}}
		catch(InputMismatchException e) {
			LOGGER.info(d.INVALID_FORMAT);
			in.nextLine();
			deleteAttr(cId);
			return;
		}
	}
}
