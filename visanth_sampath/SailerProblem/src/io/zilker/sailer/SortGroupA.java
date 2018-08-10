package io.zilker.sailer;

import java.util.Comparator;

public class SortGroupA implements Comparator<GroupA>{

	@Override
	public int compare(GroupA personOne, GroupA personTwo) {
		// TODO Auto-generated method stub
		int personOneTotal = personOne.numberOfApples + personOne.numberOfBanana+personOne.numberOfMangoes;
		int personTwoTotal = personTwo.numberOfApples + personTwo.numberOfBanana+personTwo.numberOfMangoes;
		return personOneTotal-personTwoTotal;
	}

}
