package io.zilker.contacts.services;

import io.zilker.contacts.beans.*;

public class PrintContact {

	public static void printFullContact(Contact contact) {
		if (contact.id != -1) {
			System.out.printf("%5s%16s%16s", contact.id, contact.name.firstName, contact.name.lastName);
			boolean flag = true;
			int counter = 0;
			while (flag) {
				flag = false;
				if (counter < contact.mobile.countryCode.size()) {
					System.out.printf("%8s%14s", contact.mobile.countryCode.get(counter),
							contact.mobile.mobile.get(counter));
					flag = true;
				} else
					System.out.printf("                      ");
				if (counter < contact.home.landline.size()) {
					System.out.printf("%8s%14s", contact.home.areaCode.get(counter),
							contact.home.landline.get(counter));
					flag = true;
				} else
					System.out.printf("                      ");
				if (counter < contact.office.officeLandline.areaCode.size()) {
					System.out.printf("%8s%14s", contact.office.officeLandline.areaCode.get(counter),
							contact.office.officeLandline.landline.get(counter));
					flag = true;
				} else
					System.out.printf("                      ");
				if (counter < contact.office.officeMobile.mobile.size()) {
					System.out.printf("%8s%14s", contact.office.officeMobile.countryCode.get(counter),
							contact.office.officeMobile.mobile.get(counter));
					flag = true;
				} else
					System.out.printf("                      ");
				if (counter < contact.email.email.size()) {
					System.out.printf("%24s", contact.email.email.get(counter));
					flag = true;
				} else
					System.out.printf("                        ");
				System.out.println();
				if (flag)
					System.out.printf("                                     ");
				counter++;
			}
		}
	}

}
