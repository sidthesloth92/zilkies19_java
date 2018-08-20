package com.zilker.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public boolean checkMatchPassword(String password,String confirmPassword) {
		
		if(!password.equals(confirmPassword)) {
			
			return false;
		}
		
		return true;
	}
	
	public boolean checkDate(String date) {
		
		if(!date.matches(RegexConstants.DATE_CHECKING)) {
			
			return false;
			
		}
		
		return true;
	}
	
	public boolean checkFromDate(String date) {
								
		//Date newDate = new Date();
		
		
		return true;
	}
	
}
