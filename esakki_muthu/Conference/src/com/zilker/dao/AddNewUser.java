package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.zilker.bean.RegisterData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.SqlConstants;
import com.zilker.delegate.RegisterDelegate;
import com.zilker.util.DatabaseConfig;

public class AddNewUser {	
	
	Connection connection = null;
	
	PreparedStatement prepareStmt = null;
	
	ResultSet resultSet = null;
	
	DatabaseConfig databaseConfig = new DatabaseConfig();
	
	Logger logger = Logger.getLogger(AddNewUser.class.getName());

	public int insertUser(RegisterData registerData) {
		
		try {
		
		connection = databaseConfig.getConnection();
		
		prepareStmt = connection.prepareStatement(SqlConstants.ADD_NORMAL_USER);
		
		prepareStmt.setString(1, registerData.getUser_name());
		
		prepareStmt.setString(2, registerData.getPassword());
		
		prepareStmt.setString(3, registerData.getE_mail());
		
		prepareStmt.setString(4, registerData.getPhone_no());
		
		prepareStmt.executeUpdate();	
				
		
		
		}catch(Exception e) {						
			
			e.printStackTrace();
			
			return 0;
			
		}finally {
			
			databaseConfig.closeConnection(connection, resultSet, prepareStmt);					
			
		}
		
		return 1;
		
		
	}
	
}
