package io.ztech.jkingsley.contactsapp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.jkingsley.contactsapp.beans.Contact;
import io.ztech.jkingsley.contactsapp.beans.Email;
import io.ztech.jkingsley.contactsapp.beans.HomeNumber;
import io.ztech.jkingsley.contactsapp.beans.MobileNumber;
import io.ztech.jkingsley.contactsapp.beans.OfficeNumber;
import io.ztech.jkingsley.contactsapp.beans.PhoneNumber;
import io.ztech.jkingsley.contactsapp.beans.User;
import io.ztech.jkingsley.contactsapp.constants.Strings;
import io.ztech.jkingsley.contactsapp.constants.ValidationLibrary;
import io.ztech.jkingsley.contactsapp.dao.DBLibrary;

public final class InputHandler implements KeyListener{

	private static final Logger LOGGER = Logger.getLogger(UIClass.class.getName());

	public static enum Update{
		firstName,
		lastName,
		phoneNum,
		emailID
	}
	
	public static Contact inputForAddContact() {

		Scanner scanner = new Scanner(System.in);
		Contact contact = new Contact();

		System.out.println("Enter First Name:");
		String firstName = scanner.nextLine();
		contact.user.setFirstName(firstName);

		System.out.println("Enter Last Name:");
		String lastName = scanner.nextLine();
		contact.user.setLastName(lastName);

		System.out.println("Enter Mobile Numbers(Enter -1 to skip):");
		Long mobileNumber = -1L;
		do {
			mobileNumber = scanner.nextLong();
			if (ValidationLibrary.isValidMobileNumber(mobileNumber)) {
				MobileNumber mobile = new MobileNumber();
				mobile.putNumber(mobileNumber);
				contact.mobileNumbers.add(mobile);
			} else if (mobileNumber != -1) {
				System.out.println("Not a valid mobile number");
			}
		} while (mobileNumber != -1);

		System.out.println("Enter Office Numbers(Enter -1 to skip):");
		Long officeNumber = -1L;
		do {
			officeNumber = scanner.nextLong();
			if (ValidationLibrary.isValidMobileNumber(officeNumber)) {
				OfficeNumber officeObj = new OfficeNumber();
				officeObj.putNumber(officeNumber);
				contact.officeNumbers.add(officeObj);
			} else if (officeNumber != -1) {
				System.out.println("Not a valid office number");
			}
		} while (officeNumber != -1);

		System.out.println("Enter Home Numbers(Enter -1 to skip):");
		Long homeNumber = -1L;
		do {
			homeNumber = scanner.nextLong();
			if (ValidationLibrary.isValidMobileNumber(homeNumber)) {
				HomeNumber homeObj = new HomeNumber();
				homeObj.putNumber(homeNumber);
				contact.homeNumbers.add(homeObj);
			} else if (homeNumber != -1) {
				System.out.println("Not a valid home number");
			}
		} while (homeNumber != -1);

		scanner.nextLine();

		System.out.println("Enter Email Ids(Enter -1 to skip):");
		String email = "";
		do {
			email = scanner.nextLine();
			if (ValidationLibrary.isValidEmail(email)) {
				Email emailObj = new Email();
				emailObj.putAddress(email);
				contact.emails.add(emailObj);
			} else if (!email.equals("-1")) {
				System.out.println("Not a valid email Id");
			}
		} while (!email.equals("-1"));

		scanner.close();
		return contact;
	}

	public static Long inputIdForUpdateContact() {
		ArrayList<User> users = DBLibrary.listUsers();
		for (int i = 0; i < users.size(); i++) {
			System.out.println(i + 1 + ": " + users.get(i).toString());
		}
		System.out.println("Enter User number to update:");
		Scanner sc = new Scanner(System.in);

		int n = 0;
		
		do {
			n = sc.nextInt();
			sc.nextLine();
		}while(n<=0 && n > users.size());
		return users.get(n-1).getId();
	}
	
	public static Long inputIdForDisplayContact() {
		ArrayList<User> users = DBLibrary.listUsers();
		for (int i = 0; i < users.size(); i++) {
			System.out.println(i + 1 + ": " + users.get(i).toString());
		}
		System.out.println("Enter User number to display(1 - " + users.size() + ") :");
		Scanner sc = new Scanner(System.in);
		
		int n = 0;
		
		do {
			n = sc.nextInt();
			sc.nextLine();
		}while(n<=0 && n > users.size());
		return users.get(n-1).getId();
	}

	public static int inputTypeOfUpdate() {
		System.out.println("What do you want to update?");
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		System.out.println("3. Phone Number");
		System.out.println("4. Email ID");

		Scanner sc = new Scanner(System.in);
		int n = 0;
		do {
			n = sc.nextInt();
			sc.nextLine();
		} while (n < 1 && n > 4);

		return n;
	}

	public static String inputNewFirstName() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter new first name:");
		String firstName = sc.next();
		sc.nextLine();
		return firstName;
	}

	public static String inputNewLastName() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter new last name:");
		String lastName = sc.next();
		sc.nextLine();
		return lastName;
	}

	public static int selectNumberToUpdate(int listSize) {
		// TODO Auto-generated method stub
		int n = 0;
		Scanner sc = new Scanner(System.in);
		while(n<=0 || n>listSize) {
			System.out.println("Enter option to update(1 - "+ listSize +"):");
			n = sc.nextInt();
			sc.nextLine();
			if(n<=0 || n>listSize) {
				System.out.println("Enter a valid option");
			}
		}
		return n-1;
	}

	public static Long inputNewPhoneNumber(PhoneNumber phoneNumber) {
		// TODO Auto-generated method stub
		Long newPhoneNumber = 0L;
		Scanner sc = new Scanner(System.in);
		switch(phoneNumber.getPhoneType()) {
		case Strings.PHONE_OFFICE_TYPE:
			
			do {
				System.out.println("Enter new office number:");
				newPhoneNumber = sc.nextLong();
				if(!ValidationLibrary.isValidOfficeNumber(newPhoneNumber)) {
					System.out.println("Enter a valid office number");
				}
			}while(!ValidationLibrary.isValidOfficeNumber(newPhoneNumber));
			break;
		case Strings.PHONE_HOME_TYPE:
			
			do {
				System.out.println("Enter new home number:");
				newPhoneNumber = sc.nextLong();
				if(!ValidationLibrary.isValidHomeNumber(newPhoneNumber)) {
					System.out.println("Enter a valid home number");
				}
			}while(!ValidationLibrary.isValidHomeNumber(newPhoneNumber));
			break;
		case Strings.PHONE_MOBILE_TYPE:
			do {
				System.out.println("Enter new mobile number:");
				newPhoneNumber = sc.nextLong();
				if(!ValidationLibrary.isValidMobileNumber(newPhoneNumber)) {
					System.out.println("Enter a valid mobile number");
				}
			}while(!ValidationLibrary.isValidMobileNumber(newPhoneNumber));
			break;
		}
		
		return newPhoneNumber;
	}

	public static String inputNewEmail(Email email) {
		// TODO Auto-generated method stub
		String newEmail = "";
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter new Email Id:");
			newEmail = sc.nextLine();
			if(!ValidationLibrary.isValidEmail(newEmail)) {
				System.out.println("Enter a valid Email Id");
			}
		}while(!ValidationLibrary.isValidEmail(newEmail));
		
		return newEmail;
	}
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 27) {
			System.exit(0);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}