package com.zilker.application.beans;

public class MobileNumber {
	String mobileNumber;
	int extension;
	public String getNumber() {
		return this.mobileNumber;
	}
	public int getExtension() {
		return this.extension;
	}
	public void setNumber(String number) {
		this.mobileNumber = number;
	}
	public void setExtension(int ex) {
		this.extension = ex;
	}
}
