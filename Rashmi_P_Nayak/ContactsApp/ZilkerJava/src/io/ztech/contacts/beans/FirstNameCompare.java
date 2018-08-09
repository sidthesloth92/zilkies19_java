package io.ztech.contacts.beans;

import java.util.Comparator;

public class FirstNameCompare implements Comparator<ContactDetails>{

	@Override
	public int compare(ContactDetails ob1, ContactDetails ob2) {
		// TODO Auto-generated method stub
		return ob1.getFirstName().compareTo(ob2.getFirstName());
	}

}
