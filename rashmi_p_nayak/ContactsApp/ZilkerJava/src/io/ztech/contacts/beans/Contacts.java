package io.ztech.contacts.beans;

import java.util.ArrayList;

//wrapper class to store return resultset of queries
public class Contacts {
	public ArrayList<ContactDetails> contact = new ArrayList<ContactDetails>();
	public ArrayList<MobileDetails> mobile = new ArrayList<MobileDetails>();
	public ArrayList<OfficeDetails> office = new ArrayList<OfficeDetails>();
	public ArrayList<HomeDetails> home = new ArrayList<HomeDetails>();
	public ArrayList<EmailDetails> email = new ArrayList<EmailDetails>();
}
