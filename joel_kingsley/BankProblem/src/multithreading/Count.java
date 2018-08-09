package multithreading;

import java.util.ArrayList;

public class Count extends Thread{
	
	public static ArrayList<Customer> queue = new ArrayList<>();
	int threadNumber;
	
	public Count(int threadNumber){
		this.threadNumber = threadNumber;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(!queue.isEmpty()) {
			Customer customer = null;
			synchronized(queue) {
				if(queue.isEmpty()) {
					return;
				}
				customer = queue.get(0); 
				System.out.println(customer.getCustomerNumber() + " entering thread " + threadNumber);
				queue.remove(0);
			}
			
			try {
				Thread.sleep(customer.getProcessingTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(customer.getCustomerNumber() + " left thread " + threadNumber );
		}
	}

	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
	}
	
	
}