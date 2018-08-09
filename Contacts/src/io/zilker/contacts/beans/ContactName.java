package io.zilker.contacts.beans;

import java.util.Scanner;

public class ContactName {

	static Scanner scan = new Scanner(System.in);
	public String firstName = null, lastName = null;

	public static void getFirstName(ContactName name) {
		while (!Validator.validateName(name.firstName)) {
			System.out.print("| Enter the First Name :  ");
			name.firstName = scan.nextLine();
		}
	}

	public static void getLastName(ContactName name) {
		while (!Validator.validateName(name.lastName)) {
			System.out.print("| Enter the Last Name :  ");
			name.lastName = scan.nextLine();
		}
	}
	
}
