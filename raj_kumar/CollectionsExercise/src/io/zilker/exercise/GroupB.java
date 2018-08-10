package io.zilker.exercise;

public class GroupB {
	int small, big, large, totalCount;
	public GroupB(int smallCount, int bigCount, int largeCount) {
		this.small = smallCount;
		this.big = bigCount;
		this.large = largeCount;
		this.totalCount = smallCount + bigCount + largeCount;
	}
}
