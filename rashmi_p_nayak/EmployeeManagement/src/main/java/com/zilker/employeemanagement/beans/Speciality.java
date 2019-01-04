package com.zilker.employeemanagement.beans;

import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "speciality")
public class Speciality {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
//	@ManyToMany
//	private HashSet<Employee> employeeSet;
//	
// 	public HashSet<Employee> getEmployeeSet() {
//		return employeeSet;
//	}
//
//	public void setEmployeeSet(HashSet<Employee> employeeSet) {
//		this.employeeSet = employeeSet;
//	}

	protected Speciality() {}

	public Speciality(String name) {
		super();
		this.name = name;
	}
	
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

	@Override
	public String toString() {
		return String.format("Speciality[id = %d, name = %s]", id, name);	
	}
}
