package io.ztech.ship.impl;

public class Fish {
	private int smallFishCount, largeFishCount, totalFish;

	public void calcTotalFish() {
		totalFish = smallFishCount + largeFishCount;
	}

	public int getTotalFish() {
		return totalFish;
	}

	public void setTotalFish(int totalFish) {
		this.totalFish = totalFish;
	}

	public int getSmallFishCount() {
		return smallFishCount;
	}

	public void setSmallFishCount(int smallFishCount) {
		this.smallFishCount = smallFishCount;
	}

	public int getLargeFishCount() {
		return largeFishCount;
	}

	public void setLargeFishCount(int largeFishCount) {
		this.largeFishCount = largeFishCount;
	}
}
