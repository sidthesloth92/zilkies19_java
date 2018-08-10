package io.zilker.exercise;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortGroupA implements Comparator<Integer>{
	HashMap<Integer, GroupA> base;
	public SortGroupA(HashMap<Integer, GroupA> groupA) {
		// TODO Auto-generated constructor stub
		this.base = groupA;
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
