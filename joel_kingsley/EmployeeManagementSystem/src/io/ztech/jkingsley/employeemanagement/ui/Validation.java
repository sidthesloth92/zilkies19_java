package io.ztech.jkingsley.employeemanagement.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Skill;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.MailType;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.PhoneType;
import io.ztech.jkingsley.employeemanagementsystem.services.EmployeeManagement;

public class Validation {
	
	private final static Logger LOGGER = Logger.getLogger(InputHandler.class.getName());
	
	public static boolean isValid(String string, String regex) {
		if(string.equals("-1")) {
			return false;
		}else {
			
			Matcher matcher = Pattern.compile(regex,Pattern.CASE_INSENSITIVE).matcher(string);
			return matcher.find();
		}
	}

	public static boolean isValidDate(String value, String regex) {
		LocalDateTime ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(regex);

		try {
			ldt = LocalDateTime.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, fomatter);
				String result = ld.format(fomatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, fomatter);
					String result = lt.format(fomatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
					// Debugging purposes
					// e2.printStackTrace();
				}
			}
		}

		return false;
	}

	public static boolean isValidPhoneType(String type) {
		PhoneType[] phoneTypes = PhoneType.values();
		for(int i=0;i<phoneTypes.length;i++) {
			if(type.equalsIgnoreCase(phoneTypes[i].toString())) {
				return true;
			}
		}
		return false;
	}


	public static boolean isValidSkill(String skill) {
		EmployeeManagement employeeManagement = new EmployeeManagement();
		ArrayList<Skill> skills = employeeManagement.findAllSkills();
		for(Skill skill1:skills) {
			if(skill1.getSkill_name().equalsIgnoreCase(skill)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidMailType(String type) {
		MailType[] mailTypes = MailType.values();
		for(int i=0;i<mailTypes.length;i++) {
			if(type.equalsIgnoreCase(mailTypes[i].toString())) {
				//LOGGER.info("isValidMailType ");
				return true;
			}
		}
		return false;
	}

}
