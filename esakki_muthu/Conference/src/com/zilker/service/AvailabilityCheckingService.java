package com.zilker.service;

import java.util.ArrayList;

import com.zilker.bean.Availability;
import com.zilker.delegate.AvailabilityCheckingDelegate;

public class AvailabilityCheckingService {
	
	public ArrayList<Availability> displayAvailability(){
		
		
		return new AvailabilityCheckingDelegate().displayAvailability();
	}

}
