package io.ztech.expensesapp.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import io.ztech.expensesapp.beans.Expense;
import io.ztech.expensesapp.beans.User;
import io.ztech.expensesapp.constants.CategoryOptions;
import io.ztech.expensesapp.constants.DisplayConstants;
import io.ztech.expensesapp.constants.ExpenseOptions;
import io.ztech.expensesapp.constants.MainMenuOptions;
import io.ztech.expensesapp.constants.RegexConstants;
import io.ztech.expensesapp.constants.StartUpMenuOptions;
import io.ztech.expensesapp.exceptions.LoginFailedException;
import io.ztech.expensesapp.exceptions.UsernameAlreadyExistsException;
import io.ztech.expensesapp.dao.ExpenseDAO;
import io.ztech.expensesapp.services.ExpenseService;

public class ExpenseManager {
	Scanner in;
	Validator validator;
	User activeUser;
	ExpenseService expenseService;

	public ExpenseManager() {
		in = new Scanner(System.in);
		validator = new Validator();
		expenseService = new ExpenseService();

	}

	public void startUpMenu() {
		try {
			System.out.println(DisplayConstants.STARTUP_MENU);
			int choice = in.nextInt();
			StartUpMenuOptions option = StartUpMenuOptions.values()[choice - 1];
			switch (option) {
			case SIGN_UP:
				signUp();
				break;
			case LOG_IN:
				logIn();
				break;
			default: {
				System.out.println(DisplayConstants.ENTER_VALID_CHOICE);
				startUpMenu();
			}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(DisplayConstants.INVALID_INPUT);
			in.nextLine();
			startUpMenu();
		}

	}

	public void signUp() {
		try {
			System.out.println(DisplayConstants.ENTER_USERNAME);
			String userName = in.next();
			System.out.println(DisplayConstants.ENTER_EMAILID);
			String emailId = in.next();
			if (!validator.isValidated(emailId, RegexConstants.EMAIL_REGEX)) {
				System.out.println(DisplayConstants.INVALID_EMAILID);
				signUp();
			}
			System.out.println(DisplayConstants.ENTER_NEW_PASSWORD);
			String password = in.next();
			System.out.println(DisplayConstants.CONFIRM_PASSWORD);
			String confirmPassword = in.next();
			if (!password.equals(confirmPassword) || password.length() < 6) {
				System.out.println(DisplayConstants.PASSWORD_ERROR);
				signUp();
			}
			System.out.println(DisplayConstants.ADD_EXPENSE_LIMIT);
			float expenseLimit = in.nextFloat();
			User user = new User();
			user.setEmailId(emailId);
			user.setUserName(userName);
			user.setPassword(password);
			user.setExpenseLimit(expenseLimit);

			expenseService.signUp(user);

			startUpMenu();
		} catch (InputMismatchException e) {
			// e.printStackTrace();
			System.out.println(DisplayConstants.INVALID_INPUT);
			in.nextLine();
			signUp();
		} catch (UsernameAlreadyExistsException e) {
			System.out.println(e.getMessage());
			signUp();
		}

	}

	public void logIn() {
		User user = new User();
		try {
			System.out.println(DisplayConstants.ENTER_USERNAME_EMAIL);
			String input = in.next();
			System.out.println(DisplayConstants.ENTER_PASSWORD);
			String password = in.next();
			user.setUserName(input);
			user.setPassword(password);
			activeUser = expenseService.logIn(user);
			mainMenu();
		} catch (InputMismatchException e) {
			// e.printStackTrace();
			System.out.println(DisplayConstants.INVALID_INPUT);
			in.nextLine();
			logIn();
		} catch (LoginFailedException e) {
			System.out.println(e.getMessage());
			logIn();
		}
	}

	public void mainMenu() {
		try {
			System.out.println(DisplayConstants.MAIN_MENU);
			int choice = in.nextInt();
			MainMenuOptions option = MainMenuOptions.values()[choice - 1];
			switch (option) {
			case MY_EXPENSES:
				myExpenses();
				break;
			case MY_GROUPS:
				myGroups();
				break;
			case STATISTICS:
				statistics();
				break;
			default: {
				System.out.println(DisplayConstants.ENTER_VALID_CHOICE);
				mainMenu();
			}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(DisplayConstants.INVALID_INPUT);
			in.nextLine();
			mainMenu();
		}
	}

	public void myExpenses() {
		try {
			System.out.println(DisplayConstants.MY_EXPENSE_OPTIONS);
			int choice = in.nextInt();
			switch (choice) {
			case 1:
				addNewExpense();
				break;
			case 2: 
				showAllExpenses();
				break;
			default: {
				System.out.println(DisplayConstants.ENTER_VALID_CHOICE);
				myExpenses();
			}
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
			System.out.println(DisplayConstants.INVALID_INPUT);
			in.nextLine();
			myExpenses();
		}
	}

	public void myGroups() {
		System.out.println("My groups!");
	}

	public void statistics() {
		System.out.println("Statistics!");
	}

	public void addNewExpense() {
		Expense newExpense = new Expense();
		try {
			System.out.println(DisplayConstants.ENTER_AMOUNT);
			int amount = in.nextInt();
			System.out.println(DisplayConstants.CATEGORY_TYPES);
			int categoryChoice = in.nextInt();
			int categoryOptionSize = CategoryOptions.values().length;
			if (categoryChoice < 0 || categoryChoice > categoryOptionSize) {
				System.out.println(DisplayConstants.ENTER_VALID_CHOICE);
				addNewExpense();
			}
			System.out.println(DisplayConstants.EXPENSE_TYPES);
			int typeChoice = in.nextInt();
			int expenseOptionSize = ExpenseOptions.values().length;
			if (typeChoice < 0 || typeChoice > expenseOptionSize) {
				System.out.println(DisplayConstants.ENTER_VALID_CHOICE);
				addNewExpense();
			}
			in.nextLine();
			System.out.println(DisplayConstants.ENTER_DESCRIPTION);
			String description = in.nextLine();
			if (description.length() > 100) {
				System.out.println("Enter upto 100 characters!");
				addNewExpense();
			}

			newExpense.setuId(activeUser.getuId());
			newExpense.setAmount(amount);
			newExpense.setCategoryId(categoryChoice);
			newExpense.setTypeId(typeChoice);
			newExpense.setDescription(description);

			expenseService.addNewExpense(newExpense);

		} catch (InputMismatchException e) {
			// e.printStackTrace();
			System.out.println(DisplayConstants.INVALID_INPUT);
			in.nextLine();
			addNewExpense();
		}

	}

	public void showAllExpenses() {
		activeUser.setExpenses(expenseService.showAllExpense(activeUser).getExpenses());
		for (Expense expense : activeUser.getExpenses()) {
			System.out.println(expense.getAmount() + " " + expense.getCategoryId() + " " + expense.getTypeId() + " "
					+ expense.getDescription());
		}
	}

	public static void main(String[] args) {
		new ExpenseManager().startUpMenu();

	}
}
