package io.ztech.contact;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contact.bean.Details;
import io.ztech.contact.constants.ConstantDisplayStatements;
import io.ztech.contact.services.DaoImplementation;

public class ContactHome {
	public static final Logger logger = Logger.getLogger("ContactHome");
	public static String firstName, lastName, mobileNumber, officeNumber, homeNumber, email;
	public static boolean flag = true;
	public static Scanner input = new Scanner(System.in);

	public static void insertDetails() {
		Details temp = new Details();
		DaoImplementation db = new DaoImplementation();
		logger.info(ConstantDisplayStatements.ENTERFIRSTNAME);
		firstName = input.nextLine();
		firstName=firstName.substring(0, 1).toUpperCase()+firstName.substring(1);
		logger.info(ConstantDisplayStatements.ENTERLASTNAME);
		lastName = input.nextLine();
		lastName=lastName.substring(0, 1).toUpperCase()+lastName.substring(1);
		do {
			logger.info(ConstantDisplayStatements.ENTEREMAIL);
			email = input.nextLine();
			if (Validation.validateEmail(email)) {
				flag = false;
			}
		} while (flag);
		flag = true;
		do {
			logger.info(ConstantDisplayStatements.ENTERHOMENUMBER+ConstantDisplayStatements.END);
			homeNumber = input.nextLine();
			if(homeNumber.equals("-1"))
			{
				homeNumber=null;
				break;
			}
			if (Validation.validateHomeNumber(homeNumber)) {
				flag = false;
			}
		} while (flag);
		flag = true;
		do {
			logger.info(ConstantDisplayStatements.ENTEROFFICENUMBER+ConstantDisplayStatements.END);
			officeNumber = input.nextLine();
			if(officeNumber.equals("-1"))
			{
				officeNumber=null;
				break;
			}
			if (Validation.validateofficeNumber(officeNumber)) {
				flag = false;
			}
		} while (flag);

		flag = true;
		do {
			logger.info(ConstantDisplayStatements.ENTERMOBILENUMBER+ConstantDisplayStatements.END);
			mobileNumber = input.nextLine();
			if(mobileNumber.equals("-1"))
			{
				mobileNumber=null;
				break;
			}
			if (Validation.validatemobileNumber(mobileNumber)) {
				flag = false;
			}
		} while (flag);
		temp.setFirstName(firstName);
		temp.setLastName(lastName);
		temp.setHomeNumber(homeNumber);
		temp.setOfficeNumber(officeNumber);
		temp.setMobileNumber(mobileNumber);
		temp.setEmail(email);
		db.insertDetails(temp);
	}

	public static void update() {
		logger.info(ConstantDisplayStatements.ENTERFIRSTNAME);
		boolean flag1 = true;
		input.nextLine();
		String name = input.nextLine();
		name=name.substring(0, 1).toUpperCase()+name.substring(1);
		logger.info(ConstantDisplayStatements.UPDATENUMBEROTION);
		int option = input.nextInt();
		String number = "";
		if (option == 1) {
			input.nextLine();
			do {
			logger.info(ConstantDisplayStatements.ENTEROFFICENUMBER+ConstantDisplayStatements.END);
			number = input.nextLine();
			if(number.equals("-1")) {
				number=null;
				break;
			}
			if(Validation.validateofficeNumber(number)) {
				flag1=false;
			}
			}while(flag1);
			DaoImplementation.updateDetails(number, name, option);
		}
		if (option == 2) {
			input.nextLine();
			do {
			logger.info(ConstantDisplayStatements.ENTERHOMENUMBER+ConstantDisplayStatements.END);
			number = input.nextLine();
			if(number.equals("-1")) {
				number=null;
				flag1=false;
			}
			if(Validation.validateHomeNumber(number))
			{
				flag1=false;
			}
			}while(flag1);
			DaoImplementation.updateDetails(number, name, option);
		}
		if (option == 3) {
			input.nextLine();
			do {
			logger.info(ConstantDisplayStatements.ENTERMOBILENUMBER+ConstantDisplayStatements.END);
			number = input.nextLine();
			if(number.equals("-1")) {
				number=null;
				break;
			}
			if(Validation.validatemobileNumber(number))
			{
				flag1=false;
			}
			}while(flag1);
			DaoImplementation.updateDetails(number, name, option);
		}
		if(option==4) {
			input.nextLine();
			logger.info(ConstantDisplayStatements.ENTEREMAIL+ConstantDisplayStatements.END);
			email=input.nextLine();
			if(email.equals("-1")){
					email=null;
		}
			DaoImplementation.updateEmailDetails(email, name);
		}
	}

	public static void main(String[] args) {
		int choice;
		do {
			logger.info(ConstantDisplayStatements.MAINMENU);
			choice = input.nextInt();
			if (choice == 1) {
				input.nextLine();
				insertDetails();
			}

			if (choice == 2) {

				logger.info(ConstantDisplayStatements.SORTOPTION);
				int option = input.nextInt();
				DaoImplementation.selectDetails(option);
			}
			if (choice == 3) {

				logger.info(ConstantDisplayStatements.UPDATE);
				update();
			}
			if (choice == 4) {
				logger.info(ConstantDisplayStatements.DELETE);
				logger.info(ConstantDisplayStatements.ENTERFIRSTNAME);
				input.nextLine();
				String name = input.nextLine();
				name=name.substring(0, 1).toUpperCase()+name.substring(1);
				DaoImplementation.delete(name);
			}
		} while (choice != 5);
	}

}
