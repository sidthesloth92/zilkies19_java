package com.zilker.multithread;

import java.util.logging.Logger;

public class ThreadObject implements Runnable{
	public static int threadName;
	public Logger log = Logger.getLogger(ThreadObject.class.getName());
	public static long sleepTime;
	public ThreadObject(int name,long sleepTime) {
		ThreadObject.threadName = name;
		ThreadObject.sleepTime = sleepTime*1000;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//synchronized() {
			try {
				log.info("Counter " + Thread.currentThread().getName());
				getTime();
			}
			catch(Exception e) {
				log.info(e.toString());
			}
		}
		
	//}
	public void getTime() throws InterruptedException {
		//System.out.println("Enter the Time taken for the customer ");
		Thread.sleep(sleepTime);
		
	}
	
	

}
