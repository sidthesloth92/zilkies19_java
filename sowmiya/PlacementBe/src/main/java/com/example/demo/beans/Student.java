package com.example.demo.beans;

public class Student {
	private String name;
	private int placed;
	private String studentId, department;
	private float cgpa, markX, markXII;
	private int arrearCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlaced() {
		return placed;
	}

	public void setPlaced(int placed) {
		this.placed = placed;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public float getCgpa() {
		return cgpa;
	}

	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}

	public float getMarkX() {
		return markX;
	}

	public void setMarkX(float markX) {
		this.markX = markX;
	}

	public float getMarkXII() {
		return markXII;
	}

	public void setMarkXII(float markXII) {
		this.markXII = markXII;
	}

	public int getArrearCount() {
		return arrearCount;
	}

	public void setArrearCount(int arrearCount) {
		this.arrearCount = arrearCount;
	}

}
