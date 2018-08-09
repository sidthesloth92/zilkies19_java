package io.zilker.contacts.beans;

import java.util.ArrayList;
import java.util.Scanner;
import io.zilker.contacts.constants.*;

public class Mobile {

	static Scanner scan = new Scanner(System.in);
	public ArrayList<String> mobile = new ArrayList<String>();
	public ArrayList<String> countryCode = new ArrayList<String>();
	static char option;
	static String getter;
	static ArrayList<String> extensionCode;
	
	private static void setMobileNumber(Mobile mobileNum, String mobileNumber) {
		mobileNum.mobile.add(mobileNumber);
	}

	private static void setCountryCode(Mobile mobileNum, String countryCode) {
		mobileNum.countryCode.add(countryCode);
	}

	public static ArrayList<String> getMobileNumber(Mobile mobileNum, char type) {
		extensionCode = new ArrayList<String>();
		option = 'y';
		while (option == 'y') {
			getter = null;
			while (!Validator.validateNum(getter)) {
				if (type == 'm')
					System.out.print(TemplateStrings.askMobileNumber);
				else
					System.out.print(TemplateStrings.askOfficeMobile);
				getter = scan.nextLine();
			}
			if (getter.compareTo("") != 0) {
				setMobileNumber(mobileNum, getter);
				getter = null;
				while (!Validator.validateCode(getter)) {
					System.out.print(TemplateStrings.askCountryCode);
					getter = scan.nextLine();
				}
				setCountryCode(mobileNum, getter);
				if(type == 'o')
					extensionCode.add(getExtension());
				option = 'i';
			}
			option = (option != 'i') ? 'n' : 'i';
			while (option == 'i') {
				if (type == 'm')
					System.out.print(TemplateStrings.askAnotherMobileNumber);
				else
					System.out.print(TemplateStrings.askAnotherOfficeMobile);
				getter = scan.nextLine();
				option = Validator.validateAnotherOption(getter);
			}
		}
		return extensionCode;
	}
	
	public static String getExtension() {
		getter = null;
		while (!Validator.validateExtension(getter)) {
			System.out.print(TemplateStrings.askExtensionNumber);
			getter = scan.nextLine();
		}
		return getter;
	}

}
