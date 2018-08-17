package io.ztech.ship.impl;

public class Fruits {
	private int appleCount;
	private int bananaCount;
	private int mangoCount;
	private int totalFruits;
	
	public void calcTotalFruits() {
		totalFruits=appleCount+bananaCount+mangoCount;
	}

	public int getAppleCount() {
		return appleCount;
	}

	public int getTotalFruits() {
		return totalFruits;
	}

	public void setTotalFruits(int totalFruits) {
		this.totalFruits = totalFruits;
	}

	public void setAppleCount(int appleCount) {
		this.appleCount = appleCount;
	}

	public int getBananaCount() {
		return bananaCount;
	}

	public void setBananaCount(int bananaCount) {
		this.bananaCount = bananaCount;
	}

	public int getMangoCount() {
		return mangoCount;
	}

	public void setMangoCount(int mangoCount) {
		this.mangoCount = mangoCount;
	}
}
