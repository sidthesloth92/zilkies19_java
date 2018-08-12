package io.ztech.ship.impl;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainClass {

	private static final Logger logger = Logger.getLogger(Sailor.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String args[]) throws IOException {
		Sailor obj = new Sailor();
		while (true) {
			logger.info("1.Individual Sailor Collection\n2.Group A Collection\n3.Group B Collection\n4.Sort Group A"
					+ "\n5.Sort Group B\n6.Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				individualSailor(obj);
				break;
			}
			case 2: {
				obj.getGroupACollection();
				break;
			}
			case 3: {
				obj.getGroupBCollection();
				break;
			}
			case 4: {
				obj.sailorsA = obj.sortByTotalA(obj.sailorsA);
				obj.printMapA();
				break;
			}
			case 5: {
				obj.sailorsB = obj.sortByTotalB(obj.sailorsB);
				obj.printMapB();
				break;
			}
			case 6: {
				logger.info("Thank You!");
				System.exit(0);
				break;
			}
			}

		}
	}

	public static void individualSailor(Sailor obj) {
		String groupChoice = "";
		do {
			logger.info("A.Group A\nB.Group B");
			groupChoice = sc.next();
		} while (!(groupChoice.equalsIgnoreCase("A") || groupChoice.equalsIgnoreCase("B")));
		boolean flag = true;
		do {
			logger.info("Enter Sailor Id:");
			int sailorId = sc.nextInt();
			if (groupChoice.equalsIgnoreCase("A")) {
				if (!obj.getSailorCollectionA(sailorId))
					flag = false;
				else
					flag = true;
			} else if (groupChoice.equalsIgnoreCase("B")) {
				if (!obj.getSailorCollectionB(sailorId))
					flag = false;
				else
					flag = true;
			}
			if (!flag) {
				logger.info("Invalid Sailor ID!");
			}
		} while (!flag);
	}
}
