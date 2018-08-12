package com.zilker.collections_programs;

import java.util.Comparator;

public class SortListB implements Comparator<InputData> {
	public int compare(InputData a,InputData b) {
		if(a.getSmallFishCount()>b.getSmallFishCount()) {
			return 1;
		}
		return -1;
	}
}
