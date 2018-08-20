package com.zilker.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.LoginData;
import com.zilker.bean.UserData;
import com.zilker.dao.RequestsAndHallFacilities;
import com.zilker.delegate.RequestsAndHallFacilitiesDelegate;
import com.zilker.ui.ConferenceRequests;

public class ConferenceHallRequestService {
	
	RequestsAndHallFacilities requestsAndHallFacilities = new RequestsAndHallFacilities();
	
	public ArrayList<ConferenceData> viewRequests(UserData userData) throws SQLException {	
		
		RequestsAndHallFacilitiesDelegate requestsAndHallFacilitiesDelegate = new RequestsAndHallFacilitiesDelegate();
					
		ArrayList<ConferenceData> conferenceData = requestsAndHallFacilitiesDelegate.requests_Logic(userData);
		
		ConferenceRequests conferenceRequests = new ConferenceRequests();
		
		conferenceRequests.conferenceRequest(conferenceData);		
				
		return conferenceData;
		
	}		

}
