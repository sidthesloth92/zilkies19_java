package io.zilker.contacts.beans;

import java.util.*;
import io.zilker.contacts.constants.*;

public class Email {

	static Scanner scan = new Scanner(System.in);
	public ArrayList<String> email = new ArrayList<String>();
	static char option;
	static String getter;
	
	private static void setEmailID(Email emailID, String mailID) {
		emailID.email.add(mailID);
	}
	
	public static void getEmailID(Email email) {
		option = 'y';
		while (option == 'y') {
			getter = null;
			while (!Validator.validateEmail(getter)) {
				System.out.print(TemplateStrings.askEmailID);
				getter = scan.nextLine();
			}
			option = 'i';
			if (getter.compareTo("") != 0) {
				setEmailID(email, getter);
				while (option == 'i') {
					System.out.print(TemplateStrings.askAnotherEmailID);
					getter = scan.nextLine();
					option = Validator.validateAnotherOption(getter);
				}
			}
		}
	}
	
}
