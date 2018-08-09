package com.zilker.multithreading;

import java.util.logging.Logger;

class Counter implements Runnable {

	private int customerNumber, customerTime;
	private static Logger logger = Logger.getLogger(Counter.class.getName());

	public Counter(int customerNumber, int customerTime) {
		this.customerNumber = customerNumber;
		this.customerTime = customerTime;
	}

	public void run() {
		logger.info("Counter " + Thread.currentThread().getName().substring(14) + " is allocated for customer "
				+ customerNumber);
		try {
			Thread.sleep(customerTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
