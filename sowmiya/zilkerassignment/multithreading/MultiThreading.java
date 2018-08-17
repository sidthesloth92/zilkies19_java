package com.zilkerassignment.multithreading;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreading {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int counter;
		int people_count;
		int time_spent[] = new int[10000];
		Runnable r1;
		ExecutorService threadPool;
		try {
			System.out.println("Enter the number Of counters");
			counter = scan.nextInt();
			System.out.println("Enter the number Of Person wiating for the counter");
			people_count = scan.nextInt();
			System.out.println("Enter the time spent by each person");
			for (int i = 0; i < people_count; i++) {
				time_spent[i] = scan.nextInt()*1000;
			}
			threadPool = Executors.newFixedThreadPool(counter);
			for (int i = 0; i < people_count; i++) {
				r1 = new Task("person" + (i + 1), time_spent[i]);
				threadPool.execute(r1);
			}
		} catch (Exception e) {
			System.out.println("Enter a valid input");
		}
	}
}
