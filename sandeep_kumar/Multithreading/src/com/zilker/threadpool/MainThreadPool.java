package com.zilker.threadpool;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class MainThreadPool {
	private static Logger logger = Logger.getLogger("MainThreadPool.class");
	public static void main(String args[]) {
		Scanner in=new Scanner(System.in);
		logger.info("Enter the number of counters");
		int counter=in.nextInt();
		logger.info("Enter the total number of process");
		int numberOfProcess=in.nextInt();
		int threadTime[]=new int[numberOfProcess];
		logger.info("Enter the time for each process");
		for(int i=0;i<numberOfProcess;i++) {
			threadTime[i]=in.nextInt();
		}
		ExecutorService service = Executors.newFixedThreadPool(counter);
		for(int i=0;i<numberOfProcess;i++) {
			Runnable process=new Task(i+1,threadTime[i]);
			service.execute(process);
		}
		service.shutdown(); 
	}
}
