package com.zilker.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.dao.RequestsAndHallFacilities;
import com.zilker.service.ConferenceHallRequestService;

public class RequestsAndHallFacilitiesDelegate {

	RequestsAndHallFacilities requestsAndHallFacilities = new RequestsAndHallFacilities();
	
	public ArrayList<ConferenceData> requests_Logic(UserData userData) throws SQLException {
				
		return requestsAndHallFacilities.fetchRequests(userData);
						
	}
		
}
