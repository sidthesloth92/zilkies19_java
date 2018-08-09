package com.zilker.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zilker.application.ConnectionConfig;
import com.zilker.application.constants.Constants;

public class Validation extends ConnectionConfig {
	Constants constant = new Constants();
	static Connection con = getConnection();
	
	//=========================
	// Validating Mobile Number
	// ========================
	public boolean mobile(String number) {
		if(number.length() == 10) {
			return true;
		}
		return false;
	}
	// =================
	//  Validating Email
	// =================
	public boolean email(String email) {
		 //https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
		 String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
         java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
         java.util.regex.Matcher m = p.matcher(email);
         return m.matches();
	}
	
	// ===================================
	// Checking if The Name Already Exists 
	// ===================================
	public boolean ifExists(String firstName, String lastName) {
		boolean returnValue = false;
		try {
			PreparedStatement preparedStmt = con.prepareStatement(constant.selectByFirstAndLast);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			ResultSet rs = preparedStmt.executeQuery();
			if(!rs.next()) {
				returnValue = false;
			}else {
				returnValue = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
}

