package io.ztech.contactapp;

import io.ztech.contactapp.services.ContactManager;

//=========================================================================================================================================
//START CONTACT APP CLASS - ENTRY POINT INTO CONTACTS APP
//=========================================================================================================================================
public class StartContactApp {
	public static void main(String[] args) {
		ContactManager contactManager = new ContactManager();
		contactManager.showMenu();
	}
}
