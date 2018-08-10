package io.ztech.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SailorProblem {
	
	private static HashMap<Integer, ArrayList<Integer>> groupA = new HashMap<>();
	private static HashMap<Integer, ArrayList<Integer>> groupB = new HashMap<>();
	
	public void getCount(int numberOfSailors) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < numberOfSailors/2; i++) {
			System.out.println("\nSailor " + i + ":");
			int total = 0;
			ArrayList<Integer> list = new ArrayList<>();
			System.out.print("\nEnter the number of apples you have found: ");
			int apples = sc.nextInt();
			total += apples;
			list.add(apples);
			System.out.print("Enter the number of oranges you have found: ");
			int oranges = sc.nextInt();
			total += oranges;
			list.add(oranges);
			System.out.print("Enter the number of mangos you have found: ");
			int mangoes = sc.nextInt();
			total += mangoes;
			list.add(mangoes);
			list.add(total);
			groupA.put(i, list);
		}
		for (int i = numberOfSailors/2; i < numberOfSailors; i++) {
			System.out.println("\nSailor " + i + ":");
			int total = 0;
			ArrayList<Integer> list = new ArrayList<>();
			System.out.print("\nEnter the number of large fish you have found: ");
			int largeFish = sc.nextInt();
			total += largeFish;
			list.add(largeFish);
			System.out.print("Enter the number of small fish you have found: ");
			int smallFish = sc.nextInt();
			total += smallFish;
			list.add(smallFish);
			list.add(total);
			groupB.put(i, list);
		}		
	}
	
	public void printCount() {
		int totalApples = 0, totalOranges = 0, totalMangoes = 0, totalFruits = 0;
		int totalLargeFish = 0, totalSmallFish = 0, totalFish = 0;
		System.out.println("\nGroup A:");
		for (Map.Entry<Integer, ArrayList<Integer>> m : groupA.entrySet()) {
			ArrayList<Integer> list = m.getValue();
			System.out.println("\nSailor " + m.getKey() + ":");
			System.out.println("Number of apples: " + list.get(0));
			System.out.println("Number of oranges: " + list.get(1));
			System.out.println("Number of mangoes: " + list.get(2));
			System.out.println("Total fruits collected by sailor " + m.getKey() + " = " + list.get(3));
			totalApples += list.get(0);
			totalOranges += list.get(1);
			totalMangoes += list.get(2);
			totalFruits += list.get(3);
		}
		System.out.println("\nGroup B:");
		for (Map.Entry<Integer, ArrayList<Integer>> m : groupB.entrySet()) {
			ArrayList<Integer> list = m.getValue();
			System.out.println("\nSailor " + m.getKey() + ":");
			System.out.println("Number of large fish: " + list.get(0));
			System.out.println("Number of small fish: " + list.get(1));
			System.out.println("Total fish collected by sailor " + m.getKey() + " = " + list.get(2));
			totalLargeFish += list.get(0);
			totalSmallFish += list.get(1);
			totalFish += list.get(2);
		}
		System.out.println("\nTotal number of apples collected = " + totalApples);
		System.out.println("Total number of oranges collected = " + totalOranges);
		System.out.println("Total number of mangoes collected = " + totalMangoes);
		System.out.println("Total number of large fish collected = " + totalLargeFish);
		System.out.println("Total number of small fish collected = " + totalSmallFish);
		System.out.println("\nTotal number of fruits collected = " + totalFruits);
		System.out.println("Total number of fish collected = " + totalFish);		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the total number of sailors: ");
		int numberOfSailors = sc.nextInt();
		SailorProblem sp = new SailorProblem();
		sp.getCount(numberOfSailors);
		sp.printCount();
	}
}
