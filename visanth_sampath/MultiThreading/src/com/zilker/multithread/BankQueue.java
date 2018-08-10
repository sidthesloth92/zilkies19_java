package com.zilker.multithread;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class BankQueue{
	static Scanner sc = new Scanner(System.in);
	public static Logger log = Logger.getLogger(BankQueue.class.getName());

	
	public static void main(String[] args) {
		int numberOfCounters,numberOfCustomers,i;
		ArrayList<Long> customerTime = new ArrayList<Long>();
		log.info("Enter the Number of Counters");
		numberOfCounters = sc.nextInt();
		ExecutorService counters = Executors.newFixedThreadPool(numberOfCounters);
		log.info("Enter the Number of Customers in the Queue");
		numberOfCustomers = sc.nextInt();
		log.info("Enter the Time taken for Each Customer");
		for(i=0;i<numberOfCustomers;i++) {
			customerTime.add(sc.nextLong());
		}
		
		for(i=0;i<numberOfCustomers;i++) {
			Runnable customer = new ThreadObject(i,customerTime.get(i));
			counters.execute(customer);
		}
		counters.shutdown();
		
	}
	

}
