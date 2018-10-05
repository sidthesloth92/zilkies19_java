package io.ztech.jkingsley.hrmanagement.services;

import java.math.BigInteger;
import java.util.ArrayList;

import io.ztech.jkingsley.hrmanagement.beans.objects.Designation;
import io.ztech.jkingsley.hrmanagement.beans.objects.EmergencyContact;
import io.ztech.jkingsley.hrmanagement.beans.objects.Experience;
import io.ztech.jkingsley.hrmanagement.beans.objects.Mail;
import io.ztech.jkingsley.hrmanagement.beans.objects.Phone;
import io.ztech.jkingsley.hrmanagement.beans.objects.Profile;
import io.ztech.jkingsley.hrmanagement.beans.objects.Project;
import io.ztech.jkingsley.hrmanagement.beans.objects.Skill;
import io.ztech.jkingsley.hrmanagement.beans.types.EmployeeStatus;
import io.ztech.jkingsley.hrmanagement.delegates.AddEmployeeDelegate;
import io.ztech.jkingsley.hrmanagement.delegates.MappingDelegate;
import io.ztech.jkingsley.hrmanagement.delegates.SearchEmployeeDelegate;
import io.ztech.jkingsley.hrmanagement.delegates.UpdateEmployeeDelegate;
import io.ztech.jkingsley.hrmanagement.utils.PersistenceException;

public class EmployeeManagement {

	AddEmployeeDelegate addEmployeeDelegate = new AddEmployeeDelegate();
	SearchEmployeeDelegate searchEmployeeDelegate = new SearchEmployeeDelegate();
	UpdateEmployeeDelegate updateEmployeeDelegate = new UpdateEmployeeDelegate();


	public Profile findEmployeeById(BigInteger emp_id) {
		try {
			return searchEmployeeDelegate.findEmployeeByID(emp_id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Profile> findEmployeesByDesignation(Designation designation) {
		return null;
	}

	public ArrayList<Designation> findAllDesignations() {
		try {
			return searchEmployeeDelegate.findAllDesignations();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Skill> findSkillsOfEmployee() {
		return null;
	}

	public ArrayList<Skill> findAllSkills() {
		try {
			return searchEmployeeDelegate.findAllSkills();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}


	public boolean addPhoneNumber(Phone phone) {
		try {
			return addEmployeeDelegate.addPhoneNumber(phone);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addEmergencyContact(EmergencyContact emergencyContact) {
		try {
			return addEmployeeDelegate.addEmergencyContact(emergencyContact);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addMailAddress(Mail mail) {
		try {
			return addEmployeeDelegate.addMailAddress(mail);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addExperience(Experience experience) {
		try {
			return addEmployeeDelegate.addExperience(experience);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Phone> findPhoneNumbersOfID(BigInteger emp_id) {
		try {
			return searchEmployeeDelegate.findPhoneNubersOfID(emp_id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updatePhoneNumberOfID(Phone phone) {
		
		try {
			return updateEmployeeDelegate.updatePhoneNumberOfID(phone);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateEmergencyContact(EmergencyContact emergencyContact2) {
		try {
			return updateEmployeeDelegate.updateEmergencyContactOfID(emergencyContact2);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateMailAddressOfID(Mail mail2) {
		return updateEmployeeDelegate.updateMailAddressOfID(mail2);
	}

	public ArrayList<Mail> findMailAddressesOfID(BigInteger emp_id) {
		try {
			return searchEmployeeDelegate.findMailAddressesOfID(emp_id);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<EmergencyContact> findEmergencyContactsOfID(BigInteger emp_id) {
		try {
			return searchEmployeeDelegate.findEmergencyContactsOfID(emp_id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addSkill(Skill skill) {
		try {
			return addEmployeeDelegate.addSkill(skill);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addProject(Project project) {
		try {
			return addEmployeeDelegate.addProject(project);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateEmployeeStatus(BigInteger empID, EmployeeStatus employeeStatus) {
		try {
			return updateEmployeeDelegate.updateEmployeeStatus(empID,employeeStatus);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Skill> findNotAddedSkillsOfID(BigInteger emp_id){
		try {
			return searchEmployeeDelegate.findNotAddedSkillsOfID(emp_id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Project> findAllProjects() {
		try {
			return searchEmployeeDelegate.findAllProjects();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Designation findDesignationOfDesignationID(BigInteger designationID) {
		MappingDelegate mappingDelegate = new MappingDelegate();
		try {
			return mappingDelegate.findDesignationOfDesignationID(designationID);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

}
