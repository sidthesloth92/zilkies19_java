package com.zilkeesaro.util;

import com.zilkeesaro.constants.RegexContstants;
import com.zilkeesaro.constants.Strings;

public class ValidationHelper {

	public Boolean getHome(String home) {
		
		if (home.equals("") || home == null) {
			return false;
		}else if(home.equals("-1")) {
			return true;
		} 
		else {
			if (home.matches(RegexContstants.HOME_NUM)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public Boolean getOffice(String office) {
		
		if (office.equals("") || office == null) {
			return false;
		}else if(office.equals("-1")) {
			return true;
		}
		else {
			if (office.matches(RegexContstants.OFFICE_NUM)) {
				return true;
			} else {				
				return false;
			}
		}

	}

	public Boolean getE_mail(String e_mail) {
	
		if (e_mail.equals(" ") || e_mail == null) {
			return false;
		}else if(e_mail.equals("-1")) {
			return true;
		}
		else {
			if (e_mail.matches(RegexContstants.E_MAIL)) {
				return true;
			} else {
				
				return false;
			}
		}

	}

	public Boolean getMobile_number(String mobile_number) {
		
		if (mobile_number.equals("") || mobile_number == null) {
			return false;
		}else if(mobile_number.equals("-1")) {
			return true;
		}
		else if (mobile_number.matches(RegexContstants.MOBILE_NUM)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean getFirst_name(String first_name) {
		
		if (first_name.equals("") || first_name == null) {
			return false;
		}
		return true;
	}

	public Boolean getLast_name(String last_name) {
		
		if (last_name.equals("") || last_name == null) {
			return false;
		}
		return true;
	}

}
