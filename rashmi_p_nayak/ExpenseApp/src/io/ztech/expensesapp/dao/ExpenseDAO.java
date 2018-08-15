package io.ztech.expensesapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.expensesapp.beans.Expense;
import io.ztech.expensesapp.beans.ExpenseMember;
import io.ztech.expensesapp.beans.Group;
import io.ztech.expensesapp.beans.GroupPayment;
import io.ztech.expensesapp.beans.User;
import io.ztech.expensesapp.constants.QueryConstants;
import io.ztech.expensesapp.dbutils.DBManager;

public class ExpenseDAO {
	Connection connection;
	PreparedStatement prepareStatement;
	ResultSet resultSet;
	DBManager dbManager;

	public ExpenseDAO() {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			while (resultSet.next()) {
				if (resultSet.getInt(1) > 0)
					isPresent = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
			return isPresent;
		}
	}

	public User logIn(User user) {
		User activeUser = null;
		try {
			connection = dbManager.getConnection();
			prepareStatement = connection.prepareStatement(QueryConstants.VALIDATE_USER);
			prepareStatement.setString(1, user.getUserName());
			prepareStatement.setString(2, user.getUserName());
			prepareStatement.setString(3, user.getPassword());
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				activeUser = new User();
				activeUser.setUserName(resultSet.getString(1));
				activeUser.setEmailId(resultSet.getString(2));
				activeUser.setExpenseLimit(resultSet.getFloat(3));
				activeUser.setuId(resultSet.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			while (resultSet.next()) {
				Expense expense = new Expense();
				expense.setCategoryId(resultSet.getInt(1));
				expense.setTypeId(resultSet.getInt(2));
				expense.setDescription(resultSet.getString(3));
				expense.setAmount(resultSet.getFloat(4));
				expenses.add(expense);
			}
			user.setExpenses(expenses);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
			return user;
		}
	}

	public User viewGroups(User activeUser) {
		User user = new User();
		try {
			connection = dbManager.getConnection();
			prepareStatement = connection.prepareStatement(
					"select Groups.g_id, group_name from Groups join Group_Members on Groups.g_id = Group_Members.g_id where u_id = ?");
			prepareStatement.setInt(1, activeUser.getuId());
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Group group = new Group();
				group.setgId(resultSet.getInt(1));
				group.setGroupName(resultSet.getString(2));
				user.getGroups().add(group);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
			return user;
		}
	}

	public void createGroups(User activeUser) {
		try {
			connection = dbManager.getConnection();
			prepareStatement = connection.prepareStatement(QueryConstants.INSERT_INTO_GROUPS);
			prepareStatement.setString(1, activeUser.getGroups().get(0).getGroupName());
			prepareStatement.execute();
			prepareStatement = connection.prepareStatement(QueryConstants.SELECT_RECENT_GID);
			resultSet = prepareStatement.executeQuery();
			int recentGid = 1;
			while(resultSet.next()) {
				recentGid = resultSet.getInt(1);
			}
			for(User user : activeUser.getGroups().get(0).getUsers()) {
				prepareStatement = connection.prepareStatement(QueryConstants.INSERT_INTO_GROUP_MEMBERS);
				prepareStatement.setInt(1, recentGid);
				prepareStatement.setString(2, user.getUserName());
				prepareStatement.execute();
			}
			 
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
		}
	}
	
	public void addGroupExpense(GroupPayment groupExpense) {
		try {
			connection = dbManager.getConnection();
			prepareStatement = connection.prepareStatement("insert into Group_Expenses(g_id,u_id,amount,category_id,type_id,description) values(?,?,?,?,?,?)");
			prepareStatement.setInt(1, groupExpense.getgId());
			prepareStatement.setInt(2, groupExpense.getuId());
			prepareStatement.setFloat(3, groupExpense.getAmount());
			prepareStatement.setInt(4, groupExpense.getCategoryId());
			prepareStatement.setInt(5, groupExpense.getTypeId());
			prepareStatement.setString(6, groupExpense.getDescription());
			prepareStatement.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
			
		}
	}
	
	public void addExpenseMembers(GroupPayment groupPayment) {
		try {
			connection = dbManager.getConnection();
			prepareStatement = connection.prepareStatement("insert into Expense_Members(u_id,totalAmount,pending,payment_id) values(?,?,?,(select payment_id from Group_Expenses order by payment_id desc limit 1))");
			for(ExpenseMember expenseMember : groupPayment.getExpenseMembers()) {
				prepareStatement.setInt(1, expenseMember.getuId());
				prepareStatement.setFloat(2, expenseMember.getTotalAmount());
				prepareStatement.setBoolean(3, expenseMember.isPending());
				prepareStatement.execute();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			dbManager.closeConnection(resultSet, prepareStatement, connection);
			
		}
	}
}
