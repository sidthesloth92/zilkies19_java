package com.zilker.collections_programs;

import java.util.*;

public class CollectionsProgram1 {
	static Scanner in;
	public static void main(String args[]) {
		in=new Scanner(System.in);
		ArrayList<InputData> listA=new ArrayList<InputData>();
		ArrayList<InputData> listB=new ArrayList<InputData>();
		System.out.println("Enter Group A count");
		int groupACount=in.nextInt();
		System.out.println("Enter Group B count");
		int groupBCount=in.nextInt();
		for(int i=0;i<groupACount;i++) {
			InputData sailor=new InputData();
			sailor.setId(i+1);
			System.out.println("Enter sailor "+(i+1)+" Apple count");
			int appleCount=in.nextInt();
			System.out.println("Enter sailor "+(i+1)+" Mango count");
			int mangoCount=in.nextInt();
			System.out.println("Enter sailor "+(i+1)+" Banana count");
			int bananaCount=in.nextInt();
			sailor.setAppleCount(appleCount);
			sailor.setMangoCount(mangoCount);
			sailor.setBananaCount(bananaCount);
			listA.add(sailor);
		}
		
		for(int i=0;i<groupBCount;i++) {
			InputData sailor=new InputData();
			sailor.setId(i+1);
			System.out.println("Enter sailor "+(i+1)+" Small Fish count");
			int smallFishCount=in.nextInt();
			System.out.println("Enter sailor "+(i+1)+" Big Fish count");
			int bigFishCount=in.nextInt();
			System.out.println("Enter sailor "+(i+1)+" Large Fish count");
			int largeFishCount=in.nextInt();
			sailor.setSmallFishCount(smallFishCount);
			sailor.setBigFishCount(bigFishCount);
			sailor.setLargeFishCount(largeFishCount);
			listB.add(sailor);
		}
		System.out.println("Group A sorted based on apple count");
		Collections.sort(listA, new SortListA());
		Collections.sort(listB, new SortListB());
		for(InputData x:listA) {
			System.out.println("sailor "+x.getId());
		}
		System.out.println("Group B sorted based on small fish count");
		for(InputData x:listB) {
			System.out.println("sailor "+x.getId());
		}
	}
}
