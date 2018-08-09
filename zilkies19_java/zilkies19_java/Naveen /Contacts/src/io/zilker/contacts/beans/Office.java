package io.zilker.contacts.beans;

import java.util.ArrayList;
import java.util.Scanner;
import io.zilker.contacts.constants.*;

public class Office {

	static Scanner scan = new Scanner(System.in);
	public ArrayList<String> officeExt_M = new ArrayList<String>();
	public ArrayList<String> officeExt_L = new ArrayList<String>();
	static char option;
	static String getter;
	public Mobile officeMobile = new Mobile();
	public Landline officeLandline = new Landline();

	public static void getOfficeNumber(Office office) {
		option = 'y';
		while (option == 'y') {
			getter = null;
			char type = 'i';
			while ((getter == null && type != 'l' && type != 'm') || type == 'i') {
				System.out.print(TemplateStrings.askOfficePhoneType);
				getter = scan.nextLine();
				type = Validator.officePhoneType(getter);
			}
			if (type == 'l') {
				office.officeExt_L = Landline.getLandlineNumber(office.officeLandline, 'o');
			} else if (type == 'm') {
				office.officeExt_M = Mobile.getMobileNumber(office.officeMobile, 'o');
			}
			getter = null;
			option = 'i';
			while (option == 'i' && type != 'n') {
				System.out.print(TemplateStrings.askAnotherOfficeNumber);
				getter = scan.nextLine();
				option = Validator.validateAnotherOption(getter);
			}
		}
	}

}
