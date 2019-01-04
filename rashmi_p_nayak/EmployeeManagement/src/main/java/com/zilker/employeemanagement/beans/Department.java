package com.zilker.employeemanagement.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
//	@OneToMany
//	private List<Employee> employeeList;
//
//	public List<Employee> getEmployeeList() {
//		return employeeList;
//	}
//
//	public void setEmployeeList(List<Employee> employeeList) {
//		this.employeeList = employeeList;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected Department() {
	}

	public Department(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Department[id = %d, name = %s]", id, name);
	}
}
