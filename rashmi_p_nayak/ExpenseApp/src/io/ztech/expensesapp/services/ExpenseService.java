package io.ztech.expensesapp.services;

import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

import io.ztech.expensesapp.beans.Expense;
import io.ztech.expensesapp.beans.Group;
import io.ztech.expensesapp.beans.GroupPayment;
import io.ztech.expensesapp.beans.User;
import io.ztech.expensesapp.exceptions.CouldNotAddMembersException;
import io.ztech.expensesapp.exceptions.LoginFailedException;
import io.ztech.expensesapp.exceptions.UsernameAlreadyExistsException;
import io.ztech.expensesapp.delegates.ExpenseDelegate;

public class ExpenseService {
	ExpenseDelegate expenseDelegate;

	public ExpenseService() {
		expenseDelegate = new ExpenseDelegate();
	}

	public void signUp(User user) throws UsernameAlreadyExistsException, SQLException {
		try {
			expenseDelegate.signUp(user);
		}
		catch (UsernameAlreadyExistsException e) {
			throw new UsernameAlreadyExistsException(e.getMessage());
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}

	public User logIn(User user) throws LoginFailedException,SQLException {
		User activeUser;
		try {
			activeUser = expenseDelegate.logIn(user);
		} catch (LoginFailedException e) {
			throw new LoginFailedException(e.getMessage());

		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		return activeUser;
	}

	public void addNewExpense(Expense expense) throws SQLException{
		expenseDelegate.addNewExpense(expense);
	}

	public User showAllExpense(User activeUser) throws SQLException{
		User user = expenseDelegate.showAllExpense(activeUser);
		return user;
	}

	public User viewGroups(User activeUser) throws SQLException{
		User user = expenseDelegate.viewGroups(activeUser);
		return user;
	}

	public void createGroups(User activeUser) throws CouldNotAddMembersException {
		try {
			expenseDelegate.createGroups(activeUser);
		} catch (CouldNotAddMembersException e) {
			throw new CouldNotAddMembersException(e.getMessage());
		}
	}

	public void addExpenseMembers(GroupPayment groupPayment) throws SQLException{
		expenseDelegate.addExpenseMembers(groupPayment);
	}

	public Group viewGroupExpenses(Group activeGroup) throws SQLException{
		Group group = expenseDelegate.viewGroupExpenses(activeGroup);
		return group;
	}

	public GroupPayment viewBalances(Group activeGroup) throws SQLException{
		GroupPayment groupPayment = expenseDelegate.viewBalances(activeGroup);
		return groupPayment;
	}

	public void editExpenseLimit(User activeUser) throws SQLException{
		expenseDelegate.editExpenseLimit(activeUser);
	}
}
