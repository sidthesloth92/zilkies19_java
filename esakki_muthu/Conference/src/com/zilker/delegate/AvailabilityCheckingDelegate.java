package com.zilker.delegate;

import java.util.ArrayList;

import com.zilker.bean.Availability;
import com.zilker.dao.AvailabilityChecking;

public class AvailabilityCheckingDelegate {
	
	public ArrayList<Availability> displayAvailability(){
		
		return new AvailabilityChecking().getAvailability();
	}

}
