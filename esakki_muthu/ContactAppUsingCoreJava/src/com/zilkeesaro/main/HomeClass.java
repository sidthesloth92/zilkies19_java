package com.zilkeesaro.main;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilkeesaro.beans.Details;
import com.zilkeesaro.constants.Strings;
import com.zilkeesaro.dao.Queries;
import com.zilkeesaro.service.ScanInputs;

public class HomeClass {

	static Boolean cond = true;

	static Scanner in = new Scanner(System.in);

	//public static String first_name, last_name, mobile_number, e_mail, office, home;

	static Details details = new Details();
	
	static Queries queries = new Queries();
	
	final static Logger logger = Logger.getLogger(HomeClass.class.getName());

	public static Details addContact(Details details) {

		details.getFirst_name();

		details.getLast_name();

		details.getMobile_number();

		details.getOffice();

		details.getHome();

		details.getE_mail();

		return details;
	}

	public static void getDatas(Details details) throws SQLException {

		ScanInputs inputs = new ScanInputs();

		details.setFirst_name(inputs.getFirst_name());

		details.setLast_name(inputs.getLast_name());

		details.setMobile_number(inputs.getMobile_number());

		details.setHome(inputs.getHome());

		details.setOffice(inputs.getOffice());

		details.setE_mail(inputs.getE_mail());

		details = addContact(details);

		queries.addContact(details);

	}

	public static void ViewContact(Details details) throws SQLException {

		logger.info(Strings.VIEW_OPTIONS);

		int choice = in.nextInt();
		
		switch (choice) {
		case 1:
			queries.SortList(0);
			break;
		case 2: {
			queries.SortList(1);
			break;
		}
		case 3: {
			queries.SortList(2);
			break;
		}
		case 4: {
			queries.SortList(3);
			break;
		}
		default:
			break;
		}

	}
	
	static void UpdateContact() {

		logger.info(Strings.UPDATE_OPTIONS);
				
		int choice = in.nextInt();

		switch (choice) {
		case 1:
			queries.Update(0,details);
			break;
		case 2:
			queries.Update(1,details);
			break;
		case 3:
			queries.Update(2,details);
			break;
		case 4:
			queries.Update(3,details);
			break;
		case 5:
			queries.Update(4,details);
			break;
		default:
			break;
		}

	}


	public static void main(String args[]) throws SQLException {

		while (cond) {

			logger.info(Strings.LIST_OPTIONS);

			int choice = in.nextInt();

			switch (choice) {

			case 1: {

				getDatas(details);
				break;
			}
			case 2: {
				ViewContact(details);
				break;
			}
			case 3: {
				UpdateContact();
				break;
			}
			case 4: {
				 queries.Delete();
				break;
			}
			default:
				cond = false;
				break;

			}

		}

	}

}
