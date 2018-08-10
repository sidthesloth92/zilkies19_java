package io.ztech.collectionsexercises.copy;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SailorFruitProblem {

	public static String getName(int col) {
		switch (col) {
		case 1:
			return "Apples ";
		case 2:
			return "Mangoes ";
		case 3:
			return "Bananas ";
		case 4:
			return "Small Fish ";
		case 5:
			return "Large  Fish ";
		default:
			return null;
		}
	}

	static void displayAll(ArrayList<Sailor> groupA, ArrayList<Sailor> groupB) {
		Sailor.display(groupA);
		Sailor.display(groupB);
	}

	static void sortGroupBasedOnTotal(ArrayList<Sailor> groupA, ArrayList<Sailor> groupB) {
		Collections.sort(groupA, new SortByTotal());
		Collections.sort(groupB, new SortByTotal());
		displayAll(groupA, groupB);
	}

	public static void main(String args[]) {

		Sailor s;
		int num1, num2, choice;

		ArrayList<Sailor> groupA = new ArrayList<Sailor>();
		ArrayList<Sailor> groupB = new ArrayList<Sailor>();

		Scanner sc = null, scanFile = null;

		try {
			sc = new Scanner(System.in);

			File file = new File("/home/administrator/Desktop/zilker19/SailorFruitProblemDataInput.txt");
			scanFile = new Scanner(file).useDelimiter(",");

			System.out.println("Enter number of sailors in group A :");
			num1 = sc.nextInt();

			System.out.println("Enter number of sailors in group B :");
			num2 = sc.nextInt();

			for (int i = 0; i < num1; i++) {
				s = new Sailor(i + 1);
				for (int j = 0; scanFile.hasNext() && j < 6; j++) {
					if (j >= 1 && j <= 3) {
						s.addElement(j, Integer.parseInt(scanFile.next()), s.map);
					} else {
						scanFile.next();
					}
				}
				groupA.add(s);
			}
			for (int i = 0; i < num2; i++) {
				s = new Sailor(num1 + i + 1);
				for (int j = 0; j < 6; j++) {
					if (j == 4 || j == 5) {
						s.addElement(j, Integer.parseInt(scanFile.next()), s.map);
					} else {
						scanFile.next();
					}
				}
				groupB.add(s);
			}

			do {
				System.out.println("1. Display all\t2.Sort both groups based on Total");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					displayAll(groupA, groupB);
					break;
				case 2:
					sortGroupBasedOnTotal(groupA, groupB);
					break;
				default:
					System.out.println("Enter a valid input");
				}

				System.out.println("Do you want to continue? (y/n)");
			} while (sc.next().charAt(0) == 'y');

		}
		catch (NumberFormatException e) {
			System.out.println("Enter valid inputs!");
		}
		catch (Exception e) {
			System.out.println("Enter valid inputs!");
		} finally {
			sc.close();
			scanFile.close();
		}

	}
}
