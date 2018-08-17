package threads;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import constants.FixedStatements;

public class Multiple {
	static Scanner input = new Scanner(System.in);
	static Logger logger=Logger.getLogger("Multiple");
	public static void main(String[] args) {
		int counter, number;
		logger.info(FixedStatements.NUMCOUNTER);
		counter = input.nextInt();
		logger.info(FixedStatements.NUMPERSON);
		number = input.nextInt();
		input.nextLine();
		String[] name = new String[number];
		logger.info(FixedStatements.PERNAMES);
		int[] duration = new int[number]; 
		for (int ctr = 0; ctr < number; ctr++) {
			name[ctr] = input.nextLine();
		}
		logger.info(FixedStatements.DURATION);
		for (int ctr = 0; ctr < number; ctr++) {
			duration[ctr] = input.nextInt();
		}

		ExecutorService executor = Executors.newFixedThreadPool(counter);
		for (int ctr = 0; ctr < number; ctr++) {
			executor.submit(new Task(name[ctr], duration[ctr]));
		}
		executor.shutdown();
	}

}
