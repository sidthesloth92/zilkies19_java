package com.zilker_assignment.collection.comparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class ComparatorClass {
	public static HashMap<String, HashMap<String, Integer>> sortMap(
			HashMap<String, HashMap<String, Integer>> unsortedMap, String key) {

		List<Entry<String, HashMap<String, Integer>>> list = new LinkedList<Entry<String, HashMap<String, Integer>>>(
				unsortedMap.entrySet());

		Collections.sort(list, new Comparator<Entry<String, HashMap<String, Integer>>>() {
			int value1, value2;

			@Override
			public int compare(Entry<String, HashMap<String, Integer>> o1, Entry<String, HashMap<String, Integer>> o2) {
				value1 = o1.getValue().get(key);
				value2 = o2.getValue().get(key);
				return value2 - value1;
			}
		});

		HashMap<String, HashMap<String, Integer>> sortedMap = new LinkedHashMap<String, HashMap<String, Integer>>();
		for (Entry<String, HashMap<String, Integer>> item : list) {
			sortedMap.put(item.getKey(), item.getValue());
		}
		return sortedMap;
	}
}