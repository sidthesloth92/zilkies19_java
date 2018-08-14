package io.ztech.expensesapp.beans;

import java.util.ArrayList;

public class Group {
	int gId;
	String groupName;
	ArrayList<GroupPayment> groupPayments;
	ArrayList<User> users;
	
	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
