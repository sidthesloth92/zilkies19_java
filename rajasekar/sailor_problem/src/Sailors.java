import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Sailors {
	public static final Logger logger = Logger.getLogger(Sailors.class.getName());
	static Scanner input = new Scanner(System.in);
	public static void main(String args[]) throws IOException {
		int sailors = 0, choice = 0, sid = 0, f = 0, j = 0;
		char ch = ' ';
		String s = "";
		HashMap<Integer, ArrayList<Integer>> groupA = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> groupB = new HashMap<Integer, ArrayList<Integer>>();
		Scanner input = new Scanner(System.in);
		String count[] = new String[3];
		count = FileRead.fileRead(count, "Sailor");
		System.out.println("Total No.of Sailors:" + Integer.parseInt(count[0]));
		System.out.println("No.of Sailors in Group-A:" + Integer.parseInt(count[1]));
		System.out.println("No.of Sailors in Group-B:" + Integer.parseInt(count[2]));
		groupA = GetDetails.getFruitDetails(groupA, Integer.parseInt(count[1]));
		groupB = GetDetails.getFishDetails(groupB, Integer.parseInt(count[2]));
		System.out.println("Do you want to sort the Group-A by sum?(y/n)");
		ch = input.next().charAt(0);
		if(ch=='Y' || ch=='y') {
		System.out.println("Group-A Fruits");
		System.out.println("******************************************");
		Display.DisplayGroupA(groupA);
		System.out.println("******************************************");
		System.out.println("Group-B Fish");
		System.out.println("******************************************");
		Display.DisplayGroupB(groupB);
		System.out.println("******************************************");
		for (Map.Entry<Integer, ArrayList<Integer>> m : groupA.entrySet()) {
			System.out.println(m.getKey() + "     " + m.getValue());
		}
		}
	}
}