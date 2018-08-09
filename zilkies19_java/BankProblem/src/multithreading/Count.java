package multithreading;

import java.util.ArrayList;

public class Count extends Thread{
	
	public static ArrayList<Integer> queue = new ArrayList<>();
	int threadNumber;
	long processingTime;
	
	public Count(int threadNumber,long processingTime){
		this.threadNumber = threadNumber;
		this.processingTime = processingTime;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(!queue.isEmpty()) {
			int customerNumber = -1;
			synchronized(queue) {
				if(queue.isEmpty()) {
					return;
				}
				customerNumber = queue.get(0); 
				System.out.println(customerNumber + " entering thread " + threadNumber);
				queue.remove(0);
			}
			
			try {
				Thread.sleep(this.processingTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(customerNumber + " left thread " + threadNumber );
		}
	}

	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
	}
	
	
}