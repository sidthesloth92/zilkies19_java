package io.ztech.fitnessapplication.beans;

public class UserStats {

	private float height, weight, bmi;
	private int age, bmr, regID, activityLevel, dailyTarget;
	public int getDailyTarget() {
		return dailyTarget;
	}

	public void setDailyTarget(int dailyTarget) {
		this.dailyTarget = dailyTarget;
	}

	private char gender;

	public int getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(int activityLevel) {
		this.activityLevel = activityLevel;
	}

	public int getRegID() {
		return regID;
	}

	public void setRegID(int regID) {
		this.regID = regID;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getBmr() {
		return bmr;
	}

	public void setBmr(int bmr) {
		this.bmr = bmr;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
}
