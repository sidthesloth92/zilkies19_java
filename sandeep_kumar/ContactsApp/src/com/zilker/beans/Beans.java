package com.zilker.beans;

import java.util.ArrayList;
import java.util.Scanner;

public class Beans extends Validation {
	protected static Scanner in=new Scanner(System.in);
	private String fname;
	private String lname;
	private ArrayList<String> office = new ArrayList<String>();
	private ArrayList<String> home = new ArrayList<String>();
	private ArrayList<String> mobile = new ArrayList<String>();
	private ArrayList<String> email = new ArrayList<String>();
	
	public String getFname() {
		return this.fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return this.lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public ArrayList<String> getOffice() {
		return this.office;
	}
	public void setOffice(ArrayList<String> office) {
		this.office = office;
	}
	public ArrayList<String> getHome() {
		return this.home;
	}
	public void setHome(ArrayList<String> home) {
		this.home = home;
	}
	public ArrayList<String> getMobile() {
		return this.mobile;
	}
	public void setMobile(ArrayList<String> mobile) {
		this.mobile = mobile;
	}
	public ArrayList<String> getEmail() {
		return this.email;
	}
	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}
}
