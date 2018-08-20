package com.zilker.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.bean.Availability;
import com.zilker.delegate.AvailabilityCheckingDelegate;

public class AvailabilityCheckingService {
	
	public ArrayList<Availability> displayAvailability() throws SQLException{
		
		
		return new AvailabilityCheckingDelegate().displayAvailability();
	}

}
