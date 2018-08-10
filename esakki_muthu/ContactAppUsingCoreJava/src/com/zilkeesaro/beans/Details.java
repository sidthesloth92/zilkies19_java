package com.zilkeesaro.beans;

import java.util.Scanner;

public class Details {

	public String home, office;

	public String e_mail;

	public String mobile_number;

	public String first_name;

	public String last_name;

	Scanner in = new Scanner(System.in);

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getOffice() {

		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

}
