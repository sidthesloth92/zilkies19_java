package com.zilker.threadpool;

import java.util.logging.Logger;

public class Task implements Runnable {
	private String process_name;
	private int process_time;
	private Logger logger = Logger.getLogger("Task.class");

	public Task(int process_name, int process_time) {
		this.process_name = "process-" + Integer.toString(process_name);
		this.process_time = process_time;
	}

	public void run() {
		try {
			logger.info("Counter " + Thread.currentThread().getName().substring(14) + " is allocated for "
					+ process_name);
			Thread.sleep(process_time);
			logger.info(process_name+" execution completed. Counter " + Thread.currentThread().getName().substring(14) + " is free.");
		}

		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}