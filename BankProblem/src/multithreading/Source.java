package multithreading;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;


public class Source {
	
	private static final Logger LOGGER = Logger.getLogger(Source.class.getName());
	
	public static void main(String args[]) {
		ArrayList<Integer> queue = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		LOGGER.info("Enter n(Number of counters):");
		int n = sc.nextInt();
		
		LOGGER.info("Enter x(Number of customers):");
		int x = sc.nextInt();
		
		LOGGER.info("Enter y(Time to process):");
		long y = sc.nextLong();
		
		for(int i=0;i<x;i++) {
			queue.add(i+1);
		}
		
		Count.queue = queue;
		
		for(int i=0;i<n;i++) {
			Count count = new Count(i+1,y);
			count.start();
		}
	}
}
