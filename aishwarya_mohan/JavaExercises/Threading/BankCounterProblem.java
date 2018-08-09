package Threading;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Customer implements Runnable {
	private String tokenNo;
	private int timeInSec;

	public Customer(String s, int timeInSec) {
		this.tokenNo = s;
		this.timeInSec = timeInSec;
	}

	public void run() {
		System.out.println(
				"Customer " + tokenNo + " started at Counter " + Thread.currentThread().getName().substring(14));
		processCustomerRequest();
		System.out.println("Customer " + tokenNo + " ended");
	}

	private void processCustomerRequest() {
		try {
			Thread.sleep(timeInSec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class BankCounterProblem {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of counters: ");
		int n = sc.nextInt();
		System.out.println("Enter the number of customers: ");
		int m = sc.nextInt();
		System.out.println("Enter processing time for each customer");
		int time[] = new int[m];
		for (int i = 0; i < m; i++) {
			time[i] = sc.nextInt();
		}

		ExecutorService executor = Executors.newFixedThreadPool(n);

		for (int i = 0; i < m; i++) {
			Runnable currentCustomer = new Customer(i+1+"", time[i]);
			executor.execute(currentCustomer);
		}
		executor.shutdown();
		sc.close();

	}

}
