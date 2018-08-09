package com.zilker.multithreading;

public class CounterOne extends Thread{
	int sleepDuration = 0;
	public void run() {
		System.out.println("Counter One Has Started with !"+sleepDuration);
		try {
			Thread.sleep(sleepDuration);
			Thread.yield();
			System.out.println("Counter One Has Finished ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Counter_One(int duration) {
		this.sleepDuration = duration;
	}
}
