package io.ztech.placementportal.bean;

public class Eligiblity {
	int arrear_count;
	float cgpa,Mark_X,Mark_XII;
	String dept;
	public float getMark_X() {
		return Mark_X;
	}
	public void setMark_X(float f) {
		Mark_X = f;
	}
	public float getMark_XII() {
		return Mark_XII;
	}
	public void setMark_XII(float mark_XII) {
		Mark_XII = mark_XII;
	}
	public int getArrear_count() {
		return arrear_count;
	}
	public void setArrear_count(int arrear_count) {
		this.arrear_count = arrear_count;
	}
	public float getCgpa() {
		return cgpa;
	}
	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}


}
