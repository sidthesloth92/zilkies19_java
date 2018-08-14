package io.ztech.expensesapp.beans;

public class ExpenseMember extends User {
	boolean pending;
	float amount;

	

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
