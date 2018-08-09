package io.zilker.contacts;

import java.util.*;
import io.zilker.contacts.constants.*;
import io.zilker.contacts.services.*;

public class Application {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (true) {
			UserOptions.printOptions();
			String option = scan.nextLine();
			if (AppValidator.validateOption(option)) {
				if (option.charAt(0) == '1') {
					if(AddContact.AddCompleteContact())
						System.out.println(TemplateStrings.contactAdded);
					else
						System.out.println(ErrorCodes.appErr);
					System.out.println();
				} else if (option.charAt(0) == '2') {
					EditContact.Edit();
				} else if (option.charAt(0) == '3') {
					DeleteContact.DeleteFullContact();
				} else if (option.charAt(0) == '4') {
					SortByFirstName.Sort();
				} else if (option.charAt(0) == '5') {
					SortByLastName.Sort();
				} else if (option.charAt(0) == '6') {
					System.out.println(TemplateStrings.quitting);
					break;
				}
			} else {
				System.out.println(ErrorCodes.invalidOption);
			}
		}
	}
	
}
