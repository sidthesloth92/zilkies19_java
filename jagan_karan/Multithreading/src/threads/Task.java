package threads;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import constants.FixedStatements;

public class Task extends Thread {

	Logger logger = Logger.getLogger("Task");
	String name;
	long duration;

	public Task(String name, int duration) {
		this.name = name;
		this.duration = (long) duration;
	}

	public void run() {
		logger.info(name + FixedStatements.ENTER + Thread.currentThread().getName().substring(14));
		hold(duration);
		logger.info(name + FixedStatements.LEAVE + Thread.currentThread().getName().substring(14));
	}

	private void hold(long duration2) {
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
