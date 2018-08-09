package com.zilker.threading;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Counter {

	static final Logger logger = Logger.getLogger(Counter.class.getName());
	static Scanner input = new Scanner(System.in);
	static String cntr, cust, timeinsec;
	static int[] timespent;

	public static void main(String[] args) {
		try {
			input = new Scanner(System.in);
			logger.info("Enter the no. of cntr");
			cntr = input.nextLine();
			ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(cntr));
			logger.info("Enter the no.of cust : ");
			cust = input.nextLine();
			timespent = new int[Integer.parseInt(cust)];
			for (int i = 0; i < Integer.parseInt(cust); i++) {
				logger.info("Enter the time spent for customer " + (i+1));
				timeinsec = input.next();
				timespent[i] = Integer.parseInt(timeinsec) * 1000;
			}
			for (int i = 1; i <= Integer.parseInt(cust); i++) {
				Runnable worker = new QueueTimeSpent(String.valueOf(i), timespent[i-1]);
				executor.execute(worker);
			}
			executor.shutdown();
		} catch (NumberFormatException e) {
			logger.warning("Enter correct value");
		}
	}

}
