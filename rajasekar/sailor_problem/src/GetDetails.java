import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

class GetDetails {
	public static final Logger logger = Logger.getLogger(GetDetails.class.getName());
	static Scanner input = new Scanner(System.in);
	static int k = 0;
	static char val = ' ';
	public static HashMap<Integer, ArrayList<Integer>> getFruitDetails(HashMap<Integer, ArrayList<Integer>> groupA,
			int sailors) throws IOException {

		String count[] = new String[sailors * 3];
		count = FileRead.fileRead(count, "Fruits");
		k = 0;
		for (int i = 0; i < sailors; i++) {
			ArrayList<Integer> al1 = new ArrayList<Integer>();
			al1.add(Integer.parseInt(count[k++]));
			al1.add(Integer.parseInt(count[k++]));
			al1.add(Integer.parseInt(count[k++]));
			groupA.put((i + 1), al1);
		}
		System.out.println("Do you want to sort the Group-A by sum?(y/n)");
		val = input.next().charAt(0);
		if (val == 'y' || val == 'Y') {
			System.out.println("Before Sorting");
			Display.printmap(groupA);
			groupA = Sort.sortByValues(groupA);
			System.out.println("After Sorting");
			Display.printmap(groupA);
		}
		return groupA;
	}

	public static HashMap<Integer, ArrayList<Integer>> getFishDetails(HashMap<Integer, ArrayList<Integer>> groupB,
			int sailors) throws IOException {

		String count[] = new String[sailors * 2];
		count = FileRead.fileRead(count, "Fruits");
		k = 0;
		for (int i = 0; i < sailors; i++) {
			ArrayList<Integer> al2 = new ArrayList<Integer>();
			al2.add(Integer.parseInt(count[k++]));
			al2.add(Integer.parseInt(count[k++]));
			groupB.put((i + 1), al2);
		}
		System.out.println("Do you want to sort the Group-B by sum?(y/n)");
		val = input.next().charAt(0);
		if (val == 'y' || val == 'Y') {
			System.out.println("Before Sorting");
			Display.printmap(groupB);
			groupB = Sort.sortByValues(groupB);
			System.out.println("After Sorting");
			Display.printmap(groupB);
		}
		return groupB;
	}
}
