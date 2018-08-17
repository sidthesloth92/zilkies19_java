package io.zilker.exercise;

import java.util.Comparator;
import java.util.HashMap;

public class SortGroupB implements Comparator<Integer>{
	HashMap<Integer, GroupB> base;
	public SortGroupB(HashMap<Integer, GroupB> groupB) {
		// TODO Auto-generated constructor stub
		this.base = groupB;
	}

	public int compare(Integer a, Integer b) {
		// TODO Auto-generated method stub
		if(base.get(b).totalCount > this.base.get(a).totalCount) {
			return 1;
		}else {
			return -1;
		}
	}
}
