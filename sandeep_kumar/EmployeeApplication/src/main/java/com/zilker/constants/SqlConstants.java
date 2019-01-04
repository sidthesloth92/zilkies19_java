package com.zilker.constants;

public class SqlConstants {
	public static final String INSERT_EMPLOYEE="INSERT INTO employee VALUES(DEFAULT,?,?,?,?,?)";
	public static final String UPDATE_EMPLOYEE="UPDATE employee SET firstname=?, lastname=?, email=?, phone_number=?, salary=? WHERE id=?";
	public static final String DELETE_EMPLOYEE="DELETE FROM employee WHERE id=?";
	public static final String GET_ALL_EMPLOYEES="SELECT * FROM employee";
	public static final String GET_EMPLOYEE_BY_ID="SELECT * FROM employee WHERE id=?";
}
