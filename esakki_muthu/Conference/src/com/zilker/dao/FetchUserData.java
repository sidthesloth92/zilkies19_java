package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.zilker.bean.LoginData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.DBConstants;
import com.zilker.constants.SqlConstants;
import com.zilker.delegate.LoginDelegate;
import com.zilker.ui.AdminPage;
import com.zilker.ui.UserPage;
import com.zilker.util.DatabaseConfig;

public class FetchUserData {

	Logger logger = Logger.getLogger(FetchUserData.class.getName());

	DatabaseConfig databaseConfig = new DatabaseConfig();

	UserPage homePage = new UserPage();

	AdminPage adminPage = new AdminPage();

	LoginDelegate loginDelegate = new LoginDelegate();

	Connection connection = null;

	PreparedStatement prepareStmt = null;

	ResultSet resultSet = null;

	UserData userData = new UserData();

	public UserData getUser(LoginData loginData) {

		try {

			connection = databaseConfig.getConnection();

			prepareStmt = connection.prepareStatement(SqlConstants.LOGIN);

			prepareStmt.setString(1, loginData.getE_mail());

			prepareStmt.setString(2, loginData.getPassword());

			resultSet = prepareStmt.executeQuery();

			if (resultSet.next()) {

				loginData.setUser_id(resultSet.getInt(DBConstants.USER_ID));

				loginData.setUser_name(resultSet.getString(DBConstants.USER_NAME));

				loginData.setPhone_no(resultSet.getString(DBConstants.PHONE_NO));

				int roleId = resultSet.getInt(DBConstants.ROLE_ID);

				PreparedStatement roleStmt = connection.prepareStatement(SqlConstants.GET_ROLE);

				roleStmt.setInt(1, roleId);

				ResultSet roleSet = roleStmt.executeQuery();

				// roleStmt.close();

				roleSet.next();

				String roleName = roleSet.getString(DBConstants.ROLE_NAME);

				loginData.setRole_id(roleId);

				loginData.setRole_name(roleName);

				roleSet.close();

				PreparedStatement loginStmt = connection.prepareStatement(SqlConstants.STORE_LOGIN_TIME);

				loginStmt.setInt(1, loginData.getUserId());

				loginStmt.executeUpdate();

				loginStmt.close();				
				
				userData.setUserId(loginData.getUserId());
				
				userData.setUserName(loginData.getUserName());
				
				userData.setPassword(loginData.getPassword());
				
				userData.setRoleId(loginData.getRoleId());
				
				userData.setRoleName(loginData.getRoleName());
				
				userData.setLoginStatus(1);
				
				logger.info(StringConstants.LOGIN_SUCCESS);
				

				
			} else {
				
				userData.setRoleId(0);
				
				userData.setLoginStatus(0);

				//loginController.login_Failed();

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			databaseConfig.closeConnection(connection, resultSet, prepareStmt);			

		}
		
		return userData;

	}

}
