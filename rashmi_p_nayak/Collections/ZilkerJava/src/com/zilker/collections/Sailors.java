package com.zilker.collections;

import java.util.*;

public class Sailors {

	HashMap<Integer, ArrayList<Integer>> groupA = new HashMap<>();
	HashMap<Integer, ArrayList<Integer>> groupB = new HashMap<>();
	static HashMap<Integer, String> fruits = new HashMap<>();
	static HashMap<Integer, String> fish = new HashMap<>();

	public void enterSailorCollection(int x) {
		Scanner in = new Scanner(System.in);
		int n1 = x / 2;
		int n2 = x / 2;
		if (x % 2 != 0)
			n1++;

		for (int i = 0; i < n1; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(Map.Entry mp : fruits.entrySet()) {
				System.out.println("Enter "+mp.getValue()+" collected by Sailor "+(i+1)+":");
				list.add(in.nextInt());
			}
			groupA.put(i, list);
		}

		for (int i = 0; i < n2; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(Map.Entry mp : fish.entrySet()) {
				System.out.println("Enter "+mp.getValue()+" collected by Sailor "+(i+1)+":");
				list.add(in.nextInt());
			}

			groupB.put(i, list);
		}

	}

	public void displaySailorCollection() {

		System.out.println("Group A");
		System.out.println("-------");
		int totSum = 0;
		for (Map.Entry mp : groupA.entrySet()) {
			int id = (int) mp.getKey();
			System.out.println("\nSailor " + id + ":");
			System.out.println("---------");
			ArrayList<Integer> list = (ArrayList<Integer>) mp.getValue();
			int sum = 0;
			for (int i = 0; i < list.size(); i++) {

				System.out.println(fruits.get(i) + ": " + list.get(i));
				sum += list.get(i);

			}
			totSum += sum;
			System.out.println("Total fruits: " + sum);
		}

		System.out.println("\nGroup A Total Collection: " + totSum);

		totSum = 0;
		System.out.println("\nGroup B");
		System.out.println("----------");
		for (Map.Entry mp : groupB.entrySet()) {
			int id = (int) mp.getKey();
			System.out.println("\nSailor " + id + ":");
			System.out.println("---------");
			ArrayList<Integer> list = (ArrayList<Integer>) mp.getValue();
			int sum = 0;
			for (int i = 0; i < list.size(); i++) {
				System.out.println(fish.get(i) + ": " + list.get(i));
				sum += list.get(i);
			}
			totSum += sum;
			System.out.println("Total fish: " + sum);
		}

		System.out.println("\nGroup B Total Collection: " + totSum);
	}

	public static void setNames() {
		fruits.put(0, "apples");
		fruits.put(1, "mangoes");
		fruits.put(2, "oranges");
		
		fish.put(0, "small fish");
		fish.put(1, "big fish");
		fish.put(2, "medium fish");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int choice = 1;
		setNames();
		do {
			System.out.println("Enter no. of sailors:");
			Sailors s = new Sailors();
			s.enterSailorCollection(in.nextInt());
			s.displaySailorCollection();
			System.out.println("\nDo you want to go again (1-yes, any key to ignore): ");
			choice = in.nextInt();
		} while (choice == 1);
	}
}
