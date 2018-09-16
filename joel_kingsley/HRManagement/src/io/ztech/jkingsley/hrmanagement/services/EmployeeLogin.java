package io.ztech.jkingsley.hrmanagement.services;

import java.math.BigInteger;
import java.util.logging.Logger;

import org.apache.commons.codec.digest.DigestUtils;

import io.ztech.jkingsley.hrmanagement.delegates.SearchEmployeeDelegate;
import io.ztech.jkingsley.hrmanagement.ui.Application;
import io.ztech.jkingsley.hrmanagement.utils.PersistenceException;

public class EmployeeLogin {
	
	private final Logger LOGGER = Logger.getLogger(Application.class.getName());
	
	SearchEmployeeDelegate searchEmployeeDelegate = new SearchEmployeeDelegate();
	
	public boolean isCorrect(BigInteger emp_id,String password) {
		
		try {
			String sha256hex = DigestUtils.sha256Hex(password);
			String storedPassword = searchEmployeeDelegate.findPasswordOfEmployeeID(emp_id);
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
