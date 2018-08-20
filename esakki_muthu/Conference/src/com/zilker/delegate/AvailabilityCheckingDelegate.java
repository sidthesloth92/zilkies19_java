package com.zilker.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.bean.Availability;
import com.zilker.dao.AvailabilityChecking;

public class AvailabilityCheckingDelegate {
	
	public ArrayList<Availability> displayAvailability() throws SQLException{
		
		return new AvailabilityChecking().getAvailability();
	}

}
