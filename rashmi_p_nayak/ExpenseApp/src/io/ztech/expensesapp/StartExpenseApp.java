package io.ztech.expensesapp;

import io.ztech.expensesapp.ui.ExpenseManager;

public class StartExpenseApp {
	ExpenseManager expenseManager;

	public StartExpenseApp() {
		expenseManager = new ExpenseManager();
		
	}

	public static void main(String[] args) {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");
		StartExpenseApp startExpenseApp = new StartExpenseApp();
		startExpenseApp.expenseManager.startUpMenu();
	}
}
