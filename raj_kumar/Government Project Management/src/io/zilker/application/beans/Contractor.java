package io.zilker.application.beans;

public class Contractor {
	private long annualRevenue;
	private String email, firstName, lastName, company, location, username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String password;
	public int contrId, noOfClient;

	public int getContrId() {
		return contrId;
	}

	public void setContrId(int contrId) {
		this.contrId = contrId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getAnnualRevenue() {
		return annualRevenue;
	}

	public void setAnnualRevenue(long annualRevenue) {
		this.annualRevenue = annualRevenue;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getNoOfClient() {
		return noOfClient;
	}

	public void setNoOfClient(int noOfClient) {
		this.noOfClient = noOfClient;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
