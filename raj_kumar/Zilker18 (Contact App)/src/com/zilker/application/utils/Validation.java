package com.zilker.application.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zilker.application.ConnectionConfig;
import com.zilker.application.constants.Constants;

public class Validation extends ConnectionConfig {
	static Connection con = getConnection();
	
	//===============
	// Validation
	// ==============
	
	public static boolean isValid(String value, String regex) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(value);
        return m.matches();
	}
	// ===================================
	// Checking if The Name Already Exists 
	// ===================================
	public static boolean ifExists(String firstName, String lastName) {
		boolean returnValue = false;
		try {
			PreparedStatement preparedStmt = con.prepareStatement(Constants.SELECT_BY_FIRST_LAST);
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

