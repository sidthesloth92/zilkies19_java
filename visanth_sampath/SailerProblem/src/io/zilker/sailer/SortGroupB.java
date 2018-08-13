package io.zilker.sailer;

import java.util.Comparator;

public class SortGroupB implements Comparator<GroupB>{

	@Override
	public int compare(GroupB personOne, GroupB personTwo) {
		// TODO Auto-generated method stub
		int personOneTotal = personOne.numberOfBigFish+personOne.numberOfSmallFish;
		int personTwoTotal= personTwo.numberOfBigFish+personTwo.numberOfSmallFish;
		return personOneTotal- personTwoTotal;
	}

}
