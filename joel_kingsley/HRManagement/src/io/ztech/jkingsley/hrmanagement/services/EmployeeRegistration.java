package io.ztech.jkingsley.hrmanagement.services;

import io.ztech.jkingsley.hrmanagement.beans.objects.Profile;
import io.ztech.jkingsley.hrmanagement.delegates.AddEmployeeDelegate;
import io.ztech.jkingsley.hrmanagement.utils.PersistenceException;

public class EmployeeRegistration {
	
	AddEmployeeDelegate addEmployeeDelegate = new AddEmployeeDelegate();
	
	public boolean addEmployee(Profile profile) {
		try {
			return addEmployeeDelegate.addProfile(profile);
		} catch (PersistenceException e) {
			return false;
		}
	}
	
	
	
}
