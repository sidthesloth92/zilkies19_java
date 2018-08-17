package com.zilker.application.beans;

import java.util.ArrayList;

public class Contact {
	public Email email;
	public HomeNumber home;
	public MobileNumber mobile;
	
	public ArrayList<Email> emailArrayList;
	
	public Name name;
	public OfficeNumber office;
	public Contact() {
		name = new Name();
		email = new Email();
		home = new HomeNumber();
		mobile = new MobileNumber();
		office = new OfficeNumber();
		emailArrayList = new ArrayList<Email>();
	}
}
