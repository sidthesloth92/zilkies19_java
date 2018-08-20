package io.ztech.jkingsley.employeemanagementsystem.services;

import io.ztech.jkingsley.employeemanagement.utils.PersistenceException;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Profile;
import io.ztech.jkingsley.employeemanagementsystem.delegates.AddEmployeeDelegate;

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
