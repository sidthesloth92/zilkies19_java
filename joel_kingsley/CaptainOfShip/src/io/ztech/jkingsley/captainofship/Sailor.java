package io.ztech.jkingsley.captainofship;

public class Sailor {
	Long sailorId;
	String userName;
	
	public Sailor(Long sailorId, String userName) {
		super();
		this.sailorId = sailorId;
		this.userName = userName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return sailorId + " : " + userName;
	}
	
	
	
}
