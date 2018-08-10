import java.util.*;
import java.util.logging.Logger;

class Display {
	public static final Logger logger = Logger.getLogger(Display.class.getName());
	static int i = 1;
	static long sum = 0;
	public static void printmap(HashMap<Integer,ArrayList<Integer>> hm) {
		for (Map.Entry<Integer, ArrayList<Integer>> m : hm.entrySet()) {
			System.out.println(m.getKey() + "     " + m.getValue());
		}
	}
	public static void DisplayGroupA(HashMap<Integer, ArrayList<Integer>> groupA) {
		i = 1;
		Iterator it = groupA.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			sum = 0;
			for (int v : (ArrayList<Integer>) pair.getValue()) {
				sum += v;
			}
			System.out.println(
					"Total Apples Collected by sailor " + i + " is " + ((ArrayList<Integer>) pair.getValue()).get(0));
			System.out.println(
					"Total Mangoes Collected by sailor " + i + " is " + ((ArrayList<Integer>) pair.getValue()).get(1));
			System.out.println(
					"Total Bananas Collected by sailor " + i + " is " + ((ArrayList<Integer>) pair.getValue()).get(2));
			System.out.println("------------------------------------------");
			System.out.println("Sum of Fruits by Sailor " + (i) + " is " + sum);
			System.out.println("------------------------------------------");
			i++;
			it.remove();
		}
	}

	public static void DisplayGroupB(HashMap<Integer, ArrayList<Integer>> groupB) {
		i = 1;
		Iterator it = groupB.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			sum = 0;
			for (int v : (ArrayList<Integer>) pair.getValue()) {
				sum += v;
			}
			System.out.println("Total Small Fish Collected by sailor " + i + " is "
					+ ((ArrayList<Integer>) pair.getValue()).get(0));
			System.out.println(
					"Total Big Fish Collected by sailor " + i + " is " + ((ArrayList<Integer>) pair.getValue()).get(1));
			System.out.println("------------------------------------------");
			System.out.println("Sum of Fruits by Sailor " + (i) + " is " + sum);
			System.out.println("------------------------------------------");
			i++;
		}
	}
}
