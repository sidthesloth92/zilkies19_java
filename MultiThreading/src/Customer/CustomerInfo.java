package Customer;

import java.util.*;
import java.util.logging.*;
import Thread.Worker;
import java.util.concurrent.*;

public class CustomerInfo {
	
	static final Logger logger = Logger.getLogger(Worker.class.getName());
	static String counters, customers, time;
	static Scanner scan;
	private static int[] threadTime;
	
	public static void main(String[] args) {  
		try {
			scan = new Scanner(System.in);
			logger.info("Enter the number of counters : ");
			counters = scan.next();
	        ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(counters));
	        logger.info("Enter the number of customers : ");
			customers = scan.next();
			threadTime = new int[Integer.parseInt(customers)];
	        for (int i = 0; i < Integer.parseInt(customers); i++) {
	        	logger.info("Enter the time of work for customer " + (i+1) + " : ");
	        	time = scan.next();
	        	threadTime[i] = Integer.parseInt(time) * 1000;
	          }  
	        for (int i = 0; i < Integer.parseInt(customers); i++) {
	        	Runnable worker = new Worker("" + (i+1), threadTime[i]);  
	            executor.execute(worker);
	        }
	        executor.shutdown();
		} catch(NumberFormatException e) {
			logger.warning("Oops Wrong Format");
		}
} 
	
}
