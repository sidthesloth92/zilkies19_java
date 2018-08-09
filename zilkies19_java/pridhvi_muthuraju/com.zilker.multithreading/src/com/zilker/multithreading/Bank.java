package com.zilker.multithreading;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Bank {

	private static final Logger logger = Logger.getLogger(Counter.class.getName());
	private static int counters, customers;
	static Scanner sc;
	private static int[] customerTime;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		logger.info("Number of counters : ");
		counters = sc.nextInt();
		ExecutorService executor = Executors.newFixedThreadPool(counters);
		logger.info("Number of customers : ");
		customers = sc.nextInt();
		customerTime = new int[customers];
		for (int i = 0; i < customers; i++) {
			logger.info("Time for customer " + (i + 1) + " : ");
			customerTime[i] = sc.nextInt() * 1000;
		}
		for (int i = 0; i < customers; i++) {
			Runnable counter = new Counter((i + 1), customerTime[i]);
			executor.execute(counter);
		}
		executor.shutdown();
	}

}