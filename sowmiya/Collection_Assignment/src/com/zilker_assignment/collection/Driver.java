package com.zilker_assignment.collection;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker_assignment.collection.services.ReadInput;

public class Driver {
	static Logger log = Logger.getLogger("Driver.class");

	public static void main(String args[]) {
		File inputFile;
		Scanner scan;
		char choice;
		char groupName;
		String choice1;
		String sailorName;
		try {
			inputFile = new File("/home/sowmya/eclipse-workspace/collection/input");
			scan = new Scanner(inputFile);
			ReadInput.scanInputFromFile(scan);
			log.info("Do You Want To See Individual Sailor Report Y/N");
			choice = scan.nextLine().trim().charAt(0);
			do {
				if (choice == 'N')
					break;
				log.info("Enter 1.groupA 2.groupB");
				groupName = scan.nextLine().trim().charAt(0);
				sailorName = scan.nextLine();
				System.out.println(sailorName);
				ReadInput.getIndividualReport(groupName, sailorName);
				log.info("Do You Want To Continue Y/N");
				choice = scan.nextLine().charAt(0);
			} while (choice == 'Y');
			log.info("Sort GroupA by count of Banana Apple Orange:Enter any one of value");
			choice1 = scan.nextLine();
			System.out.println(choice1);
			ReadInput.sort(1, choice1);
			log.info("Sort GroupB by count of small big large fish:Enter any one of value");
			choice1 = scan.nextLine();
			ReadInput.sort(2, choice1);
			System.out.println(choice1);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
