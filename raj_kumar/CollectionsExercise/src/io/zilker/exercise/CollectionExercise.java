package io.zilker.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class CollectionExercise {
	public static void main(String args[]) {
		int sumOfGroupA = 0, sumOfGroupB = 0;
		int fruit1, fruit2, fruit3;
		ArrayList<Integer> aList = new ArrayList<Integer>();
		ArrayList<Integer> bList = new ArrayList<Integer>();
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the Number of Sailors");
		int numOfSailors = in.nextInt();
		HashMap<Integer, String> fruitType = new HashMap<>();
		HashMap<Integer, String> fishType = new HashMap<>();
		
		for(int fruitCounter = 0;fruitCounter < 3;fruitCounter++) {
			System.out.println("Enter the Type of Fruit "+fruitCounter);
			String fruitName = in.nextLine();
			fruitType.put(fruitCounter, fruitName);
		}
		
		for(int fishCounter = 0;fishCounter < 3;fishCounter++) {
			System.out.println("Enter the Type of Fish "+fishCounter);
			String fishName = in.nextLine();
			fishType.put(fishCounter, fishName);
		}
		
		HashMap<Integer, GroupA> groupA = new HashMap<>();
		HashMap<Integer, GroupB> groupB = new HashMap<>();
		SortGroupA sortA = new SortGroupA(groupA);
		SortGroupB sortB = new SortGroupB(groupB);
		TreeMap<Integer, GroupA> sorted_map = new TreeMap<Integer, GroupA>(sortA);
		TreeMap<Integer, GroupB> sorted_mapB = new TreeMap<Integer, GroupB>(sortB);
		
		for(int i = 0;i < numOfSailors / 2;i++) {
			System.out.println("Enter Fruit Count for Sailor "+(i + 1)+" Group A");
			fruit1 = in.nextInt();
			fruit2 = in.nextInt();
			fruit3 = in.nextInt();
			groupA.put(i, new GroupA(fruit1, fruit2, fruit3));
		}
		
		
		
		for(int i = 0;i < numOfSailors / 2;i++) {
			System.out.println("Enter Fruit Count for Sailor "+(i + 1)+" Group B");
			fruit1 = in.nextInt();
			fruit2 = in.nextInt();
			fruit3 = in.nextInt();
			groupB.put(i, new GroupB(fruit1, fruit2, fruit3));
		}
		sorted_map.putAll(groupA);
		sorted_mapB.putAll(groupB);
		System.out.println("Sailors from Group A");
		for (Entry<Integer, GroupA> entry : sorted_map.entrySet()) {
		     System.out.println("Sailor ID: " + entry.getKey() + ". Total Fruits Collected: " + entry.getValue().totalCount);
		}
		System.out.println("Sailors from Group B");
		for (Entry<Integer, GroupB> entry : sorted_mapB.entrySet()) {
		     System.out.println("Sailor ID: " + entry.getKey() + ". Total Fish Collected: " + entry.getValue().totalCount);
		}
		
		
		for(int i = 0;i < numOfSailors / 2;i++) {
			sumOfGroupA += groupA.get(i).apple;
			sumOfGroupA += groupA.get(i).banana;
			sumOfGroupA += groupA.get(i).mango;
			aList.add(sumOfGroupA);
		}
		for(int i = 0;i < numOfSailors / 2 ;i++) {
			sumOfGroupB += groupB.get(i).big;
			sumOfGroupB += groupB.get(i).large;
			sumOfGroupB += groupB.get(i).small;
			bList.add(sumOfGroupB);
		}
		System.out.println("");
		System.out.println("The Total number of Fruits Collected by Group A");
		System.out.println(sumOfGroupA);
		
		System.out.println("The Total number of Fruits Collected by Group B");
		System.out.println(sumOfGroupB);
		in.close();
		
		
		
	}
}
