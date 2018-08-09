package com.zilker.multithreading;

public class CounterTwo extends Thread{
	int sleepDuration = 0;
	public void run() {
		System.out.println("Counter Two Has Started with ! "+sleepDuration );
		try {
			Thread.sleep(sleepDuration);
			Thread.yield();
			System.out.println("Counter Two Has Finished ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Counter_Two(int duration) {
		this.sleepDuration = duration;
	}
}
