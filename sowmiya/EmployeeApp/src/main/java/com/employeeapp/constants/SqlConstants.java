package com.employeeapp.constants;

public class SqlConstants {
	public static final String GET_ALL_EMPLOYEE = "select * from employee";
	public static final String GET_EMPLOYEE = "select * from employee where id=?";
	public static final String ADD_EMPLOYEE = "insert into employee(firstname,lastname,email,phone_number,salary) values(?,?,?,?,?)";
	public static final String DELETE_EMPLOYEE = "delete from employee where id=?";
	public static final String UPDATE_EMPLOYEE = "update employee set firstname=?,lastname=?,email=?,phone_number=?,salary=? where id=?";
	public static final String URL = "jdbc:mysql://localhost:3306/employee_db?useSSL=false";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	public static final String MYSQL_DRIVER = "jdbc:mysql://localhost:3306/employee_db?useSSL=false";
}
