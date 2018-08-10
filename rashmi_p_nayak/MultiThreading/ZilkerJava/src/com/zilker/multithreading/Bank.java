package com.zilker.multithreading;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bank {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter no.of counters:");
		int m = in.nextInt();
		ExecutorService executor = Executors.newFixedThreadPool(m);
		System.out.println("Enter no.of customers:");
		int n = in.nextInt();
		System.out.print("Enter the service times of "+n+" customers:");
		int [] serviceTime = new int[n];
		for(int i = 0; i<n;i++)
			serviceTime[i] = in.nextInt();
		for(int i =0 ;i < n; i++) {
			Runnable counter = new CounterThread(i+1,serviceTime[i]);
			executor.execute(counter);
			
		}
		executor.shutdown();
		while(!executor.isTerminated()) {
			
		}
		System.out.println("All customers are attended and counters are free!");
	}
}
 