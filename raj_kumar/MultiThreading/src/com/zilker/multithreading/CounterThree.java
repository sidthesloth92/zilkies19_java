package com.zilker.multithreading;

public class CounterThree extends Thread{
	int sleepDuration = 0;
	public void run() {
		System.out.println("Counter Three Has Started ! with "+sleepDuration);
		try {
			Thread.sleep(sleepDuration);
			Thread.yield();
			System.out.println("Counter Three Has Finished ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Counter_Three(int duration) {
		this.sleepDuration = duration;
	}
}
