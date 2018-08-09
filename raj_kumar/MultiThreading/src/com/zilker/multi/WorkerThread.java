package com.zilker.multi;

public class WorkerThread implements Runnable{
	int duration;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		processCustomer();
	}
	public WorkerThread(int s){  
        this.duration = s; 
    }  
	public void processCustomer() {
		try {
			long threadId = Thread.currentThread().getId();
			System.out.println("Thread with ID "+threadId+" is starting !");
			System.out.println("Sleep with duration "+this.duration+" is Called");
			Thread.sleep(this.duration);
			System.out.println("Sleep with duration "+this.duration+" is Stopped");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
