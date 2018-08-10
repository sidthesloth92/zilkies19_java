package Captain;
import java.util.*;
import java.io.*;
import java.util.logging.Logger;

public class CaptainProb {

	static HashMap<Integer, ArrayList<Integer>> groupA = new HashMap<Integer, ArrayList<Integer>>();
	static HashMap<Integer, ArrayList<Integer>> groupB = new HashMap<Integer, ArrayList<Integer>>();
	static HashMap<String, Integer> fruits = new HashMap<String, Integer>();
	static HashMap<String, Integer> fishes = new HashMap<String, Integer>();
	static Scanner scan = new Scanner(System.in);
	static int sailors;
	static char option;
	static char group;
	private static FileReader reader;
	private final static Logger LOGGER = Logger.getLogger(CaptainProb.class.getName());

	static void openFileReader(String path) {
		try {
			reader = new FileReader(path);
		} catch (Exception e) {
			LOGGER.info("Exception " + e);
		}
	}

	static void closeFileReader() {
		try {
			reader.close();
		} catch (Exception e) {
			LOGGER.info("Exception " + e);
		}
	}

	static void readFruits() {
		try {
			openFileReader("/home/ztech/eclipse-workspace/CaptainProblem/src/Captain/fruits.txt");
			int ascii;
			String fruit = "";
			while ((ascii = reader.read()) != -1) {
				if ((char) ascii == '\n') {
					fruits.put(fruit, 0);
					fruit = "";
				} else
					fruit = fruit + (char) ascii;
			}
		} catch (Exception e) {
			LOGGER.info("Exception :  " + e);
		} finally {
			closeFileReader();
		}
	}

