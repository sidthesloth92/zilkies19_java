package Thread;

import java.util.logging.*;

public class Worker implements Runnable {
	
	String name;
	int time;
	static Logger logger = Logger.getLogger(Worker.class.getName());
	
	public Worker(String s, int time) {
		this.name = s;
		this.time = time;
	}

	public void run() {
		logger.info(Thread.currentThread().getName().substring(14) + " is used by customer " + this.name);
		try {
			Thread.sleep(this.time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info(this.name+" has completed his work");
	}

	
	
}
