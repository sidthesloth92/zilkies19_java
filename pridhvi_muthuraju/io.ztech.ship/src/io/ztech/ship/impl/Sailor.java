package io.ztech.ship.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class Sailor {

	private static final Logger logger = Logger.getLogger(Sailor.class.getName());
	private static final Scanner sc = new Scanner(System.in);
	private static int totalSailors, groupACount, groupBCount;
	HashMap<Integer, Fruits> sailorsA = new HashMap<>();
	HashMap<Integer, Fish> sailorsB = new HashMap<>();

	Sailor() throws IOException {

		FileReader fileInput = new FileReader("/home/pridhvi/Desktop/testinput");
		int i;
		String readFile = "";
		while ((i = fileInput.read()) != -1) {
			readFile += (char) i;
		}
		String input[] = readFile.split(",");

		// setting total sailor count
		getTotalCounts(Integer.parseInt(input[0]));

		// splitting sailors into groups
		initializeSailors();
		i = 1;

		// setting collection of sailors in group A
		for (Map.Entry<Integer, Fruits> entry : sailorsA.entrySet()) {
			entry.getValue().setAppleCount(Integer.parseInt(input[(i++)]));
			entry.getValue().setMangoCount(Integer.parseInt(input[(i++)]));
			entry.getValue().setBananaCount(Integer.parseInt(input[(i++)]));
		}

		// setting collection of sailors in group B
		for (Map.Entry<Integer, Fish> entry : sailorsB.entrySet()) {
			entry.getValue().setSmallFishCount(Integer.parseInt(input[(i++)]));
			entry.getValue().setLargeFishCount(Integer.parseInt(input[(i++)]));
		}

	}

	public static void getTotalCounts(int totalSailors) {
		Sailor.totalSailors = totalSailors;
	}

	public void initializeSailors() {
		groupACount = totalSailors / 2;
		groupBCount = totalSailors - groupACount;

		for (int i = 1; i <= groupACount; i++) {
			sailorsA.put(i, new Fruits());
		}
		for (int i = 1; i <= groupBCount; i++) {
			sailorsB.put(i, new Fish());
		}
	}

	public boolean getSailorCollectionA(int sailorId) {
		Fruits f = sailorsA.get(sailorId);
		if (f == null)
			return false;
		logger.info("Apple Count of Sailor " + sailorId + ":" + f.getAppleCount());
		logger.info("Banana Countof Sailor " + sailorId + ":" + f.getBananaCount());
		logger.info("Mango Countof Sailor " + sailorId + ":" + f.getMangoCount());
		return true;
	}

	public boolean getSailorCollectionB(int sailorId) {
		Fish f = sailorsB.get(sailorId);
		if (f == null)
			return false;
		logger.info("Small Fish Count of Sailor " + sailorId + ":" + f.getSmallFishCount());
		logger.info("Large Fish Count of Sailor " + sailorId + ":" + f.getLargeFishCount());
		return true;
	}

	public void getGroupACollection() {
		int apples = 0, mangoes = 0, bananas = 0;
		for (Map.Entry<Integer, Fruits> entry : sailorsA.entrySet()) {
			apples += entry.getValue().getAppleCount();
			mangoes += entry.getValue().getMangoCount();
			bananas += entry.getValue().getBananaCount();
		}
		logger.info("Apples by Group A:" + apples);
		logger.info("Mangoes by Group A:" + mangoes);
		logger.info("Bananas by Group A:" + bananas);
	}

	public void getGroupBCollection() {
		int smallFish = 0, largeFish = 0;
		for (Map.Entry<Integer, Fish> entry : sailorsB.entrySet()) {
			smallFish += entry.getValue().getSmallFishCount();
			largeFish += entry.getValue().getLargeFishCount();
		}
		logger.info("Small Fish by Group B:" + smallFish);
		logger.info("Large Fish by Group B:" + largeFish);
	}

	public HashMap sortByTotalA(HashMap<Integer, Fruits> map) {
		LinkedList list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				Fruits f1 = (Fruits) ((Map.Entry) (o1)).getValue();
				Fruits f2 = (Fruits) ((Map.Entry) (o2)).getValue();
				if (f1.getTotalFruits() > f2.getTotalFruits())
					return 1;
				else
					return -1;
			}
		});
		HashMap map2 = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			map2.put(entry.getKey(), entry.getValue());
		}
		return map2;
	}

	public void printMapA() {
		for (Integer name : sailorsA.keySet()) {
			logger.info("Sailor " + name + " Apple Count: " + sailorsA.get(name).getAppleCount());
			logger.info("Sailor " + name + " Mango Count: " + sailorsA.get(name).getMangoCount());
			logger.info("Sailor " + name + " Banana Count: " + sailorsA.get(name).getBananaCount());
		}
	}

	public HashMap sortByTotalB(HashMap<Integer, Fish> map) {
		LinkedList list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				Fish f1 = (Fish) ((Map.Entry) (o1)).getValue();
				Fish f2 = (Fish) ((Map.Entry) (o2)).getValue();
				if (f1.getTotalFish() > f2.getTotalFish())
					return 1;
				else
					return -1;
			}
		});
		HashMap map2 = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			map2.put(entry.getKey(), entry.getValue());
		}
		return map2;
	}

	public void printMapB() {
		for (Integer name : sailorsB.keySet()) {
			logger.info("Sailor " + name + " Apple Count: " + sailorsB.get(name).getSmallFishCount());
			logger.info("Sailor " + name + " Mango Count: " + sailorsB.get(name).getLargeFishCount());
		}
	}

}
