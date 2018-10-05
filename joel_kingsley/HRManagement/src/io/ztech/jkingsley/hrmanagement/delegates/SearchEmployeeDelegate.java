package io.ztech.jkingsley.hrmanagement.delegates;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.ztech.jkingsley.hrmanagement.beans.objects.Assign;
import io.ztech.jkingsley.hrmanagement.beans.objects.Designation;
import io.ztech.jkingsley.hrmanagement.beans.objects.EmergencyContact;
import io.ztech.jkingsley.hrmanagement.beans.objects.Employee;
import io.ztech.jkingsley.hrmanagement.beans.objects.Experience;
import io.ztech.jkingsley.hrmanagement.beans.objects.Mail;
import io.ztech.jkingsley.hrmanagement.beans.objects.Phone;
import io.ztech.jkingsley.hrmanagement.beans.objects.Profile;
import io.ztech.jkingsley.hrmanagement.beans.objects.Project;
import io.ztech.jkingsley.hrmanagement.beans.objects.ResponseObject;
import io.ztech.jkingsley.hrmanagement.beans.objects.Skill;
import io.ztech.jkingsley.hrmanagement.constants.StatusCodes;
import io.ztech.jkingsley.hrmanagement.dao.DesignationDAO;
import io.ztech.jkingsley.hrmanagement.dao.EmergencyContactDAO;
import io.ztech.jkingsley.hrmanagement.dao.EmployeeDAO;
import io.ztech.jkingsley.hrmanagement.dao.ExperienceDAO;
import io.ztech.jkingsley.hrmanagement.dao.MailDAO;
import io.ztech.jkingsley.hrmanagement.dao.PhoneDAO;
import io.ztech.jkingsley.hrmanagement.dao.ProjectDAO;
import io.ztech.jkingsley.hrmanagement.dao.SkillDAO;
import io.ztech.jkingsley.hrmanagement.utils.PersistenceException;
import io.ztech.jkingsley.hrmanagement.utils.RestCaller;

public class SearchEmployeeDelegate {
	
	RestCaller restCaller;
	
	public SearchEmployeeDelegate() {
		super();
		restCaller = new RestCaller();
	}

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
		
		Profile profile = new Profile();
		ResponseObject responseObject = new ResponseObject();
		
		try {
			JsonReader reader = restCaller.doGet("http://localhost:8080/profiles/" + emp_id);
			JsonObject jsonObj = reader.readObject();
			reader.close();
			
			responseObject.setStatus(jsonObj.getInt("status"));
			
			if(responseObject.getStatus() == StatusCodes.SUCCESS) {
				JsonArray jsonArray = jsonObj.getJsonArray("objects");
				JsonObject jsonObject = jsonArray.getJsonObject(0);
				JsonObject empObj = jsonObject.getJsonObject("employee");
				JsonArray phObj = jsonObject.getJsonArray("phoneNumbers");
				JsonArray eContactsObj = jsonObject.getJsonArray("emergencyContacts");
				JsonArray mAddressesObj = jsonObject.getJsonArray("mailAddresses");
				JsonArray pAssigned = jsonObject.getJsonArray("projectsAssigned");
				JsonArray tExperience = jsonObject.getJsonArray("totalExperience");
				
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				
				Employee employee = gson.fromJson(empObj.toString(), Employee.class);
				ArrayList<Phone> phoneNumbers = gson.fromJson(phObj.toString(), new TypeToken<ArrayList<Phone>>(){}.getType());
				ArrayList<EmergencyContact> emergencyContacts = gson.fromJson(eContactsObj.toString(), new TypeToken<ArrayList<EmergencyContact>>() {}.getType());
				ArrayList<Mail> mails = gson.fromJson(mAddressesObj.toString(), new TypeToken<ArrayList<Mail>>() {}.getType());
				ArrayList<Assign> assignments = gson.fromJson(pAssigned.toString(), new TypeToken<ArrayList<Assign>>() {}.getType());
				ArrayList<Experience> experience = gson.fromJson(tExperience.toString(), new TypeToken<ArrayList<Experience>>() {}.getType());
						
				profile.setEmployee(employee);
				profile.setPhoneNumbers(phoneNumbers);
				profile.setEmergencyContacts(emergencyContacts);
				profile.setMailAddresses(mails);
				profile.setProjectsAssigned(assignments);
				profile.setTotalExperience(experience);
				
			} else {
				
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} finally {
			
		}
/*		EmployeeDAO employeeDAO = new EmployeeDAO();
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
		profile.setProjectsAssigned(assignDAO.findProjectsAssignedByID());*/
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
