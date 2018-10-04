package io.ztech.dao;


import java.sql.*;
import java.sql.DriverManager;
import io.ztech.employee.Employee;

public class EmployeeDao {
	
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeSystem?autoReconnect=true&useSSL=false", "root", "Ztech@123");
			
		} catch (Exception e) {
			System.out.println(e);
		} 
		return conn;
	}
	
	public void insertIntoTable(String name, String designation, String manager) {
		Connection conn = this.connect();
		if(conn != null) {
			try {
				PreparedStatement query = conn.prepareStatement("INSERT INTO Employee(Name, Designation, Manager) VALUES (?, ?, ?)");
				query.setString(1, name);
				query.setString(2, designation);
				query.setString(3, manager);
				query.executeUpdate();
				conn.close();
			} catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public Employee[] getFromTable() {
		Employee[] employees = null;
		Connection conn = this.connect();
		if(conn != null) {
			try {
				PreparedStatement query = conn.prepareStatement("SELECT * FROM Employee");
				ResultSet rs = query.executeQuery();
				rs.last();
				employees = new Employee[rs.getRow()];
				rs.beforeFirst();
				int index = 0;
				while(rs.next()) {
					employees[index] = new Employee();
					employees[index].setId(rs.getInt("Id"));
					employees[index].setName(rs.getString("Name"));
					employees[index].setDesignation(rs.getString("Designation"));
					employees[index].setManager(rs.getString("Manager"));
					index++;
				}
				conn.close();
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		return employees;
	}
	
}

