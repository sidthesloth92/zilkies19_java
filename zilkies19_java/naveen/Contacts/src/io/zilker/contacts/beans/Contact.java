package io.zilker.contacts.beans;

public class Contact {
	
	public int id = -1;
	public ContactName name = new ContactName();
	public Mobile mobile = new Mobile();
	public Landline home = new Landline();
	public Office office = new Office();
	public Email email = new Email();
	
	public void getValues() {
		ContactName.getFirstName(name);
		ContactName.getLastName(name);
		Mobile.getMobileNumber(mobile, 'm');
		Landline.getLandlineNumber(home, 'h');
		Office.getOfficeNumber(office);
		Email.getEmailID(email);
	}	
}
