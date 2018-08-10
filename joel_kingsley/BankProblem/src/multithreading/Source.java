package multithreading;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;


public class Source {
	
	private static final Logger LOGGER = Logger.getLogger(Source.class.getName());
	
	public static void main(String args[]) {
		ArrayList<Customer> queue = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		LOGGER.info("Enter n(Number of counters):");
		int n = sc.nextInt();
		
		LOGGER.info("Enter x(Number of customers):");
		int x = sc.nextInt();
		
		LOGGER.info("Enter Time to process for each customer(in milli seconds):");
		for(int i=0;i<x;i++) {
			LOGGER.info("Time for customer " + Integer.toString(i+1) +":");
			Customer customer = new Customer(i+1,sc.nextLong());
			queue.add(customer);
		}
		
		Count.queue = queue;
		
		for(int i=0;i<n;i++) {
			Count count = new Count(i+1);
			count.start();
		}
		sc.close();
	}
}
