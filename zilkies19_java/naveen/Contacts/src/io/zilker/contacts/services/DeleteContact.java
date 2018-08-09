package io.zilker.contacts.services;

import java.util.*;
import io.zilker.contacts.*;
import io.zilker.contacts.constants.ErrorCodes;
import io.zilker.contacts.constants.TemplateStrings;
import io.zilker.contacts.dao.*;

public class DeleteContact {

	static Scanner scan = new Scanner(System.in);

	public static boolean DeleteFullContact() {
		int contactID = -1;
		String getter;
		do {
			getter = null;
			while (!AppValidator.validateNum(getter)) {
				SortByFirstName.Sort();
				System.out.print(TemplateStrings.askContactID);
				getter = scan.nextLine();
			}
			if (getter.compareTo("") != 0)
				contactID = Integer.parseInt(getter);
		} while (getter.compareTo("") != 0 && !Check.hasContactID(contactID));
		if (getter.compareTo("") != 0) {
			if (Deletion.deleteContact(contactID)) {
				System.out.println(TemplateStrings.contactDeleted);
				return true;
			} else {
				System.out.println(ErrorCodes.dbErr);
			}
		}
		return true;
	}

}
