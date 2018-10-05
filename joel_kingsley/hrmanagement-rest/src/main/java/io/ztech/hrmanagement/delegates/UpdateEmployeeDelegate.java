package io.ztech.hrmanagement.delegates;

import java.math.BigInteger;

import io.ztech.hrmanagement.beans.objects.EmergencyContact;
import io.ztech.hrmanagement.beans.objects.Mail;
import io.ztech.hrmanagement.beans.objects.Phone;
import io.ztech.hrmanagement.beans.types.EmployeeStatus;
import io.ztech.hrmanagement.dao.EmergencyContactDAO;
import io.ztech.hrmanagement.dao.EmployeeDAO;
import io.ztech.hrmanagement.dao.MailDAO;
import io.ztech.hrmanagement.dao.PhoneDAO;
import io.ztech.hrmanagement.utils.PersistenceException;

public class UpdateEmployeeDelegate {

	public boolean updatePhoneNumberOfID(Phone phone) throws PersistenceException {
		PhoneDAO phoneDAO = new PhoneDAO();
		return phoneDAO.updatePhoneNumberOfID(phone);
	}

	public boolean updateEmergencyContactOfID(EmergencyContact emergencyContact2) throws PersistenceException {
		EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO();
		return emergencyContactDAO.updateEmergencyContactOfID(emergencyContact2);
	}

	public boolean updateMailAddressOfID(Mail mail2) {
		MailDAO mailDAO = new MailDAO();
		return mailDAO.updateMailAddressOfID(mail2);
	}

	public boolean updateEmployeeStatus(BigInteger empID, EmployeeStatus employeeStatus) throws PersistenceException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		return employeeDAO.updateEmployeeStatus(empID,employeeStatus);
	}
	
}
