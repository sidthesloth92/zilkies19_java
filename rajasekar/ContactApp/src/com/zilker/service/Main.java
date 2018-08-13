package com.zilker.service;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.*;
import com.zilker.beans.ContactDetails;
import com.zilker.constant.ConsoleStrings;
import com.zilker.dao.*;
public class Main {
	public static void main(String args[]) {
		final Logger logger = Logger.getLogger(Main.class.getName());
		Scanner in = new Scanner(System.in);
		int f = 0, choice = 0, checkcid = 0, cntid = 0, option = 0;
		char ff = ' ', check = ' ', var = ' ';
		String fname = "", lname = "", email = "", officeextension = "", officenumber = "", homearea = "", homecc = "",
				homenumber = "", mobilecc = "", mobilenumber = "";
		Sorting ref = new Sorting();
		Display disp = new Display();
		Insert ins = new Insert();
		Update upt = new Update();
		Fetch gd = new Fetch();
		do {
			logger.info(ConsoleStrings.MAINCHOICE);
			choice = in.nextInt();
			in.nextLine();
			ArrayList<String> al = new ArrayList<String>();
			switch (choice) {
			case 1:
				ContactDetails obj = new ContactDetails();
				logger.info(ConsoleStrings.FIRST_NAME);
				fname = in.nextLine();
				logger.info(ConsoleStrings.LASTNAME);
				lname = in.nextLine();
				logger.info(ConsoleStrings.EMAIL);
				email = in.nextLine();
				boolean flag = Validation.validateemail(email);
				if (flag == false) {
					logger.warning(ConsoleStrings.EMAIL_FAIL);
					break;
				}
				obj.setFname(fname);
				obj.setLname(lname);
				obj.setEmail(email);
				f = ins.insertContact(obj);
				if (f == 0) {
					logger.warning(ConsoleStrings.EXISTS);
					break;
				}
				al.add(obj.getEmail());
				ins.insertEmail(f, al);
				al.clear();
				logger.info(ConsoleStrings.OFFICE);
				check = in.nextLine().charAt(0);
				if (check == 'y' || check == 'Y') {
					do {
						al = gd.getOfficeDetails(officeextension, officenumber, al);
						logger.info(ConsoleStrings.ONEMORE);
						var = in.nextLine().charAt(0);
					} while (var == 'y' || var == 'Y');
					ins.insertOffice(f, al);
					al.clear();
				}
				logger.info(ConsoleStrings.HOME);
				check = in.nextLine().charAt(0);
				if (check == 'y' || check == 'Y') {
					do {
						al = gd.getHomeDetails(homearea, homecc, homenumber, al);
						logger.info(ConsoleStrings.ONEMORE);
						var = in.nextLine().charAt(0);
					} while (var == 'y' || var == 'Y');
					ins.insertHome(f, al);
					al.clear();
				}
				logger.info(ConsoleStrings.MOBILE);
				check = in.nextLine().charAt(0);
				if (check == 'y' || check == 'Y') {
					do {
						al = gd.getMobileDetails(mobilecc, mobilenumber, al);
						logger.info(ConsoleStrings.ONEMORE);
						var = in.nextLine().charAt(0);
					} while (var == 'y' || var == 'Y');
					ins.insertMobile(f, al);
					al.clear();
				}
				logger.info(ConsoleStrings.MAILID);
				check = in.nextLine().charAt(0);
				if (check == 'y' || check == 'Y') {
					do {
						al = gd.getEmailDetails(email, al);
						logger.info(ConsoleStrings.MAILID);
						var = in.nextLine().charAt(0);
					} while (var == 'y' || var == 'Y');
					ins.insertEmail(f, al);
					al.clear();
				}
				break;
			case 2:
				logger.info(ConsoleStrings.FIRST_NAME);
				String fn = in.nextLine();
				logger.info(ConsoleStrings.LASTNAME);
				String ln = in.nextLine();
				checkcid = ref.showBase(fn, ln);
				if (checkcid == 0) {
					logger.warning(ConsoleStrings.INVALID);
					break;
				}
				logger.info(ConsoleStrings.UPDATECHOICE);
				cntid = checkcid;
				option = in.nextInt();
				in.nextLine();
				al.clear();
				switch (option) {
				case 1:
					String coucode = "", mobilenum = "";
					f = disp.displayMobile(cntid);
					logger.info(ConsoleStrings.OPTION);
					choice = in.nextInt();
					if (choice == 1) {
						al = gd.getMobileDetails(coucode, mobilenum, al);
						ins.insertMobile(cntid, al);
						al.clear();
					}
					if (choice == 2) {
						if (f == 0) {
							logger.info(ConsoleStrings.NOTHING);
							break;
						}
						logger.info(ConsoleStrings.MOBILE_ID);
						int mobid = in.nextInt();
						al = gd.getMobileDetails(coucode, mobilenum, al);
						upt.updateMobile(al.get(0), al.get(1), mobid);
					}
					break;
				case 2:
					String exnum = "", officenum = "";
					f = disp.displayOffice(cntid);
					logger.info(ConsoleStrings.OPTION);
					choice = in.nextInt();
					in.nextLine();
					if (choice == 1) {
						al = gd.getOfficeDetails(exnum, officenum, al);
						ins.insertOffice(cntid, al);
						al.clear();
					}
					if (choice == 2) {
						if (f == 0) {
							logger.warning(ConsoleStrings.NOTHING);
							break;
						}
						logger.info(ConsoleStrings.OFFICE_ID);
						int oid = in.nextInt();
						in.nextLine();
						al = gd.getOfficeDetails(exnum, officenum, al);
						upt.updateOffice(al.get(0), al.get(1), oid);
					}
					break;
				case 3:
					String narea = "", nhome = "", ncc = "";
					f = disp.displayHome(cntid);
					logger.info(ConsoleStrings.OPTION);
					choice = in.nextInt();
					in.nextLine();
					if (choice == 1) {
						al = gd.getHomeDetails(narea, nhome, ncc, al);
						ins.insertHome(cntid, al);
						al.clear();
					}
					if (choice == 2) {
						if (f == 0) {
							logger.warning(ConsoleStrings.NOTHING);
							break;
						}
						logger.info(ConsoleStrings.HOME_ID);
						int hid = in.nextInt();
						in.nextLine();
						al = gd.getHomeDetails(narea, nhome, ncc, al);
						upt.updateHome(al.get(0), al.get(1), al.get(2), hid);
					}
					break;
				case 4:
					String nemail = "";
					f = disp.displayEmail(cntid);
					logger.info(ConsoleStrings.OPTION);
					choice = in.nextInt();
					in.nextLine();
					if (choice == 1) {
						al = gd.getEmailDetails(nemail, al);
						ins.insertEmail(cntid, al);
						al.clear();
					}
					if (choice == 2) {
						if (f == 0) {
							logger.warning(ConsoleStrings.NOTHING);
							break;
						}
						logger.info(ConsoleStrings.EMAIL_ID);
						int eid = in.nextInt();
						in.nextLine();
						al = gd.getEmailDetails(nemail, al);
						upt.updateEmail(al.get(0), eid);
					}
					break;
				case 5:
					logger.info(ConsoleStrings.EMAIL);
					String gmail = in.nextLine();
					logger.info(ConsoleStrings.FIRST_NAME);
					String nfname = in.nextLine();
					upt.updateFirstName(gmail, nfname);
					break;
				case 6:
					logger.info(ConsoleStrings.EMAIL);
					gmail = in.nextLine();
					logger.info(ConsoleStrings.LASTNAME);
					String nlname = in.nextLine();
					upt.updateLastName(gmail, nlname);
					break;
				}
				break;
			case 3:
				ref.sortFirstName();
				break;
			case 4:
				ref.sortLastName();
				break;
			}

			logger.info(ConsoleStrings.MAINMENU);
			ff = in.next().charAt(0);

		} while (ff == 'y' || ff == 'Y');
		logger.info(ConsoleStrings.BYE);
	}
}
