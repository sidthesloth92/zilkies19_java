package com.zilker.util;

import com.zilker.constants.RegexConstants;

public class ValidationHelper {
	
	public boolean check_User_name(String user_name) {
		
		if(user_name.equals("") || user_name == null || user_name.equals(" ")){
		
			return false;
		}
		
		return true;
	}
	
	public boolean check_Phone_no(String phone_no) {
		
		if(!phone_no.matches(RegexConstants.PHONE_NO)) {
			
			return false;
		}
		
		return true;
	}
	
	public boolean checkEmail(String email_id) {
		
		if(!email_id.matches(RegexConstants.E_MAIL)) {
			
			return false;
		}
		
		return true;
		
	}
	
	public boolean check_Password(String pass_word) {
		
		if(!pass_word.matches(RegexConstants.PASS_WORD))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean check_Match_Password(String pass_word,String confirm_password) {
		
		if(!pass_word.equals(confirm_password)) {
			
			return false;
		}
		
		return true;
	}
	
}
