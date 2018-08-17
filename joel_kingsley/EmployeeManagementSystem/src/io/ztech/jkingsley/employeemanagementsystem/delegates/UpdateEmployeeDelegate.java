package io.ztech.jkingsley.employeemanagementsystem.delegates;

import java.math.BigInteger;

import io.ztech.jkingsley.employeemanagement.utils.PersistenceException;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.EmergencyContact;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Mail;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Phone;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.EmployeeStatus;
import io.ztech.jkingsley.employeemanagementsystem.dao.EmergencyContactDAO;
import io.ztech.jkingsley.employeemanagementsystem.dao.EmployeeDAO;
import io.ztech.jkingsley.employeemanagementsystem.dao.MailDAO;
import io.ztech.jkingsley.employeemanagementsystem.dao.PhoneDAO;

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
