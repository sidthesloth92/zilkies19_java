package io.ztech.contacts.beans;

import java.util.Comparator;

public class LastNameCompare implements Comparator<ContactDetails>{

	@Override
	public int compare(ContactDetails ob1, ContactDetails ob2) {
		return ob1.getLastName().compareTo(ob2.getLastName());
	}

}
