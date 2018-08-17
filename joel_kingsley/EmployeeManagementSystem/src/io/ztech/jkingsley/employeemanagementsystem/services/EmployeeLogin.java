package io.ztech.jkingsley.employeemanagementsystem.services;

import java.math.BigInteger;
import java.util.logging.Logger;

import io.ztech.jkingsley.employeemanagement.ui.Application;
import io.ztech.jkingsley.employeemanagement.utils.PersistenceException;
import io.ztech.jkingsley.employeemanagementsystem.delegates.SearchEmployeeDelegate;

public class EmployeeLogin {
	
	private final Logger LOGGER = Logger.getLogger(Application.class.getName());
	
	SearchEmployeeDelegate searchEmployeeDelegate = new SearchEmployeeDelegate();
	
	public boolean isCorrect(BigInteger emp_id,String password) {
		
		try {
			String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
			String storedPassword = searchEmployeeDelegate.findPasswordOfEmployeeID(emp_id);
			//LOGGER.info(sha256hex);
			//LOGGER.info(storedPassword);
			if(storedPassword == null) {
				return false;
			} else if(sha256hex.equals(storedPassword)) {
				return true;
			} else {
				return false;
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}
}
