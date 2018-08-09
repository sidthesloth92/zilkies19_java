package io.zilker.contacts.services;

import io.zilker.contacts.dao.*;

public class SortByLastName {

	public static void Sort() {
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%5s%16s%16s%8s%14s%8s%14s%8s%14s%8s%14s%24s\n", "ID", "First Name", "Last Name",
				"C-Code", "Mobile", "A-Code", "Home", "A-Code", "Office-L", "C-Code", "Office-M", "E-Mail");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		View.fillViewDetails('l');
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}
	
}
