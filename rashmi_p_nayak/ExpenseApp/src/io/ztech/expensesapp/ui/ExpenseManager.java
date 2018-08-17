package io.ztech.expensesapp.ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import io.ztech.expensesapp.beans.Expense;
import io.ztech.expensesapp.beans.ExpenseMember;
import io.ztech.expensesapp.beans.Group;
import io.ztech.expensesapp.beans.GroupPayment;
import io.ztech.expensesapp.beans.User;
import io.ztech.expensesapp.constants.CategoryOptions;
import io.ztech.expensesapp.constants.DisplayConstants;
import io.ztech.expensesapp.constants.ExpenseMenuOptions;
import io.ztech.expensesapp.constants.ExpenseOptions;
import io.ztech.expensesapp.constants.GroupMenuOptions;
import io.ztech.expensesapp.constants.GroupOptions;
import io.ztech.expensesapp.constants.MainMenuOptions;
import io.ztech.expensesapp.constants.RegexConstants;
import io.ztech.expensesapp.constants.StartUpMenuOptions;
import io.ztech.expensesapp.exceptions.CouldNotAddMembersException;
import io.ztech.expensesapp.exceptions.LoginFailedException;
import io.ztech.expensesapp.exceptions.UsernameAlreadyExistsException;
import io.ztech.expensesapp.services.ExpenseService;

public class ExpenseManager {
	Scanner in;
	Validator validator;
	User activeUser;
	ExpenseService expenseService;
	Group activeGroup;
	Logger logger;

	public ExpenseManager() {
		in = new Scanner(System.in);
		validator = new Validator();
		expenseService = new ExpenseService();
		logger = Logger.getLogger(ExpenseManager.class.getName());

	}

