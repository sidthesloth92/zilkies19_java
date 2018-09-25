package io.zilker.application.beans;

import java.util.ArrayList;

public class ControllerResponse {
	int status;
	ArrayList<Object> arrayList = new ArrayList<Object>();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<Object> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<Object> arrayList) {
		this.arrayList = arrayList;
	}
}
