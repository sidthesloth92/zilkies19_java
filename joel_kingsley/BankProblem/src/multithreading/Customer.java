package multithreading;

public class Customer {
	int customerNumber;
	Long processingTime = 1000L;
	
	public Customer(int customerNumber,Long processingTime) {
		this.customerNumber = customerNumber;
		this.processingTime = processingTime;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Long getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(Long processingTime) {
		this.processingTime = processingTime;
	}
	
	
}
