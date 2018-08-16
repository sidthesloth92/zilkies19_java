package io.ztech.jkingsley.captainofship;

public class GroupA {
	int applesCollected;
	int mangoesCollected;
	int bananasCollected;
	public int getApplesCollected() {
		return applesCollected;
	}
	public void setApplesCollected(int applesCollected) {
		this.applesCollected = applesCollected;
	}
	public int getMangoesCollected() {
		return mangoesCollected;
	}
	public void setMangoesCollected(int mangoesCollected) {
		this.mangoesCollected = mangoesCollected;
	}
	public int getBananasCollected() {
		return bananasCollected;
	}
	public void setBananasCollected(int bananasCollected) {
		this.bananasCollected = bananasCollected;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "apples:" + applesCollected + " mangoes:" + mangoesCollected + " bananas:" + bananasCollected;
	}
	
	
}
