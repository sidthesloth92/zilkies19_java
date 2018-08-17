package io.ztech.jkingsley.employeemanagementsystem.delegates;

import java.math.BigInteger;
import java.util.ArrayList;

import io.ztech.jkingsley.employeemanagement.utils.PersistenceException;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Designation;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.EmergencyContact;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Experience;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Mail;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Phone;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Profile;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Skill;
import io.ztech.jkingsley.employeemanagementsystem.dao.AssignDAO;
import io.ztech.jkingsley.employeemanagementsystem.dao.DesignationDAO;
import io.ztech.jkingsley.employeemanagementsystem.dao.EmergencyContactDAO;
import io.ztech.jkingsley.employeemanagementsystem.dao.EmployeeDAO;
import io.ztech.jkingsley.employeemanagementsystem.dao.ExperienceDAO;
import io.ztech.jkingsley.employeemanagementsystem.dao.MailDAO;
import io.ztech.jkingsley.employeemanagementsystem.dao.PhoneDAO;
import io.ztech.jkingsley.employeemanagementsystem.dao.SkillDAO;

public class SearchEmployeeDelegate {

	public ArrayList<Designation> findAllDesignations() throws PersistenceException {
		DesignationDAO designationDAO = new DesignationDAO();
		return designationDAO.findAllDesignations();
	}

	public ArrayList<Skill> findAllSkills() throws PersistenceException {
		SkillDAO skillDAO = new SkillDAO();
		return skillDAO.findAllSkills();
	}

	public String findPasswordOfEmployeeID(BigInteger emp_id) throws PersistenceException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		return employeeDAO.findPasswordOfEmployee(emp_id);
	}

	public Profile findEmployeeByID(BigInteger emp_id) throws PersistenceException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Profile profile = new Profile();
		profile.setEmployee(employeeDAO.findEmployeeById(emp_id));
		
		EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO();
		profile.setEmergencyContacts(emergencyContactDAO.findEmergencyContactsOfID(emp_id));
		
		MailDAO mailDAO = new MailDAO();
		profile.setMailAddresses(mailDAO.findMailAddressesOfID(emp_id));
		
		PhoneDAO phoneDAO = new PhoneDAO();
		profile.setPhoneNumbers(phoneDAO.findPhoneNumbersOfID(emp_id));
		
		ExperienceDAO experienceDAO = new ExperienceDAO();
		profile.setTotalExperience(experienceDAO.findTotalExperienceOfID(emp_id));
		
		AssignDAO assignDAO = new AssignDAO();
		profile.setProjectsAssigned(assignDAO.findProjectsAssignedByID());
		return profile; 
	}

	public ArrayList<Phone> findPhoneNubersOfID(BigInteger emp_id) throws PersistenceException {
		PhoneDAO phoneDAO = new PhoneDAO();
		return phoneDAO.findPhoneNumbersOfID(emp_id);
	}

	public ArrayList<Mail> findMailAddressesOfID(BigInteger emp_id) throws PersistenceException {
		MailDAO mailDAO = new MailDAO();
		return mailDAO.findMailAddressesOfID(emp_id);
	}

	public ArrayList<EmergencyContact> findEmergencyContactsOfID(BigInteger emp_id) throws PersistenceException {
		EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO(); 
		return emergencyContactDAO.findEmergencyContactsOfID(emp_id);
	}
	
	public ArrayList<Skill> findNotAddedSkillsOfID(BigInteger emp_id) throws PersistenceException{
		ExperienceDAO experienceDAO = new ExperienceDAO();
		SkillDAO skillDAO = new SkillDAO();
		ArrayList<Skill> skills = skillDAO.findAllSkills();
		ArrayList<Experience> totalExperience = experienceDAO.findTotalExperienceOfID(emp_id);
		for(Skill skill:new ArrayList<Skill>(skills)) {
			for(Experience experience:new ArrayList<Experience>(totalExperience)) {
				if(skill.getSkill_id() == experience.getSkill_id()) {
					skills.remove(skill);
					break;
				}
			}
		}
		return skills;
		
	}

}
