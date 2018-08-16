package io.ztech.expensesapp.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import io.ztech.expensesapp.beans.Expense;
import io.ztech.expensesapp.beans.ExpenseMember;
import io.ztech.expensesapp.beans.Group;
import io.ztech.expensesapp.beans.GroupPayment;
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
				Expense expense = new Expense();
				addNewExpense(expense, activeUser.getuId());
				expenseService.addNewExpense(expense);

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
		try {
			System.out.println(DisplayConstants.GROUP_MENU);
			int choice = in.nextInt();
			switch (choice) {
			case 1:
				viewGroups();
				break;
			case 2:
				createGroups();
				break;
			default: {
				System.out.println(DisplayConstants.ENTER_VALID_CHOICE);

				myGroups();
			}
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
			System.out.println(DisplayConstants.INVALID_INPUT + " here2");
			in.nextLine();
			myGroups();
		}
	}

	public void viewGroups() {
		activeUser.setGroups(expenseService.viewGroups(activeUser).getGroups());
		for (Group group : activeUser.getGroups()) {
			System.out.println(group.getgId() + " " + group.getGroupName());
		}
		System.out.println("Enter group ID to enter group, enter 0 to go back: ");
		int choice = in.nextInt();
		List<Group> currentGroup = activeUser.getGroups().stream().filter(group -> group.getgId() == choice)
				.collect(Collectors.toList());
		if (currentGroup.isEmpty()) {
			System.out.println(DisplayConstants.ENTER_VALID_CHOICE);
			return;
		}
		enterGroup(choice);
	}

	public void enterGroup(int gId) {
		try {
			System.out.println("1.Add Expense \n2.View Expenses \n3.View Balances \nEnter choice:");
			int choice = in.nextInt();
			switch (choice) {
			case 1:
				addGroupExpense(gId);
				break;
			case 2:// viewExpense
				break;
			case 3: // viewBalances
				break;
			default: {
				System.out.println(DisplayConstants.ENTER_VALID_CHOICE);
				enterGroup(gId);
			}
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
			in.nextLine();
			enterGroup(gId);

		}
	}

	public void addGroupExpense(int gId) {
		GroupPayment groupPayment = new GroupPayment();
		try {
			ExpenseMember member = new ExpenseMember();
			System.out.println("Payment made by (Enter uId) :");
			int uId = in.nextInt();
			member.setuId(uId);
			groupPayment.getExpenseMembers().add(member);
			groupPayment.setuId(uId);
			groupPayment.setgId(gId);
			addNewExpense(groupPayment, uId);
			System.out.println("Enter no.of people to split between (excluding payer):");
			int noOfMembers = in.nextInt();
			while (noOfMembers-- > 0) {
				member = new ExpenseMember();
				System.out.println("Enter uId : ");
				int memberUid = in.nextInt();
				member.setuId(memberUid);
				groupPayment.getExpenseMembers().add(member);
			}
			System.out.println("Enter 1 to split unequally, anything else to split equally:");
			int choice = in.nextInt();
			if (choice != 1) {
				for (ExpenseMember expenseMember : groupPayment.getExpenseMembers()) {
					expenseMember.setTotalAmount(groupPayment.getAmount() / groupPayment.getExpenseMembers().size());
					if (expenseMember.getuId() == uId)
						expenseMember.setPending(false);
					else
						expenseMember.setPending(true);

				}
			} else {
				int totalAmount = 0;
				for (ExpenseMember expenseMember : groupPayment.getExpenseMembers()) {
					System.out.println("Enter amount for uId : " + expenseMember.getuId());
					int amount = in.nextInt();
					expenseMember.setTotalAmount(amount);
					if (expenseMember.getuId() == uId)
						expenseMember.setPending(false);
					else
						expenseMember.setPending(true);
					totalAmount += amount;
				}
				if (totalAmount != groupPayment.getAmount()) {
					System.out.println("Amount does not tally, please check your math!");
					addGroupExpense(gId);
				}

			}

		} catch (InputMismatchException e) {
			e.printStackTrace();
			in.nextLine();
			System.out.println(DisplayConstants.INVALID_INPUT);
			addGroupExpense(gId);
		} finally {

			expenseService.addNewExpense(groupPayment);
			expenseService.addExpenseMembers(groupPayment);
		}
	}

	public void createGroups() {
		try {
			Group group = new Group();
			System.out.println(DisplayConstants.ENTER_GROUP_NAME);
			in.nextLine();
			String groupName = in.nextLine();
			System.out.println(DisplayConstants.ENTER_NO_OF_MEMBERS);
			int noOfMembers = in.nextInt();
			in.nextLine();
			System.out.println("Enter usernames of " + noOfMembers + " members:");
			group.setGroupName(groupName);
			group.getUsers().add(activeUser);
			while (noOfMembers-- > 0) {
				String userName = in.next();
				User user = new User();
				user.setUserName(userName);
				group.getUsers().add(user);
			}
			activeUser.getGroups().add(group);
			expenseService.createGroups(activeUser);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			in.nextLine();
			System.out.println(DisplayConstants.INVALID_INPUT);
			createGroups();
		}
	}

	public void statistics() {
		System.out.println("Statistics!");
	}

	public void addNewExpense(Expense expense, int uId) {
		try {
			System.out.println(DisplayConstants.ENTER_AMOUNT);
			int amount = in.nextInt();
			System.out.println(DisplayConstants.CATEGORY_TYPES);
			int categoryChoice = in.nextInt();
			int categoryOptionSize = CategoryOptions.values().length;
			if (categoryChoice < 0 || categoryChoice > categoryOptionSize) {
				System.out.println(DisplayConstants.ENTER_VALID_CHOICE);
				addNewExpense(expense, uId);
			}
			System.out.println(DisplayConstants.EXPENSE_TYPES);
			int typeChoice = in.nextInt();
			int expenseOptionSize = ExpenseOptions.values().length;
			if (typeChoice < 0 || typeChoice > expenseOptionSize) {
				System.out.println(DisplayConstants.ENTER_VALID_CHOICE);
				addNewExpense(expense, uId);
			}
			in.nextLine();
			System.out.println(DisplayConstants.ENTER_DESCRIPTION);
			String description = in.nextLine();
			if (description.length() > 100) {
				System.out.println("Enter upto 100 characters!");
				addNewExpense(expense, uId);
			}
			expense.setuId(uId);
			expense.setAmount(amount);
			expense.setCategoryId(categoryChoice);
			expense.setTypeId(typeChoice);
			expense.setDescription(description);

		} catch (InputMismatchException e) {
			// e.printStackTrace();
			System.out.println(DisplayConstants.INVALID_INPUT);
			in.nextLine();
			addNewExpense(expense, uId);
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
