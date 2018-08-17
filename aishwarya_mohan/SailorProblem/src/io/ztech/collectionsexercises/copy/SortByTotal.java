package io.ztech.collectionsexercises.copy;

import java.util.Comparator;

public class SortByTotal implements Comparator<Sailor> {

	@Override
	public int compare(Sailor o1, Sailor o2) {
		return o1.getTotal() - o2.getTotal();
	}

}
