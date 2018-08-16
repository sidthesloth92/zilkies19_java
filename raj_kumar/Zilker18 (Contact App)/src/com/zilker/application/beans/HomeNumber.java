package com.zilker.application.beans;

public class HomeNumber {
	int homeNumber;
	int extension;
	public int getNumber() {
		return this.homeNumber;
	}
	public int getExtension() {
		return this.extension;
	}
	public void setNumber(int number) {
		this.homeNumber = number;
	}
	public void setExtension(int ex) {
		this.extension = ex;
	}
}
