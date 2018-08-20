package com.zilker.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.delegate.GetUserRequestsDelegate;

public class GetUserRequestService {
	
	public ArrayList<ConferenceData> getUserRequests(UserData userData) throws SQLException{
		
		return new GetUserRequestsDelegate().getUserRequests(userData);
	}

}
