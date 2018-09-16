package io.ztech.jkingsley.hrmanagement.delegates;

import java.math.BigInteger;

import io.ztech.jkingsley.hrmanagement.beans.objects.EmergencyContact;
import io.ztech.jkingsley.hrmanagement.beans.objects.Experience;
import io.ztech.jkingsley.hrmanagement.beans.objects.Mail;
import io.ztech.jkingsley.hrmanagement.beans.objects.Phone;
import io.ztech.jkingsley.hrmanagement.beans.objects.Profile;
import io.ztech.jkingsley.hrmanagement.beans.objects.Project;
import io.ztech.jkingsley.hrmanagement.beans.objects.Skill;
import io.ztech.jkingsley.hrmanagement.dao.EmergencyContactDAO;
import io.ztech.jkingsley.hrmanagement.dao.EmployeeDAO;
import io.ztech.jkingsley.hrmanagement.dao.ExperienceDAO;
import io.ztech.jkingsley.hrmanagement.dao.MailDAO;
import io.ztech.jkingsley.hrmanagement.dao.PhoneDAO;
import io.ztech.jkingsley.hrmanagement.dao.ProjectDAO;
import io.ztech.jkingsley.hrmanagement.dao.SkillDAO;
import io.ztech.jkingsley.hrmanagement.utils.PersistenceException;

public class AddEmployeeDelegate {
	
	public boolean addProfile(Profile profile) throws PersistenceException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		BigInteger emp_id = employeeDAO.addEmployee(profile.employee);
		
		profile.phoneNumbers.forEach(phoneNumber -> phoneNumber.setEmp_id(emp_id));
		PhoneDAO phoneDAO = new PhoneDAO();
		phoneDAO.addPhoneNumbers(profile.phoneNumbers);
		
		profile.emergencyContacts.forEach(emergencyContact -> emergencyContact.setEmp_id(emp_id));
		EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO();
		emergencyContactDAO.addEmergencyContacts(profile.emergencyContacts);
		
		profile.mailAddresses.forEach(mailAddress -> mailAddress.setEmp_id(emp_id));
		MailDAO mailDAO = new MailDAO();
		mailDAO.addMailAddresses(profile.mailAddresses);
		
		profile.totalExperience.forEach(experience -> experience.setEmp_id(emp_id));
		//profile.totalExperience.forEach(experience -> LOGGER.info(experience.getSkill_id().toString()));
		ExperienceDAO experienceDAO = new ExperienceDAO();
		experienceDAO.addExperiences(profile.totalExperience);
		return true;
	}

	public boolean addPhoneNumber(Phone phone) throws PersistenceException {
		PhoneDAO phoneDAO = new PhoneDAO();
		return phoneDAO.addPhoneNumber(phone);
	}

	public boolean addEmergencyContact(EmergencyContact emergencyContact) throws PersistenceException {
		EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO();
		return emergencyContactDAO.addEmergencyContact(emergencyContact);
	}

	public boolean addMailAddress(Mail mail) throws PersistenceException {
		MailDAO mailDAO = new MailDAO();
		return mailDAO.addMailAddress(mail);
	}

	public boolean addExperience(Experience experience) throws PersistenceException {
		ExperienceDAO experienceDAO = new ExperienceDAO();
		return experienceDAO.addExperience(experience);
	}

	public boolean addSkill(Skill skill) throws PersistenceException {
		SkillDAO skillDAO = new SkillDAO();
		return skillDAO.addSkill(skill);
	}

	public boolean addProject(Project project) throws PersistenceException {
		ProjectDAO projectDAO = new ProjectDAO();
		return projectDAO.addProject(project);
	}


}
