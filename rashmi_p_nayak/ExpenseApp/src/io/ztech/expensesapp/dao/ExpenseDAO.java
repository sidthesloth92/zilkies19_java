package io.ztech.expensesapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.expensesapp.beans.Expense;
import io.ztech.expensesapp.beans.User;
import io.ztech.expensesapp.constants.QueryConstants;
import io.ztech.expensesapp.dbutils.DBManager;

public class ExpenseDAO {
	Connection connection;
	PreparedStatement prepareStatement;
	ResultSet resultSet;
	DBManager dbManager;
	
	
	public ExpenseDAO(){
		dbManager = new DBManager();
	}
	
	public void signUp(User user) {
		try {
			connection = dbManager.getConnection();
			prepareStatement = connection.prepareStatement(QueryConstants.INSERT_INTO_USERS);
			prepareStatement.setString(1, user.getUserName());
			prepareStatement.setString(2, user.getEmailId());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setFloat(4, user.getExpenseLimit());
			prepareStatement.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
		}
	}
	
	public boolean isExistingUserName(String userName) {
		boolean isPresent = false;
		try {
			
			connection = dbManager.getConnection();
			prepareStatement = connection.prepareStatement(QueryConstants.CHECK_EXISTING_USERNAME);
			prepareStatement.setString(1, userName);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				if(resultSet.getInt(1) > 0)
					isPresent = true;
			}
				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
			return isPresent;
		}
	}
	
	public User logIn(User user) {
		User activeUser = null;
		try {
			connection =dbManager.getConnection();
			prepareStatement = connection.prepareStatement(QueryConstants.VALIDATE_USER);
			prepareStatement.setString(1, user.getUserName());
			prepareStatement.setString(2, user.getUserName());
			prepareStatement.setString(3, user.getPassword());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				activeUser = new User();
				activeUser.setUserName(resultSet.getString(1));
				activeUser.setEmailId(resultSet.getString(2));
				activeUser.setExpenseLimit(resultSet.getFloat(3));
				activeUser.setuId(resultSet.getInt(4));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
			return activeUser;
		}
	}
	
	public void addNewExpense(Expense expense) {
		try {
			connection = dbManager.getConnection();
			prepareStatement = connection.prepareStatement(QueryConstants.INSERT_INTO_EXPENSES);
			prepareStatement.setInt(1, expense.getuId());
			prepareStatement.setInt(2, expense.getCategoryId());
			prepareStatement.setInt(3, expense.getTypeId());
			prepareStatement.setString(4, expense.getDescription());
			prepareStatement.setFloat(5, expense.getAmount());
			prepareStatement.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
		}
	}
	
	public User showAllExpenses(User activeUser) {
		User user = new User();
		try {
			connection = dbManager.getConnection();
			prepareStatement = connection.prepareStatement(QueryConstants.SELECT_ALL_EXPENSES);
			prepareStatement.setInt(1, activeUser.getuId());
			resultSet = prepareStatement.executeQuery();
			ArrayList<Expense> expenses = new ArrayList<Expense>();
			while(resultSet.next()) {
				Expense expense = new Expense();
				expense.setCategoryId(resultSet.getInt(1));
				expense.setTypeId(resultSet.getInt(2));
				expense.setDescription(resultSet.getString(3));
				expense.setAmount(resultSet.getFloat(4));
				expenses.add(expense);
			}
			user.setExpenses(expenses);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
			return user;
		}
	}
}
