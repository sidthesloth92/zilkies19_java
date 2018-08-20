package io.ztech.expensesapp.beans;

import java.util.ArrayList;

public class GroupPayment extends Expense {
	private int paymentId;
	private int gId;
	private ArrayList<ExpenseMember> expenseMembers;

	public GroupPayment() {
		super();
		expenseMembers = new ArrayList<ExpenseMember>();
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public ArrayList<ExpenseMember> getExpenseMembers() {
		return expenseMembers;
	}

	public void setExpenseMembers(ArrayList<ExpenseMember> expenseMembers) {
		this.expenseMembers = expenseMembers;
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

}
