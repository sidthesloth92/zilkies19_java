package com.zilker.constants;

public class DatabaseConstants {

	public final static String ADD_EMPLOYEE = "INSERT INTO employee(firstname, lastname, email, phone_number, salary) VALUES(?, ?, ?, ?, ?)";
	public final static String GET_EMPLOYEE_USERS = "SELECT * FROM employee";
	public final static String GET_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id = ?";
	public final static String UPDATE_EMPLOYEE_BY_ID = "UPDATE employee set firstname = ?, lastname = ?, email = ?, phone_number = ?, salary = ? where id = ?";
	public final static String DELETE_EMPLOYEE_BY_ID = "UPDATE employee set status = 0 WHERE id = ?";
	
	// column names
	public final static String FIRST_NAME = "firstname";
	public final static String LAST_NAME = "lastname";
	public final static String EMAIL = "email";
	public final static String PHONE_NUMBER = "phone_number";
	public final static String SALARY = "salary";
}
