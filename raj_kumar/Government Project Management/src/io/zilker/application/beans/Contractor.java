package io.zilker.application.beans;

public class Contractor {
	public AnnualRevenue revenue;
	public ContractorCompany company;
	public ContractorName name;
	public NoOfClient client;
	public String email;
	public String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Contractor() {
		revenue = new AnnualRevenue();
		company = new ContractorCompany();
		name = new ContractorName();
		client = new NoOfClient();
	}
}
