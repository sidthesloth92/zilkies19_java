package com.zilker.multithread;

public class ThreadTest implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10000);
			System.out.println("yess");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String [] args) {
		ThreadTest counter1 = new ThreadTest();
		Thread thread1 = new Thread(counter1);
		
		ThreadTest counter2 = new ThreadTest();
		Thread thread2 = new Thread(counter2);
		System.out.println(thread1.getState());
		thread1.start();
		System.out.println(thread1.getState());
		System.out.println(thread2.getState());
		thread2.start();
	}
	
	

}
