package com.zilker.employeemanagement.beans;

import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	
	private String email;
	
	@Column(name = "phone_number")
	private long phoneNumber;
	
	private long salary;
	
	@Column(name = "department_id")
	private int departmentId;
	
	@Column(name = "address_id")
	private int addressId;
	
//	@OneToOne
//	private Address address;
//	
//	@ManyToOne
//	private Department department;
//	
//	@ManyToMany
//	private HashSet<Speciality> specialitySet;
//	
//	public Department getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(Department department) {
//		this.department = department;
//	}
//
//	public HashSet<Speciality> getSpecialitySet() {
//		return specialitySet;
//	}
//
//	public void setSpecialitySet(HashSet<Speciality> specialitySet) {
//		this.specialitySet = specialitySet;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}

	protected Employee() {}

	public Employee(String firstName, String lastName, String email, long phoneNumber, long salary, int departmentId,
			int addressId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
		this.departmentId = departmentId;
		this.addressId = addressId;
	}
	
	@Override
	public String toString() {
		return String.format("Employee[id = %d, firstName = %s, lastName = %s, email = %s, phoneNumber = %ld, salary = %ld, departmentId = %d, addressId = %d ]", id, firstName, lastName, email, phoneNumber, salary, departmentId, addressId);
	}
}
