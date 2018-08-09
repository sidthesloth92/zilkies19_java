package com.zilker.multithreading;

public class MyRunnable implements Runnable {
	int duration;
	public void run() {
        // your code to run in the thread goes here
		try {
			Thread.sleep(this.duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void gettingTime(int time) {
		this.duration = time;
	}
}
