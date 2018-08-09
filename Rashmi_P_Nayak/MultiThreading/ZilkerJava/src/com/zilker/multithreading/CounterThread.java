package com.zilker.multithreading;

public class CounterThread implements Runnable{
	int customerId;
	int customerTime;
	
	public CounterThread(int customerId, int customerTime) {
		this.customerId = customerId;
		this.customerTime = customerTime;
	}

	@Override
	public void run() {
		System.out.println("Customer "+customerId+" moves to Counter No."+Thread.currentThread().getName().charAt(14));
		try {
			Thread.sleep(customerTime*1000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Counter No."+Thread.currentThread().getName().charAt(14)+" is now free!");
		
	}
	
	
}
