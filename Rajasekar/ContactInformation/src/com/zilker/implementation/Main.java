package com.zilker.implementation;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.*;
import com.zilker.beans.ContactDetails;
import com.zilker.constant.consolestrings;

public class Main {
	public static void main(String args[]) {
		final Logger logger = Logger.getLogger(Main.class.getName());
		Scanner in = new Scanner(System.in);
		int f = 0, choice = 0, checkcid = 0, cntid = 0, option = 0;
		char ff = ' ', check = ' ', var = ' ';
		String fname = "", lname = "", email = "", officeextension = "", officenumber = "", homearea = "", homecc = "",
				homenumber = "", mobilecc = "", mobilenumber = "";
		ContactImplementation ref = new ContactImplementation();
		Display disp = new Display();
		Insert ins = new Insert();
		Update upt = new Update();
		GetDetails gd = new GetDetails();
		do {
			logger.info(consolestrings.mainchoice);
			choice = in.nextInt();
			in.nextLine();
			ArrayList<String> al = new ArrayList<String>();
			switch (choice) {
			case 1:
				ContactDetails obj = new ContactDetails();
				logger.info(consolestrings.fname);
				fname = in.nextLine();
				logger.info(consolestrings.lname);
				lname = in.nextLine();
				logger.info(consolestrings.email);
				email = in.nextLine();
				boolean flag = Validation.validateemail(email);
				if (flag == false) {
					logger.warning(consolestrings.emailfail);
					break;
				}
				obj.setFname(fname);
				obj.setLname(lname);
				obj.setEmail(email);
				f = ins.insertcontact(obj);
				if (f == 0) {
					logger.warning(consolestrings.exists);
					break;
				}
				al.add(obj.getEmail());
				ins.insertemail(f, al);
				al.clear();
				logger.info(consolestrings.office);
				check = in.nextLine().charAt(0);
				if (check == 'y' || check == 'Y') {
					do {
						al = gd.getofficedetails(officeextension, officenumber, al);
						logger.info(consolestrings.onemore);
						var = in.nextLine().charAt(0);
					} while (var == 'y' || var == 'Y');
					ins.insertoffice(f, al);
					al.clear();
				}
				logger.info(consolestrings.home);
				check = in.nextLine().charAt(0);
				if (check == 'y' || check == 'Y') {
					do {
						al = gd.gethomedetails(homearea, homecc, homenumber, al);
						logger.info(consolestrings.onemore);
						var = in.nextLine().charAt(0);
					} while (var == 'y' || var == 'Y');
					ins.inserthome(f, al);
					al.clear();
				}
				logger.info(consolestrings.mobile);
				check = in.nextLine().charAt(0);
				if (check == 'y' || check == 'Y') {
					do {
						al = gd.getmobiledetails(mobilecc, mobilenumber, al);
						logger.info(consolestrings.onemore);
						var = in.nextLine().charAt(0);
					} while (var == 'y' || var == 'Y');
					ins.insertmobile(f, al);
					al.clear();
				}
				logger.info(consolestrings.mailid);
				check = in.nextLine().charAt(0);
				if (check == 'y' || check == 'Y') {
					do {
						al = gd.getemaildetails(email, al);
						logger.info(consolestrings.mailid);
						var = in.nextLine().charAt(0);
					} while (var == 'y' || var == 'Y');
					ins.insertemail(f, al);
					al.clear();
				}
				break;
			case 2:
				logger.info(consolestrings.fname);
				String fn = in.nextLine();
				logger.info(consolestrings.lname);
				String ln = in.nextLine();
				checkcid = ref.showbase(fn, ln);
				if (checkcid == 0) {
					logger.warning(consolestrings.invalid);
					break;
				}
				logger.info(consolestrings.updatechoice);
				cntid = checkcid;
				option = in.nextInt();
				in.nextLine();
				al.clear();
				switch (option) {
				case 1:
					String coucode = "", mobilenum = "";
					f = disp.displaymobile(cntid);
					logger.info(consolestrings.option);
					choice = in.nextInt();
					if (choice == 1) {
						al = gd.getmobiledetails(coucode, mobilenum, al);
						ins.insertmobile(cntid, al);
						al.clear();
					}
					if (choice == 2) {
						if (f == 0) {
							logger.info(consolestrings.nothing);
							break;
						}
						logger.info(consolestrings.mid);
						int mobid = in.nextInt();
						al = gd.getmobiledetails(coucode, mobilenum, al);
						upt.updatemobile(al.get(0), al.get(1), mobid);
					}
					break;
				case 2:
					String exnum = "", officenum = "";
					f = disp.displayoffice(cntid);
					logger.info(consolestrings.option);
					choice = in.nextInt();
					in.nextLine();
					if (choice == 1) {
						al = gd.getofficedetails(exnum, officenum, al);
						ins.insertoffice(cntid, al);
						al.clear();
					}
					if (choice == 2) {
						if (f == 0) {
							logger.warning(consolestrings.nothing);
							break;
						}
						logger.info(consolestrings.oid);
						int oid = in.nextInt();
						in.nextLine();
						al = gd.getofficedetails(exnum, officenum, al);
						upt.updateoffice(al.get(0), al.get(1), oid);
					}
					break;
				case 3:
					String narea = "", nhome = "", ncc = "";
					f = disp.displayhome(cntid);
					logger.info(consolestrings.option);
					choice = in.nextInt();
					in.nextLine();
					if (choice == 1) {
						al = gd.gethomedetails(narea, nhome, ncc, al);
						ins.inserthome(cntid, al);
						al.clear();
					}
					if (choice == 2) {
						if (f == 0) {
							logger.warning(consolestrings.nothing);
							break;
						}
						logger.info(consolestrings.hid);
						int hid = in.nextInt();
						in.nextLine();
						al = gd.gethomedetails(narea, nhome, ncc, al);
						upt.updatehome(al.get(0), al.get(1), al.get(2), hid);
					}
					break;
				case 4:
					String nemail = "";
					f = disp.displayemail(cntid);
					logger.info(consolestrings.option);
					choice = in.nextInt();
					in.nextLine();
					if (choice == 1) {
						al = gd.getemaildetails(nemail, al);
						ins.insertemail(cntid, al);
						al.clear();
					}
					if (choice == 2) {
						if (f == 0) {
							logger.warning(consolestrings.nothing);
							break;
						}
						logger.info(consolestrings.eid);
						int eid = in.nextInt();
						in.nextLine();
						al = gd.getemaildetails(nemail, al);
						upt.updateemail(al.get(0), eid);
					}
					break;
				case 5:
					logger.info(consolestrings.email);
					String gmail = in.nextLine();
					logger.info(consolestrings.fname);
					String nfname = in.nextLine();
					upt.updatefname(gmail, nfname);
					break;
				case 6:
					logger.info(consolestrings.email);
					gmail = in.nextLine();
					logger.info(consolestrings.lname);
					String nlname = in.nextLine();
					upt.updatelname(gmail, nlname);
					break;
				}
				break;
			case 3:
				ref.sortfname();
				break;
			case 4:
				ref.sortlname();
				break;
			}

			logger.info(consolestrings.mainmenu);
			ff = in.next().charAt(0);

		} while (ff == 'y' || ff == 'Y');
		logger.info(consolestrings.bye);
	}
}
