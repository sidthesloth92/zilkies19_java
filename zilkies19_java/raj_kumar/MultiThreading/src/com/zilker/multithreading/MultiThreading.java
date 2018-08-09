package com.zilker.multithreading;

import java.util.Date;
import java.util.Scanner;

public class MultiThreading {
	public static void main(String args[]) {
		int counter = 0;
		//  Creating Objects for Thread
		CounterOne counterOne[] = new CounterOne[5];
		CounterTwo counterTwo[] = new CounterTwo[5];
		//MyRunnable myRunnable = new MyRunnable();
		CounterThree counterThree[] = new CounterThree[5];
		Scanner in = new Scanner(System.in);
		//Getting Num Of Counters
		
		
		
		System.out.println("Enter the Number of Counters !");
		int numOfCounters = in.nextInt();
		System.out.println("Enter the Number of Customers");
		int numOfCustomers = in.nextInt();
		int[] durationCustomer = new int[numOfCustomers];
		for(counter = 0;counter < numOfCustomers;counter++) {
			durationCustomer[counter] = in.nextInt();
		}
		Date start = new Date();
		//Thread[] threads = new Thread[numOfCounters];
		//System.out.println("Starting time "+start.toString());
		counter = 0;
		int incrementor = 0;
		counterOne[incrementor].Counter_One(durationCustomer[counter]);
		counterOne[incrementor].start();
		while(counter <= numOfCustomers) {
			System.out.println("Counter value is"+counter+" numOfCustomers Value is"+numOfCustomers);
			System.out.println("Starting A Cycle !");;
			
			if(!counterOne[incrementor].isAlive()) {
				counterOne[incrementor].Counter_One(durationCustomer[counter]);
				counterOne[incrementor].start();
				incrementor++;
				counter++;
			}
			if(!counterTwo[incrementor].isAlive()) {
				counterTwo[incrementor].Counter_Two(durationCustomer[counter]);
				counterTwo[incrementor].start();
				incrementor++;
				counter++;
			}
			if(!counterThree[incrementor].isAlive()) {
				counterThree[incrementor].Counter_Three(durationCustomer[counter]);
				counterThree[incrementor].start();
				counter++;
				incrementor++;
			}
			while(counterOne[incrementor].isAlive() || counterOne[incrementor].isAlive() || counterOne[incrementor].isAlive()) {
				//System.out.println("All Counters are Busy");
			}
		}
		Date time = new Date();
		//https://stackoverflow.com/questions/1970239/in-java-how-do-i-get-the-difference-in-seconds-between-2-dates
		long seconds = (time.getTime()- start.getTime())/1000;
		System.out.println("It took "+numOfCustomers+" "+seconds+" seconds to finish their Job");
	}
}

