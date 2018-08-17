package com.zilker.application.beans;

public class OfficeNumber {
	int officeNumber;
	int extension;
	public int getNumber() {
		return this.officeNumber;
	}
	public int getExtension() {
		return this.extension;
	}
	public void setNumber(int number) {
		this.officeNumber = number;
	}
	public void setExtension(int ex) {
		this.extension = ex;
	}
}
