package com.zilker_assignment.collection.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class SailorReport {
	static Logger log = Logger.getLogger("SailorReport.class");

	public static int totalFruits(HashMap<String, HashMap<String, Integer>> groupA) {
		int count = 0;
		for (String sailor : groupA.keySet()) {
			HashMap<String, Integer> fruit_detail = groupA.get(sailor);
			for (String key : fruit_detail.keySet()) {
				count = count + fruit_detail.get(key);
			}

		}
		return count;

	}

	public static int totalFishes(HashMap<String, HashMap<String, Integer>> groupB) {
		int count = 0;
		for (String sailor : groupB.keySet()) {
			HashMap<String, Integer> fishes_detail = groupB.get(sailor);
			for (String key : fishes_detail.keySet()) {
				count = count + fishes_detail.get(key);
			}

		}
		return count;
	}

	public static void individualDetail(HashMap<String, HashMap<String, Integer>> group, String sailorName) {
		if (group.containsKey(sailorName)) {
			display(group.get(sailorName));
		} else {
			log.warning("SailorName doesNot exists");
		}
	}

	public static void display(HashMap<String, Integer> sailorDetail) {
		Iterator it = sailorDetail.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry detail = (Map.Entry) it.next();
			log.info(detail.getKey() + ":" + detail.getValue());
		}
	}

}
