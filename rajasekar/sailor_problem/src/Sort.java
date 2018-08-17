import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Sort {
	public static HashMap sortByValues(HashMap<Integer, ArrayList<Integer>> map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				ArrayList<Integer> al1 = (ArrayList<Integer>) ((Map.Entry) (o1)).getValue();
				ArrayList<Integer> al2 = (ArrayList<Integer>) ((Map.Entry) (o2)).getValue();
				int sum1 = 0, sum2 = 0;
				for (int v : al1) {
					sum1 += v;
				}
				for (int v : al2) {
					sum2 += v;
				}
				if (sum1 > sum2)
					return 1;
				else if(sum1==sum2) {
					return 0;
				}
				else {
					return -1;
				}
			}
		});
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}
}
