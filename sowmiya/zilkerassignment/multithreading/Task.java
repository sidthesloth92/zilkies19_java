package com.zilkerassignment.multithreading;

public class Task implements Runnable {
    String name;
    int time;
	Task(String name,int time){
		this.name=name;
		this.time=time;
		
	}
	@Override
	public void run() {
		try {
			System.out.println(name+"Enters counter "+Thread.currentThread().getName().substring(14));
			Thread.sleep(time);
			System.out.println();
			System.out.println(name+"Exits counter "+Thread.currentThread().getName().substring(14));
			System.out.println();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
