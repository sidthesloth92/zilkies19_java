package io.ztech.jkingsley.captainofship;

public class GroupB {
	int smallFishCollected;
	int mediumFishCollected;
	int bigFishCollected;
	
	public int getSmallFishCollected() {
		return smallFishCollected;
	}
	public void setSmallFishCollected(int smallFishCollected) {
		this.smallFishCollected = smallFishCollected;
	}
	public int getMediumFishCollected() {
		return mediumFishCollected;
	}
	public void setMediumFishCollected(int mediumFishCollected) {
		this.mediumFishCollected = mediumFishCollected;
	}
	public int getBigFishCollected() {
		return bigFishCollected;
	}
	public void setBigFishCollected(int bigFishCollected) {
		this.bigFishCollected = bigFishCollected;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "small:" + smallFishCollected + " medium:" + mediumFishCollected + " large:" + bigFishCollected;
	}
	
	

}
