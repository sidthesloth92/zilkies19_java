package com.zilker.multi;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	//https://www.javatpoint.com/java-thread-pool
	public static void main(String[] args) {  
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the Number Of Customers ");
		int numOfCustomers = in.nextInt();
		System.out.println("Enter the number of Counters !");
		int numOfCounters = in.nextInt();
		int[] durationOfCustomer = new int[numOfCustomers];
		for(int inc = 0;inc < numOfCustomers;inc++) {
			System.out.println("Enter the Duration in Seconds For Customer "+(inc + 1));
			durationOfCustomer[inc] = in.nextInt() * 1000;
		}
		//============================
		//  Thread Executioner Starts
		//============================
        ExecutorService executor = Executors.newFixedThreadPool(numOfCounters);//creating a pool of 5 threads
        Date startDate = new Date();
        for (int duration: durationOfCustomer) {  
            Runnable worker = new WorkerThread(duration);  
            executor.execute(worker);//calling execute method of ExecutorService  
         }  
        executor.shutdown();  
        while (!executor.isTerminated()) {   }  
        System.out.println("Finished all threads"); 
        //https://stackoverflow.com/questions/1970239/in-java-how-do-i-get-the-difference-in-seconds-between-2-dates
        Date endDate = new Date();
        long seconds = (endDate.getTime()-startDate.getTime())/1000;
        System.out.println("It took "+seconds+" seconds To complete the process !");
    }
}
