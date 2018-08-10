package com.multithread;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
 
public class Multiple {
	
	static Scanner in=new Scanner(System.in);
	
	static int counter=5,cust_count;
	
	static int[] persons=new int[100];
	
	static int[] duration=new int[100];
	
	public static final Logger logger = Logger.getLogger(Multiple.class.getName());
	
	public static void main(String args[])
	{
		logger.info(Strings.NUM_COUNTERS);
		
		counter=in.nextInt();
		
		logger.info(Strings.NUM_CUSTOMERS);
		
		cust_count=in.nextInt();
		
		logger.info(Strings.CUSTOMER_NO);
		
		for(int pos=0;pos<cust_count;pos++)
		{
			persons[pos]=in.nextInt();
			
		}
		logger.info(Strings.CUSTOMER_DURATION);
		
		for(int pos=0;pos<cust_count;pos++)
		{
			duration[pos]=in.nextInt();
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(counter);
		
		for(int pos=0;pos<cust_count;pos++) {
			
			executor.submit(new CounterClass(persons[pos],duration[pos]));
		}
		executor.shutdown();
	}

}
class CounterClass extends Thread{

	int name;
	
	int duration;
	
	Logger logger=Logger.getLogger(CounterClass.class.getName());
	
	public CounterClass(int name,int duration)
	{
		this.name=name;
		
		this.duration=duration;
	}
	
	public void run()
	{
		logger.info(Strings.CUSTOMER + name + Strings.ENTERING + Strings.COUNTER +Thread.currentThread().getName().substring(14));
		try {
			
			TimeUnit.SECONDS.sleep(duration);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		logger.info(Strings.CUSTOMER + name + Strings.LEAVING + Strings.COUNTER +Thread.currentThread().getName().substring(14));
	}
		
}
