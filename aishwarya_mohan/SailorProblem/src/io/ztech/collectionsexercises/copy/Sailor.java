package io.ztech.collectionsexercises.copy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Sailor {
	int id;
	public HashMap<Integer, Integer> map;

	public Sailor(int id) {
		this.id = id;
		this.map = new HashMap<Integer, Integer>();
	}

	public void addElement(int col, int num, HashMap<Integer, Integer> map) {
		if (map.containsKey(col)) {
			map.put(col, map.get(col) + num);
		} else {
			map.put(col, num);
		}
	}

	public int getTotal() {
		int sum = 0;
		for (int i : this.map.keySet()) {
			sum += map.get(i);
		}
		return sum;
	}

	public static void display(ArrayList<Sailor> group) {
		Sailor s;
		Iterator<Sailor> iterate = group.iterator();
		while (iterate.hasNext()) {
			s = iterate.next();

			System.out.println("\nSailor " + s.id);
			for (int elmtID : s.map.keySet()) {
				System.out.println(SailorFruitProblem.getName(elmtID) + s.map.get(elmtID));
			}
		}
	}
}
