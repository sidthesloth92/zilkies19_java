package com.zilker.threading;

import java.util.logging.Logger;

class QueueTimeSpent implements Runnable {

	String cust_name;
	int timespent;
	static Logger logger = Logger.getLogger(QueueTimeSpent.class.getName());

	public QueueTimeSpent(String cust_name, int timespent) {
		this.cust_name = cust_name;
		this.timespent = timespent;
	}

	public void run() {
		logger.info(Thread.currentThread().getName() + " is alloted for customer " + cust_name);
		spendtime();
		logger.info(Thread.currentThread().getName() + " is free");
	}

	private void spendtime() {
		try {
			Thread.sleep(timespent);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}