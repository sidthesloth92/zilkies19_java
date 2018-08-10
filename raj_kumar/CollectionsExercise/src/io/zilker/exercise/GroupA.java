package io.zilker.exercise;



public class GroupA {
	int apple, mango, banana, totalCount;
	public GroupA(int appleCount, int mangoCount, int bananaCount) {
		this.apple = appleCount;
		this.mango = mangoCount;
		this.banana = bananaCount;
		this.totalCount = appleCount + mangoCount + bananaCount;
	}
}