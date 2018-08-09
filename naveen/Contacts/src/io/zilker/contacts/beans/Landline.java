package io.zilker.contacts.beans;

import java.util.*;
import io.zilker.contacts.constants.*;

public class Landline {

	static Scanner scan = new Scanner(System.in);
	public ArrayList<String> landline = new ArrayList<String>();
	public ArrayList<String> areaCode = new ArrayList<String>();
	static char option;
	static String getter;
	static ArrayList<String> extensionCode;
	
	private static void setLandlineNumber(Landline landlineNum, String landlineNumber) {
		landlineNum.landline.add(landlineNumber);
	}

	private static void setAreaCode(Landline landlineNum, String areaCode) {
		landlineNum.areaCode.add(areaCode);
	}

	public static ArrayList<String> getLandlineNumber(Landline landlineNum, char type) {
		extensionCode = new ArrayList<String>();
		option = 'y';
		while (option == 'y') {
			getter = null;
			while (!Validator.validateNum(getter)) {
				if (type == 'h')
					System.out.print(TemplateStrings.askHomeNumber);
				else
					System.out.print(TemplateStrings.askOfficeNumber);
				getter = scan.nextLine();
			}
			if (getter.compareTo("") != 0) {
				setLandlineNumber(landlineNum, getter);
				getter = null;
				while (!Validator.validateCode(getter)) {
					System.out.print(TemplateStrings.askAreaCode);
					getter = scan.nextLine();
				}
				setAreaCode(landlineNum, getter);
				if(type == 'o')
					extensionCode.add(getExtension());
				option = 'i';
			}
			option = (option != 'i') ? 'n' : 'i';
			while (option == 'i') {
				if (type == 'h')
					System.out.print(TemplateStrings.askAnotherHomeNumber);
				else
					System.out.print(TemplateStrings.askAnotherOfficeLandline);
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
