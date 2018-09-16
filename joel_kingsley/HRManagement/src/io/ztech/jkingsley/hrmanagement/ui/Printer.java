package io.ztech.jkingsley.hrmanagement.ui;

import java.util.logging.Logger;

import io.ztech.jkingsley.hrmanagement.beans.objects.Profile;
import io.ztech.jkingsley.hrmanagement.constants.Titles;

public class Printer {

	private final Logger LOGGER = Logger.getLogger(Application.class.getName());
	
	public void print(Profile profile) {
		LOGGER.info("Employee ID:" + profile.getEmployee().getEmp_id());
		LOGGER.info("Name:" + profile.getEmployee().getEmp_name());
		LOGGER.info("Gender:" + profile.getEmployee().getGender());
		LOGGER.info("Marital Status:" + profile.getEmployee().getMarital_status());
		LOGGER.info("Designation ID:" + profile.getEmployee().getDesignation_id());
		LOGGER.info("DOB:" + profile.getEmployee().getDob());
		LOGGER.info("DOJ:" + profile.getEmployee().getDoj());
		LOGGER.info("Highest Qualification:" + profile.getEmployee().getHighest_qualification());
		LOGGER.info("Blood Group:" + profile.getEmployee().getBloodGroup());
		LOGGER.info("PAN:" + profile.getEmployee().getPan());
		LOGGER.info("Aadhar:" + profile.getEmployee().getAadhar());
		LOGGER.info("UAN:" + profile.getEmployee().getUan());
		LOGGER.info("Present Address:" + profile.getEmployee().getPresent_address());
		LOGGER.info("Permanent Address:" + profile.getEmployee().getPermanent_address());
		LOGGER.info("Employee Status:" + profile.getEmployee().getEmp_status());
		LOGGER.info("Phone Numbers:");
		profile.getPhoneNumbers().forEach(phoneNumber -> LOGGER.info(phoneNumber.getPhone_number() + " " + phoneNumber.getPhone_type()));
		LOGGER.info(Titles.MAIL_PRINT_HEADING);
		profile.getMailAddresses().forEach(mail -> LOGGER.info(mail.getMail_address() + " " + mail.getMail_type()));
		LOGGER.info(Titles.EMERGENCY_CONTACT_PRINT_HEADING);
		profile.getEmergencyContacts().forEach(emergencyContact -> LOGGER.info(emergencyContact.getEmergency_contact_name() + " " + emergencyContact.getEmergency_contact_phone()));
		LOGGER.info(Titles.TOTAL_EXPERIENCE_PRINT_HEADING);
		profile.getTotalExperience().forEach(experience -> LOGGER.info("Skill ID:" + experience.getSkill_id()));
	}
	
	
}
