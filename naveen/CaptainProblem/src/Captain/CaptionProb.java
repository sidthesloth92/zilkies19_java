package Captain;

import java.util.*;

public class CaptionProb {

	static HashMap<Integer, ArrayList<Integer>> groupA = new HashMap<Integer, ArrayList<Integer>>();
	static HashMap<Integer, ArrayList<Integer>> groupB = new HashMap<Integer, ArrayList<Integer>>();
	static ArrayList<Integer> totalCount = new ArrayList<Integer>();
	static Scanner scan = new Scanner(System.in);
	static int sailors;
	static char option;

	static void getFruitCount(int n) {
		try {
			for (int i = 1; i <= n; i++) {
				ArrayList<Integer> fruits = new ArrayList<Integer>();
				System.out.print("Enter the number of apples collected by Group-A Sailor " + i + ":  ");
				fruits.add(scan.nextInt());
				System.out.print("Enter the number of mangoes collected by Group-A Sailor " + i + ":  ");
				fruits.add(scan.nextInt());
				System.out.print("Enter the number of bananas collected by Group-A Sailor " + i + ":  ");
				fruits.add(scan.nextInt());
				groupA.put(i, fruits);
				if (totalCount.size() < 3) {
					totalCount.add(fruits.get(0));
					totalCount.add(fruits.get(1));
					totalCount.add(fruits.get(2));
				} else {
					totalCount.set(0, totalCount.get(0) + fruits.get(0));
					totalCount.set(1, totalCount.get(1) + fruits.get(1));
					totalCount.set(2, totalCount.get(2) + fruits.get(2));
				}
			}
		} catch (Exception e) {
			System.out.println("Incorrect Format");
		}
	}

	static void getFishCount(int n) {
		try {
			for (int i = 1; i <= n; i++) {
				ArrayList<Integer> fish = new ArrayList<Integer>();
				System.out.print("Enter the number of small fish collected by Group-B Sailor " + i + ":  ");
				fish.add(scan.nextInt());
				System.out.print("Enter the number of big fish collected by Group-B Sailor " + i + ":  ");
				fish.add(scan.nextInt());
				groupB.put(i, fish);
				if (totalCount.size() < 5) {
					totalCount.add(fish.get(0));
					totalCount.add(fish.get(1));
				} else {
					totalCount.set(3, totalCount.get(0) + fish.get(0));
					totalCount.set(4, totalCount.get(1) + fish.get(1));
				}
			}
		} catch (Exception e) {
			System.out.println("Incorrect Format");
		}

	}

	static void printAllSailors() {
		for (int i = 1; i <= sailors; i++) {
			ArrayList<Integer> items = new ArrayList<Integer>();
			if (i <= sailors / 2)
				items = groupA.get(i);
			else
				items = groupB.get(i - sailors / 2);
			if (items.size() == 3)
				System.out.println("sailor " + i + " collected " + items.get(0) + " apples " + items.get(1)
						+ " mangoes " + items.get(2) + " bananas");
			else
				System.out.println(
						"sailor " + i + " collected " + items.get(0) + " small fish " + items.get(1) + " big fish ");
		}
	}

	static void printIndividualItemsCount() {
		System.out.println("Total apples collected :  " + totalCount.get(0));
		System.out.println("Total mangoes collected :  " + totalCount.get(1));
		System.out.println("Total bananas collected :  " + totalCount.get(2));
		System.out.println("Total big fish collected :  " + totalCount.get(3));
		System.out.println("Total small fish collected :  " + totalCount.get(4));
	}

	static void printIndividualSailor(int sailorID) {
		ArrayList<Integer> items = new ArrayList<Integer>();
		if (sailorID <= sailors / 2)
			items = groupA.get(sailorID);
		else
			items = groupB.get(sailorID - sailors / 2);
		if (items.size() == 3)
			System.out.println("sailor " + sailorID + " collected " + items.get(0) + " apples " + items.get(1)
					+ " mangoes " + items.get(2) + " bananas");
		else
			System.out.println(
					"sailor " + sailorID + " collected " + items.get(0) + " small fish " + items.get(1) + " big fish ");

	}

	static void askIndividualSailorCollection() {
		option = 'y';
		try {
			while (option == 'y') {
				scan.nextLine();
				System.out.print("Do you want a specific Sailor collections (y for yes/n for no) :  ");
				option = scan.nextLine().charAt(0);
				System.out.print("Enter the Sailor ID :  ");
				int id = scan.nextInt();
				printIndividualSailor(id);
			}
		} catch (Exception e) {
			System.out.println("Oops Wrong Input");
		}
	}

	public static void main(String[] args) {
		System.out.print("Enter the total number of Sailors : ");
		sailors = scan.nextInt();
		getFruitCount(sailors / 2);
		getFishCount(sailors - sailors / 2);
		printAllSailors();
		askIndividualSailorCollection();
	}

}
