package io.ztech.expensesapp.delegates;

import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

import io.ztech.expensesapp.beans.Expense;
import io.ztech.expensesapp.beans.Group;
import io.ztech.expensesapp.beans.GroupPayment;
import io.ztech.expensesapp.beans.User;
import io.ztech.expensesapp.constants.DisplayConstants;
import io.ztech.expensesapp.exceptions.CouldNotAddMembersException;
import io.ztech.expensesapp.exceptions.LoginFailedException;
import io.ztech.expensesapp.exceptions.UsernameAlreadyExistsException;
import io.ztech.expensesapp.dao.ExpenseDAO;

public class ExpenseDelegate {
	ExpenseDAO expenseDao;

	public ExpenseDelegate() {
		expenseDao = new ExpenseDAO();
	}

	public void signUp(User user) throws UsernameAlreadyExistsException, SQLException {
		if (expenseDao.isExistingUserName(user.getUserName())) {
			throw new UsernameAlreadyExistsException(DisplayConstants.USERNAME_EXISTS);
		}
		expenseDao.signUp(user);

	}

	public User logIn(User user) throws LoginFailedException, SQLException {
		User activeUser = expenseDao.logIn(user);
		if (activeUser == null) {
			throw new LoginFailedException(DisplayConstants.INVALID_USERNAME_PASSWORD);
		}
		return activeUser;

	}

	public void addNewExpense(Expense expense) throws SQLException {
		if (expense instanceof GroupPayment)
			expenseDao.addGroupExpense((GroupPayment) expense);
		if (expense instanceof Expense)
			expenseDao.addNewExpense(expense);

	}

	public User showAllExpense(User activeUser) throws SQLException {

		User user = expenseDao.showAllExpenses(activeUser);
		return user;

	}

	public User viewGroups(User activeUser) throws SQLException {
		User user = expenseDao.viewGroups(activeUser);
		return user;
	}

	public void createGroups(User activeUser) throws CouldNotAddMembersException {
		try {
			expenseDao.createGroups(activeUser);
		} catch (CouldNotAddMembersException e) {
			throw new CouldNotAddMembersException(e.getMessage());
		}
	}

	public void addExpenseMembers(GroupPayment groupPayment) throws SQLException {
		expenseDao.addExpenseMembers(groupPayment);
	}

	public Group viewGroupExpenses(Group activeGroup) throws SQLException {
		Group group = expenseDao.viewGroupExpenses(activeGroup);
		return group;
	}

	public void editExpenseLimit(User activeUser) throws SQLException {
		expenseDao.editExpenseLimit(activeUser);
	}

	public GroupPayment viewBalances(Group activeGroup) throws SQLException {
		GroupPayment groupPayment = expenseDao.viewBalances(activeGroup);
		return groupPayment;
	}
}
