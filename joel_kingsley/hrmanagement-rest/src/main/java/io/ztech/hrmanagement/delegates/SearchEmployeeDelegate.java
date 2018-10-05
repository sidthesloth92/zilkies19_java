package io.ztech.hrmanagement.delegates;

import java.math.BigInteger;
import java.util.ArrayList;

import io.ztech.hrmanagement.beans.objects.Designation;
import io.ztech.hrmanagement.beans.objects.EmergencyContact;
import io.ztech.hrmanagement.beans.objects.Employee;
import io.ztech.hrmanagement.beans.objects.Experience;
import io.ztech.hrmanagement.beans.objects.Mail;
import io.ztech.hrmanagement.beans.objects.Phone;
import io.ztech.hrmanagement.beans.objects.Profile;
import io.ztech.hrmanagement.beans.objects.Project;
import io.ztech.hrmanagement.beans.objects.Skill;
import io.ztech.hrmanagement.dao.AssignDAO;
import io.ztech.hrmanagement.dao.DesignationDAO;
import io.ztech.hrmanagement.dao.EmergencyContactDAO;
import io.ztech.hrmanagement.dao.EmployeeDAO;
import io.ztech.hrmanagement.dao.ExperienceDAO;
import io.ztech.hrmanagement.dao.MailDAO;
import io.ztech.hrmanagement.dao.PhoneDAO;
import io.ztech.hrmanagement.dao.ProjectDAO;
import io.ztech.hrmanagement.dao.SkillDAO;
import io.ztech.hrmanagement.utils.PersistenceException;

public class SearchEmployeeDelegate {

	public ArrayList<Designation> findAllDesignations() throws PersistenceException {
		DesignationDAO designationDAO = new DesignationDAO();
		return designationDAO.findAllDesignations();
	}

	public ArrayList<Skill> findAllSkills() throws PersistenceException {
		SkillDAO skillDAO = new SkillDAO();
		return skillDAO.findAllSkills();
	}

	public ArrayList<Profile> findAllProfiles() throws PersistenceException {
		
		ArrayList<Profile> profiles = new ArrayList<>();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ArrayList<BigInteger> employeeIDs = employeeDAO.findAllEmployeeIDs();
		
		for(BigInteger employeeID:employeeIDs) {
			Profile profile = new Profile();
			profile.setEmployee(employeeDAO.findEmployeeById(employeeID));
			
			EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO();
			profile.setEmergencyContacts(emergencyContactDAO.findEmergencyContactsOfID(employeeID));
			
			MailDAO mailDAO = new MailDAO();
			profile.setMailAddresses(mailDAO.findMailAddressesOfID(employeeID));
			
			PhoneDAO phoneDAO = new PhoneDAO();
			profile.setPhoneNumbers(phoneDAO.findPhoneNumbersOfID(employeeID));
			
			ExperienceDAO experienceDAO = new ExperienceDAO();
			profile.setTotalExperience(experienceDAO.findTotalExperienceOfID(employeeID));
			
			AssignDAO assignDAO = new AssignDAO();
			profile.setProjectsAssigned(assignDAO.findProjectsAssignedByID());
			
			profiles.add(profile);
		}
		return profiles;
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

	public ArrayList<Project> findAllProjects() throws PersistenceException {
		ProjectDAO projectDAO = new ProjectDAO();
		return projectDAO.findAllProjects();
	}

}
