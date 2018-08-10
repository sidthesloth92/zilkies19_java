package com.zilker_assignment.collection.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker_assignment.collection.comparator.ComparatorClass;

public class ReadInput {
	static Logger log = Logger.getLogger("ReadInput.class");
	static Scanner scan;
	static HashMap<String, HashMap<String, Integer>> groupA = new LinkedHashMap<String, HashMap<String, Integer>>();
	static HashMap<String, HashMap<String, Integer>> groupB = new LinkedHashMap<String, HashMap<String, Integer>>();

	public static void scanInputFromFile(Scanner scanVariable) {
		int sailorCount;
		String sailorName;
		char choice;
		int groupSelection;
		scan = scanVariable;
		log.info(scan.nextLine());
		sailorCount = scan.nextInt();
		scan.nextLine();
		log.info(String.valueOf(sailorCount));
		for (int i = 0; i < sailorCount; i++) {
			log.info("Name of the sailor" + i);
			sailorName = scan.nextLine();
			log.info(sailorName);
			if (i % 2 == 0)
				addToGroupA(groupA, sailorName);
			else
				addToGroupB(groupB, sailorName);
		}
		System.out.println("Total Count of Fruits=" + SailorReport.totalFruits(groupA));
		System.out.println("Total Count of Fishes=" + SailorReport.totalFishes(groupB));

	}

	public static void addToGroupA(HashMap<String, HashMap<String, Integer>> groupA, String sailorName) {
		int temp;
		HashMap<String, Integer> fruits = new HashMap<String, Integer>();
		log.info("Number of Bananas Collected by" + sailorName);
		temp = scan.nextInt();
		fruits.put("Banana", temp);
		log.info(String.valueOf(temp));
		log.info("Number of Apples Collected by" + sailorName);
		temp = scan.nextInt();
		log.info(String.valueOf(temp));
		fruits.put("Apple", temp);
		log.info("Number of Oranges Collected by" + sailorName);
		temp = scan.nextInt();
		fruits.put("Orange", temp);
		log.info(String.valueOf(temp));
		groupA.put(sailorName, fruits);
		scan.nextLine();
	}

	public static void addToGroupB(HashMap<String, HashMap<String, Integer>> groupB, String sailorName) {
		HashMap<String, Integer> fishes = new HashMap<String, Integer>();
		int temp;
		log.info("Enter the Number of Small Fish Collected by" + sailorName);
		temp = scan.nextInt();
		log.info(String.valueOf(temp));
		fishes.put("small_fish", temp);
		log.info("Enter the Number of Big Fish Collected by" + sailorName);
		temp = scan.nextInt();
		log.info(String.valueOf(temp));
		fishes.put("big_fish", temp);
		log.info("Enter the Number of Large Fish Collected by" + sailorName);
		temp = scan.nextInt();
		log.info(String.valueOf(temp));
		fishes.put("large_fish", temp);
		groupB.put(sailorName, fishes);
		scan.nextLine();

	}

	public static void getIndividualReport(char groupName, String sailorName) {
		if (groupName == '1') {
			SailorReport.individualDetail(groupA, sailorName);

		}
		if (groupName == '2')
			SailorReport.individualDetail(groupB, sailorName);

	}

	public static void sort(int group, String keyChoice) {
		if (group == 1) {
			groupA = ComparatorClass.sortMap(groupA, keyChoice);
			displayGroup(groupA);
		} else {
			groupB = ComparatorClass.sortMap(groupB, keyChoice);
			displayGroup(groupB);
		}

	}

	public static void displayGroup(HashMap<String, HashMap<String, Integer>> group) {
		for (Entry<String, HashMap<String, Integer>> detail : group.entrySet()) {
			System.out.println(detail.getKey() + ":" + detail.getValue());
		}

	}

}
