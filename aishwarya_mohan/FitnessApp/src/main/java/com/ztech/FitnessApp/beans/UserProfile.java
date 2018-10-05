package com.ztech.FitnessApp.beans;

public class UserProfile {
	int gender, age, lifestyle, target;
	float height, weight, bmi, bmr, tdee;
	String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getLifestyle() {
		return lifestyle;
	}

	public void setLifestyle(int lifestyle) {
		this.lifestyle = lifestyle;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getBmi() {
		return bmi;
	}

	public void setBmi(float bmi) {
		this.bmi = bmi;
	}

	public float getBmr() {
		return bmr;
	}

	public void setBmr(float bmr) {
		this.bmr = bmr;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public float getTdee() {
		return tdee;
	}

	public void setTdee(float tdee) {
		this.tdee = tdee;
	}

}