	public void startUpMenu() {
		while (true) {
			try {
				logger.info(DisplayConstants.STARTUP_MENU);
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
					logger.info(DisplayConstants.ENTER_VALID_CHOICE);

				}
				}
			} catch (Exception e) {
				// e.printStackTrace();
				logger.info(DisplayConstants.INVALID_INPUT);
				in.nextLine();

			}
		}

	}

	public void signUp() {
		
		
		while (true) {
			try {
				logger.info("\n");
				logger.info(DisplayConstants.ENTER_USERNAME);
				String userName = in.next();
				logger.info(DisplayConstants.ENTER_EMAILID);
				String emailId = in.next();
				if (!validator.isValidated(emailId, RegexConstants.EMAIL_REGEX)) {
					logger.info(DisplayConstants.INVALID_EMAILID);
					continue;
				}
				logger.info(DisplayConstants.ENTER_NEW_PASSWORD);
				String password = in.next();
				logger.info(DisplayConstants.CONFIRM_PASSWORD);
				String confirmPassword = in.next();
				if (!password.equals(confirmPassword) || password.length() < 6) {
					logger.info(DisplayConstants.PASSWORD_ERROR);
					continue;
				}
				logger.info(DisplayConstants.ADD_EXPENSE_LIMIT);
				float expenseLimit = in.nextFloat();
				User user = new User();
				user.setEmailId(emailId);
				user.setUserName(userName);
				user.setPassword(password);
				user.setExpenseLimit(expenseLimit);

				expenseService.signUp(user);
				logger.info(DisplayConstants.SUCCESSFUL_SIGNUP);
				break;

			} catch (InputMismatchException e) {
				// e.printStackTrace();
				logger.info(DisplayConstants.INVALID_INPUT);
				in.nextLine();
			} catch (UsernameAlreadyExistsException e) {
				logger.info(e.getMessage());

			}
		}
	}

	public void logIn() {
		while (true) {
			User user = new User();
			try {
				logger.info(DisplayConstants.ENTER_USERNAME_EMAIL);
				String input = in.next();
				logger.info(DisplayConstants.ENTER_PASSWORD);
				String password = in.next();
				user.setUserName(input);
				user.setPassword(password);
				activeUser = expenseService.logIn(user);
				logger.info(DisplayConstants.SUCCESSFUL_LOGIN);
				mainMenu();
				return;
			} catch (InputMismatchException e) {
				// e.printStackTrace();
				logger.info(DisplayConstants.INVALID_INPUT);
				in.nextLine();

			} catch (LoginFailedException e) {
				logger.info(e.getMessage());
				return;
			}
		}
	}

	public void mainMenu() {
		while (true) {
			try {
				logger.info("\n");
				logger.info(DisplayConstants.MAIN_MENU);
				int choice = in.nextInt();
				MainMenuOptions option = MainMenuOptions.values()[choice - 1];
				switch (option) {
				case MY_EXPENSES:
					myExpenses();
					break;
				case MY_GROUPS:
					myGroups();
					break;
				case EXPENSE_LIMIT:
					editExpenseLimit();
					break;
				case LOG_OUT:
					activeUser = null;
					return;
				default: {
					logger.info(DisplayConstants.ENTER_VALID_CHOICE);

				}
				}
			} catch (Exception e) {
				// e.printStackTrace();
				logger.info(DisplayConstants.INVALID_INPUT);
				in.nextLine();

			}
		}
	}

	public void myExpenses() {
		while (true) {
			try {
				logger.info(DisplayConstants.MY_EXPENSE_OPTIONS);
				int choice = in.nextInt();
				ExpenseMenuOptions option = ExpenseMenuOptions.values()[choice - 1];
				switch (option) {
				case ADD_EXPENSE:
					Expense expense = new Expense();
					addNewExpense(expense, activeUser.getuId());
					expenseService.addNewExpense(expense);
					logger.info(DisplayConstants.EXPENSE_ADDED);
					break;
				case SHOW_EXPENSES:
					showAllExpenses();
					break;
				case BACK:
					return;
				default: {
					logger.info(DisplayConstants.ENTER_VALID_CHOICE);
				}
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
				logger.info(DisplayConstants.INVALID_INPUT);
				in.nextLine();

			}
		}
	}

	public void myGroups() {
		while (true) {
			try {
				logger.info(DisplayConstants.GROUP_MENU);
				int choice = in.nextInt();
				GroupMenuOptions option = GroupMenuOptions.values()[choice - 1];
				switch (option) {
				case VIEW_GROUPS:
					viewGroups();
					break;
				case CREATE_GROUP:
					createGroups();
					break;
				case BACK:
					return;
				default: {
					logger.info(DisplayConstants.ENTER_VALID_CHOICE);
				}
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
				logger.info(DisplayConstants.INVALID_INPUT);
				in.nextLine();
			}
		}
	}

	public void viewGroups() {
		activeUser.setGroups(expenseService.viewGroups(activeUser).getGroups());
		if (activeUser.getGroups().size() == 0) {
			logger.info(DisplayConstants.GROUPS_EMPTY);
			return;
		}
		logger.info(DisplayConstants.GROUP_HEADING);
		for (Group group : activeUser.getGroups()) {
			logger.info(group.getgId() + " " + group.getGroupName());

		}
		List<Group> currentGroup;
		while (true) {
			logger.info(DisplayConstants.ENTER_GID);
			int choice = in.nextInt();
			if (choice == 0)
				return;
			currentGroup = activeUser.getGroups().stream().filter(group -> group.getgId() == choice)
					.collect(Collectors.toList());
			if (currentGroup.isEmpty()) {
				logger.info(DisplayConstants.ENTER_VALID_CHOICE);
				continue;
			}
			break;
		}
		activeGroup = currentGroup.get(0);
		enterGroup();
	}

	public void enterGroup() {
		while (true) {
			try {
				logger.info(DisplayConstants.GROUP_OPTIONS);
				int choice = in.nextInt();
				GroupOptions option = GroupOptions.values()[choice - 1];
				switch (option) {
				case ADD_EXPENSE:
					addGroupExpense();
					break;
				case VIEW_EXPENSES:
					viewGroupExpenses();
					break;
				case VIEW_BALANCES:
					viewBalances();
					break;
				case VIEW_MEMBERS:
					viewMembers();
					break;
				case BACK:
					activeGroup = null;
					return;
				default: {
					logger.info(DisplayConstants.ENTER_VALID_CHOICE);

				}
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
				in.nextLine();

			}
		}
	}

	public void viewMembers() {
		logger.info(DisplayConstants.GROUP_MEMBER_HEADING);
		for (User user : activeGroup.getUsers()) {
			logger.info(user.getuId() + " " + user.getUserName());
		}
	}

	public void viewGroupExpenses() {
		Group group = expenseService.viewGroupExpenses(activeGroup);
		if (group.getGroupPayments().isEmpty()) {
			logger.info(DisplayConstants.EXPENSES_EMPTY);
			return;
		}
		logger.info(DisplayConstants.GROUP_EXPENSE_HEADING);
		for (GroupPayment groupPayment : group.getGroupPayments()) {
			logger.info(DisplayConstants.EXPENSE_PAID_BY + groupPayment.getExpenseMembers().stream()
					.filter(member -> member.getuId() == groupPayment.getuId()).findAny().get().getUserName());
			logger.info(DisplayConstants.AMOUNT + groupPayment.getAmount());
			logger.info(DisplayConstants.DESCRIPTION + groupPayment.getDescription());
			logger.info(DisplayConstants.LAST_UPDATED_AT + groupPayment.getUpdatedAt());
			boolean involved = false;
			for (ExpenseMember member : groupPayment.getExpenseMembers()) {
				if (activeUser.getuId() == member.getuId()) {
					involved = true;
					if (activeUser.getuId() == groupPayment.getuId())
						logger.info(DisplayConstants.YOU_LENT + (groupPayment.getAmount() - member.getAmountPaid()));

					else
						logger.info(DisplayConstants.YOU_BORROWED + member.getAmountPaid());
					logger.info(DisplayConstants.STATUS
							+ (member.isPending() ? DisplayConstants.NOT_SETTLED : DisplayConstants.SETTLED));
					logger.info("\n");
				}

			}
			if (!involved)
				logger.info(DisplayConstants.NOT_INVOLVED);
		}
		logger.info("\n");
	}

	public void addGroupExpense() {
		while (true) {
			GroupPayment groupPayment = new GroupPayment();
			try {
				ExpenseMember member = new ExpenseMember();
				logger.info(DisplayConstants.PAYMENT_MADE_BY);
				String payer = in.next();
				if (!activeGroup.getUsers().stream().anyMatch(user -> user.getUserName().compareTo(payer) == 0)) {
					logger.info(DisplayConstants.ENTER_EXISTING_USERNAME);
					continue;
				}
				member.setUserName(payer);
				groupPayment.getExpenseMembers().add(member);
				User user = activeGroup.getUsers().stream().filter(users -> users.getUserName().compareTo(payer) == 0)
						.findAny().get();
				groupPayment.setuId(user.getuId());
				groupPayment.setgId(activeGroup.getgId());
				addNewExpense(groupPayment, user.getuId());
				logger.info(DisplayConstants.NO_OF_PEOPLE_TO_SPLIT);
				int noOfMembers = in.nextInt();
				while (noOfMembers > 0) {
					member = new ExpenseMember();
					logger.info(DisplayConstants.ENTER_USERNAME_EMAIL);
					String memberUserName = in.next();
					if (!activeGroup.getUsers().stream()
							.anyMatch(users -> users.getUserName().compareTo(memberUserName) == 0)) {
						logger.info(DisplayConstants.ENTER_EXISTING_USERNAME);
						continue;
					}
					if (memberUserName.compareTo(payer) == 0) {
						logger.info(DisplayConstants.ENTER_APART_FROM_PAYER);
						continue;
					}
					member.setUserName(memberUserName);
					groupPayment.getExpenseMembers().add(member);
					noOfMembers--;
				}
				logger.info(DisplayConstants.SPLIT_EQUAL_UNEQUAL);
				int choice = in.nextInt();
				if (choice != 1) {
					for (ExpenseMember expenseMember : groupPayment.getExpenseMembers()) {
						if (expenseMember.getUserName().compareTo(user.getUserName()) == 0) {
							expenseMember.setPending(false);
							expenseMember
									.setAmountPaid(groupPayment.getAmount() / groupPayment.getExpenseMembers().size());
							expenseMember
									.setTotalAmount(groupPayment.getAmount() / groupPayment.getExpenseMembers().size());

						} else {
							expenseMember.setPending(true);
							expenseMember
									.setTotalAmount(groupPayment.getAmount() / groupPayment.getExpenseMembers().size());

						}

					}
				} else {
					while (true) {
						int totalAmount = 0;
						for (ExpenseMember expenseMember : groupPayment.getExpenseMembers()) {
							logger.info(DisplayConstants.ENTER_AMOUNT_FOR + expenseMember.getUserName());
							int amount = in.nextInt();
							if (expenseMember.getuId() == user.getuId()) {
								expenseMember.setPending(false);
								expenseMember.setAmountPaid(amount);
								expenseMember.setTotalAmount(amount);

							} else {
								expenseMember.setPending(true);
								expenseMember.setTotalAmount(amount);
								expenseMember.setAmountPaid(0);

							}
							totalAmount += amount;
						}
						if (totalAmount != groupPayment.getAmount()) {
							logger.info(DisplayConstants.AMOUNT_DOESNT_TALLY);
							continue;
						}
						break;
					}

				}

			} catch (InputMismatchException e) {
				e.printStackTrace();
				in.nextLine();
				logger.info(DisplayConstants.INVALID_INPUT);
			}
			expenseService.addNewExpense(groupPayment);
			expenseService.addExpenseMembers(groupPayment);
			logger.info(DisplayConstants.EXPENSE_ADDED);
			break;

		}
	}

	public void createGroups() {
		while (true) {
			try {
				Group group = new Group();
				logger.info(DisplayConstants.ENTER_GROUP_NAME);
				in.nextLine();
				String groupName = in.nextLine();
				logger.info(DisplayConstants.ENTER_NO_OF_MEMBERS);
				int noOfMembers = in.nextInt();
				if (noOfMembers > 10) {
					logger.info(DisplayConstants.TOO_MANY_MEMBERS);
					continue;
				}
				in.nextLine();
				logger.info(DisplayConstants.ENTER_GROUP_MEMBERS);
				group.setGroupName(groupName);
				group.getUsers().add(activeUser);
				User user;
				while (noOfMembers-- > 0) {
					String userName = in.next();
					user = new User();
					user.setUserName(userName);
					group.getUsers().add(user);
				}

				activeUser.getGroups().add(group);
				expenseService.createGroups(activeUser);

				break;
			} catch (InputMismatchException e) {
				// e.printStackTrace();
				in.nextLine();
				logger.info(DisplayConstants.INVALID_INPUT);

			} catch (CouldNotAddMembersException e) {
				logger.info(DisplayConstants.GROUP_CREATED);
				logger.info(e.getMessage());
				break;
			}
		}
	}

	public void editExpenseLimit() {
		while (true) {
			try {
				logger.info(DisplayConstants.NEW_EXPENSE_LIMIT);
				float expenseLimit = in.nextInt();
				activeUser.setExpenseLimit(expenseLimit);
				expenseService.editExpenseLimit(activeUser);
				logger.info(DisplayConstants.EXPENSE_LIMIT_UPDATED);
				break;
			} catch (InputMismatchException e) {
				logger.info(DisplayConstants.INVALID_INPUT);
				in.nextLine();
			}
		}
	}

	public void addNewExpense(Expense expense, int uId) {
		while (true) {
			try {
				logger.info(DisplayConstants.ENTER_AMOUNT);
				int amount = in.nextInt();
				if (amount < 1) {
					logger.info(DisplayConstants.INVALID_INPUT);
					continue;
				}
				logger.info(DisplayConstants.CATEGORY_TYPES);
				int categoryChoice = in.nextInt();
				int categoryOptionSize = CategoryOptions.values().length;
				if (categoryChoice < 0 || categoryChoice > categoryOptionSize) {
					logger.info(DisplayConstants.ENTER_VALID_CHOICE);
					continue;
				}
				logger.info(DisplayConstants.EXPENSE_TYPES);
				int typeChoice = in.nextInt();
				int expenseOptionSize = ExpenseOptions.values().length;
				if (typeChoice < 0 || typeChoice > expenseOptionSize) {
					logger.info(DisplayConstants.ENTER_VALID_CHOICE);
					continue;
				}
				in.nextLine();
				logger.info(DisplayConstants.ENTER_DESCRIPTION);
				String description = in.nextLine();
				if (description.length() > 100) {
					logger.info(DisplayConstants.ENTER_100_CHARACTERS);
					continue;
				}
				expense.setuId(uId);
				expense.setAmount(amount);
				expense.setCategoryId(categoryChoice);
				expense.setTypeId(typeChoice);
				expense.setDescription(description);
				break;

			} catch (InputMismatchException e) {
				// e.printStackTrace();
				logger.info(DisplayConstants.INVALID_INPUT);
				in.nextLine();

			}
		}

	}

	public void showAllExpenses() {
		activeUser.setExpenses(expenseService.showAllExpense(activeUser).getExpenses());
		int i = 0;
		float groupTotalAmount = 0, personalTotalAmount = 0;
		Expense expense;
		if (activeUser.getExpenses().isEmpty()) {
			logger.info(DisplayConstants.EXPENSES_EMPTY);
			return;
		}
		logger.info(DisplayConstants.GROUP_EXPENSE_HEADING);
		logger.info(DisplayConstants.EXPENSE_HEADING);
		logger.info(DisplayConstants.LINE);
		while (i < activeUser.getExpenses().size()
				&& (expense = activeUser.getExpenses().get(i)) instanceof GroupPayment) {
			logger.info(expense.getAmount() + " " + expense.getDescription() + " " + expense.getCategory() + " "
					+ expense.getType() + " " + expense.getCreatedAt() + " " + expense.getUpdatedAt() + "\n");
			i++;
			groupTotalAmount += expense.getAmount();
		}
		logger.info(DisplayConstants.TOTAL_GROUP_EXPENSE + groupTotalAmount);
		logger.info("\n");
		logger.info(DisplayConstants.PERSONAL_EXPENSE_HEADING);
		logger.info(DisplayConstants.EXPENSE_HEADING);
		logger.info(DisplayConstants.LINE);
		while (i < activeUser.getExpenses().size() && (expense = activeUser.getExpenses().get(i)) instanceof Expense) {
			logger.info(expense.getAmount() + " " + expense.getDescription() + " " + expense.getCategory() + " "
					+ expense.getType() + " " + expense.getCreatedAt() + " " + expense.getUpdatedAt() + "\n");
			i++;
			personalTotalAmount += expense.getAmount();
		}
		logger.info(DisplayConstants.TOTAL_PERSONAL_EXPENSE + personalTotalAmount);
		logger.info("\n");
		logger.info(DisplayConstants.TOTAL_EXPENSE + (groupTotalAmount + personalTotalAmount));
		logger.info("\n");
		float percent = ((groupTotalAmount + personalTotalAmount) / activeUser.getExpenseLimit()) * 100;
		logger.info(DisplayConstants.YOU_HAVE_REACHED + percent + DisplayConstants.PERCENT_OF_LIMIT);
		logger.info(DisplayConstants.EXPENSE_LIMIT + activeUser.getExpenseLimit());
	}

	public void viewBalances() {
		try {
			GroupPayment groupPayment = expenseService.viewBalances(activeGroup);
			for (ExpenseMember member : groupPayment.getExpenseMembers()) {
				logger.info(member.getUserName());
				float amount;
				logger.info(
						((amount = member.getTotalAmount()) < 0 ? DisplayConstants.GETS_BACK : DisplayConstants.OWES)
								+ Math.abs(amount));
				logger.info("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