	static void readFishes() {
		try {
			openFileReader("/home/ztech/eclipse-workspace/CaptainProblem/src/Captain/fish.txt");
			int ascii;
			String fish = "";
			while ((ascii = reader.read()) != -1) {
				if ((char) ascii == '\n') {
					fishes.put(fish, 0);
					fish = "";
				} else
					fish = fish + (char) ascii;
			}
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getFruits(int n) {
		Iterator<Map.Entry<String, Integer>> iterator;
		int count;
		ArrayList<Integer> fruitCount;
		for (int i = 1; i <= n; i++) {
			iterator = fruits.entrySet().iterator();
			fruitCount = new ArrayList<Integer>();
			while (iterator.hasNext()) {
				Map.Entry<String, Integer> fruit = iterator.next();
				System.out.print("Group-A ID-" + i + " :  Enter the number of " + fruit.getKey() + " you collected : ");
				count = scan.nextInt();
				fruitCount.add(count);
				fruits.put(fruit.getKey(), fruit.getValue() + count);
			}
			groupA.put(i, fruitCount);
		}
	}

	static void getFishes(int n) {
		Iterator<Map.Entry<String, Integer>> iterator;
		int count;
		ArrayList<Integer> fishCount;
		for (int i = 1; i <= n; i++) {
			iterator = fishes.entrySet().iterator();
			fishCount = new ArrayList<Integer>();
			while (iterator.hasNext()) {
				Map.Entry<String, Integer> fishesEntry = iterator.next();
				System.out.print("Group-B ID-" + i + " :  Enter the number of " + fishesEntry.getKey()
						+ " fish you collected : ");
				count = scan.nextInt();
				fishCount.add(count);
				fishes.put(fishesEntry.getKey(), fishesEntry.getValue() + count);
			}
			groupB.put(i, fishCount);
		}
	}

	static void printGroupA() {
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> sailorIterator;
		Iterator<Map.Entry<String, Integer>> fruitIterator;
		ArrayList<Integer> fruitCount;
		int itemNum;
		sailorIterator = groupA.entrySet().iterator();
		while (sailorIterator.hasNext()) {
			fruitIterator = fruits.entrySet().iterator();
			Map.Entry<Integer, ArrayList<Integer>> sailor = sailorIterator.next();
			itemNum = 0;
			fruitCount = sailor.getValue();
			while (fruitIterator.hasNext()) {
				Map.Entry<String, Integer> fruit = fruitIterator.next();
				System.out.println(
						"Group-A ID-" + sailor.getKey() + " has " + fruitCount.get(itemNum) + " " + fruit.getKey());
				itemNum++;
			}
			System.out.println();
		}
	}

	static void printGroupB() {
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> sailorIterator;
		Iterator<Map.Entry<String, Integer>> fishIterator;
		ArrayList<Integer> fishCount;
		int itemNum;
		sailorIterator = groupB.entrySet().iterator();
		while (sailorIterator.hasNext()) {
			fishIterator = fishes.entrySet().iterator();
			Map.Entry<Integer, ArrayList<Integer>> sailor = sailorIterator.next();
			itemNum = 0;
			fishCount = sailor.getValue();
			while (fishIterator.hasNext()) {
				Map.Entry<String, Integer> fish = fishIterator.next();
				System.out.println("Group-B ID-" + sailor.getKey() + " has " + fishCount.get(itemNum) + " "
						+ fish.getKey() + " fishes");
				itemNum++;
			}
			System.out.println();
		}
	}

	static void printAllSailors() {
		System.out.println();
		printGroupA();
		System.out.println();
		printGroupB();
		System.out.println();
	}

	static void printIndividualItemsCount(char group, int id) {
		int itemNum = 0;
		if (group == 'A') {
			ArrayList<Integer> fruitsCount = groupA.get(id);
			Iterator<Map.Entry<String, Integer>> fruitIterator;
			fruitIterator = fruits.entrySet().iterator();
			while (fruitIterator.hasNext()) {
				Map.Entry<String, Integer> fruit = fruitIterator.next();
				System.out.println("Group-A ID-" + id + " has " + fruitsCount.get(itemNum) + " " + fruit.getKey());
				itemNum++;
			}
		} else {
			ArrayList<Integer> fishCount = groupB.get(id);
			Iterator<Map.Entry<String, Integer>> fishIterator;
			fishIterator = fishes.entrySet().iterator();
			while (fishIterator.hasNext()) {
				Map.Entry<String, Integer> fruit = fishIterator.next();
				System.out.println(
						"Group-B ID-" + id + " has " + fishCount.get(itemNum) + " " + fruit.getKey() + " fishes");
				itemNum++;
			}
		}
	}

	static void askIndividualSailorCollection() {
		option = 'y';
		try {
			while (option == 'y') {
				scan.nextLine();
				System.out.print("Do you want a specific Sailor collections (y for yes/n for no) :  ");
				option = scan.nextLine().charAt(0);
				if (option == 'y') {
					System.out.print("Enter the Sailor group (A or B) :  ");
					group = scan.nextLine().toUpperCase().charAt(0);
					System.out.print("Enter the Sailor ID :  ");
					int id = scan.nextInt();
					if (group == 'A') {
						printIndividualItemsCount(group, id);
					} else if (group == 'B') {
						printIndividualItemsCount(group, id);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void main(String[] args) {
		System.out.print("Enter the total number of Sailors : ");
		sailors = scan.nextInt();
		readFruits();
		readFishes();
		getFruits(sailors / 2);
		getFishes(sailors - sailors / 2);
		printAllSailors();
		askIndividualSailorCollection();
		System.out.println();
		System.out.println("------------- Sailors with most fruits collected -------------");
		groupA = sortByValues(groupA);
		printGroupA();
		System.out.println("------------- Sailors with most fishes collected -------------");
		groupB = sortByValues(groupB);
		printGroupB();
	}

	private static HashMap<Integer, ArrayList<Integer>> sortByValues(HashMap<Integer, ArrayList<Integer>> map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				ArrayList<Integer> arrList1 = (ArrayList<Integer>) ((Map.Entry) (o1)).getValue();
				ArrayList<Integer> arrList2 = (ArrayList<Integer>) ((Map.Entry) (o2)).getValue();
				int sum1 = 0, sum2 = 0;
				for (int i : arrList1)
					sum1 += i;
				for (int i : arrList2)
					sum2 += i;
				return sum2 - sum1;
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
