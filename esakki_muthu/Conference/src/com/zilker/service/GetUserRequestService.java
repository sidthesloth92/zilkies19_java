package com.zilker.service;

import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.delegate.GetUserRequestsDelegate;

public class GetUserRequestService {
	
	public ArrayList<ConferenceData> getUserRequests(UserData userData){
		
		return new GetUserRequestsDelegate().getUserRequests(userData);
	}

}
