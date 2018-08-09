package com.zilker.application;

import java.util.Scanner;

import com.zilker.application.constants.Constants;
import com.zilker.application.service.ContactCreation;
import com.zilker.application.service.ContactDisplay;
import com.zilker.application.service.ContactManipulation;

public class MenuInterface {
	static ContactDisplay contactDisplay = new ContactDisplay();
	static ContactCreation contactCreation = new ContactCreation();
	static ContactManipulation contactManipulation = new ContactManipulation();
	static Constants constant = new Constants();
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		boolean displayMenu = true;
		while(displayMenu) {
			constant.displayMenu();
			int option = in.nextInt();
			switch(option) {
				case 1:
					contactCreation.creatingContact();
					break;
				case 2:
					contactManipulation.updateContact();
					break;
				case 3:
					contactDisplay.sortByFirstName();
					break;
				case 4:
					contactDisplay.sortByLastName();
					break;
				case 5:
					displayMenu = false;
				default: 
					break;
			}
		}
		in.close();
	}
}
