package com.zilker.collections_programs;

import java.util.Comparator;

public class SortListA implements Comparator<InputData> {
	public int compare(InputData a,InputData b) {
		if(a.getAppleCount()>b.getAppleCount()) {
			return 1;
		}
		return -1;
	}
}
