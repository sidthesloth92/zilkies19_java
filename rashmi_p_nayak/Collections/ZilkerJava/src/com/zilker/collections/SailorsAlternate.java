package com.zilker.collections;

import java.util.*;

public class SailorsAlternate {

	HashMap<Integer, HashMap<String, Integer>> groupA = new HashMap<>();
	HashMap<Integer, HashMap<String, Integer>> groupB = new HashMap<>();

	ArrayList<String> fruits = new ArrayList<String>();
	ArrayList<String> fish = new ArrayList<String>();

	static Scanner in = new Scanner(System.in);

	public void setNames(int fruitCount, int fishCount) {
		for (int i = 0; i < fruitCount; i++) {
			System.out.println("Enter fruit name:");
			fruits.add(in.next());
		}

		for (int i = 0; i < fishCount; i++) {
			System.out.println("Enter fish name:");
			fish.add(in.next());
		}
	}

	public void enterSailorCollection(int numOfSailors) {

		int n1 = numOfSailors / 2;
		int n2 = numOfSailors / 2;
		if (numOfSailors % 2 != 0)
			n1++;

		for (int i = 0; i < n1; i++) {
			HashMap<String, Integer> map = new HashMap<>();
			for (String fruit : fruits) {
				System.out.println("Enter " + fruit + " collected by Sailor " + (i + 1) + " in group A:");
				map.put(fruit, in.nextInt());
			}
			groupA.put(i, map);
		}

		for (int i = 0; i < n2; i++) {
			HashMap<String, Integer> map = new HashMap<>();
			for (String fish : fish) {
				System.out.println("Enter " + fish + " collected by Sailor " + (i + 1) + " in group B:");
				map.put(fish, in.nextInt());
			}
			groupB.put(i, map);
		}

	}

	public void displaySailorCollection() {

		
		HashMap<Integer,HashMap<String,Integer>>groupAs = sortByCollection(groupA);
		HashMap<Integer,HashMap<String,Integer>>groupBs = sortByCollection(groupB);
		System.out.println("In display!");
		System.out.println("Group A");
		System.out.println("-------");
		int totSum = 0;
		for (Map.Entry mp : groupAs.entrySet()) {
			int id = (int) mp.getKey();
			System.out.println("\nSailor " + id + ":");
			System.out.println("---------");
			HashMap<String, Integer> map = (HashMap<String, Integer>) mp.getValue();
			int sum = 0;
			for (Map.Entry m : map.entrySet()) {
				int count = (int) m.getValue();
				System.out.println((String) m.getKey() + ": " + count);
				sum += count;
			}
			totSum += sum;
			System.out.println("Total fruits: " + sum);
		}

		System.out.println("\nGroup A Total Collection: " + totSum);

		totSum = 0;
		System.out.println("\nGroup B");
		System.out.println("----------");
		for (Map.Entry mp : groupBs.entrySet()) {
			int id = (int) mp.getKey();
			System.out.println("\nSailor " + id + ":");
			System.out.println("---------");
			HashMap<String, Integer> map = (HashMap<String, Integer>) mp.getValue();
			int sum = 0;
			for (Map.Entry m : map.entrySet()) {
				int count = (int) m.getValue();
				System.out.println((String) m.getKey() + ": " + count);
				sum += count;
			}
			totSum += sum;
			System.out.println("Total fish: " + sum);
		}

		System.out.println("\nGroup B Total Collection: " + totSum);
	}

	@SuppressWarnings("unchecked")
	public HashMap sortByCollection(HashMap map) {
		System.out.println("In sort Method!");
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new collectionComparator());
		HashMap<Integer, HashMap<String,Integer>> sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put((Integer) entry.getKey(), (HashMap<String,Integer>) entry.getValue());
		}
		return sortedMap;

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int choice = 1;
		do {
			SailorsAlternate s = new SailorsAlternate();
			System.out.println("Enter no.of fruits:");
			int fruitCount = in.nextInt();
			System.out.println("Enter no.of fish:");
			int fishCount = in.nextInt();
			s.setNames(fruitCount, fishCount);
			System.out.println("Enter no. of sailors:");
			s.enterSailorCollection(in.nextInt());
			s.displaySailorCollection();
			System.out.println("\nDo you want to go again (1-yes, any key to ignore): ");
			choice = in.nextInt();
		} while (choice == 1);
	}

}

class collectionComparator implements Comparator{
	public int compare(Object ob1, Object ob2) {
		int sum1=0;
		HashMap<String, Integer> map1 = (HashMap)((Map.Entry)ob1).getValue();
		for (Map.Entry mp : map1.entrySet()) {
			sum1 += (int) mp.getValue();
		}
		System.out.println("Sum1:"+sum1);
		int sum2 = 0;
		HashMap<String, Integer> map2 = (HashMap)((Map.Entry)ob2).getValue();
		for (Map.Entry mp : map2.entrySet()) {
			sum2 += (int) mp.getValue();
		}
		System.out.println("Sum2:"+sum2);
		return sum1-sum2;
	}
	
	
}