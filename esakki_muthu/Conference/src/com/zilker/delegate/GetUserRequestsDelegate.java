package com.zilker.delegate;

import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.dao.GetUserRequests;

public class GetUserRequestsDelegate {

	
	public ArrayList<ConferenceData> getUserRequests(UserData userData){
		
		return new GetUserRequests().getRequests(userData);
	}
}
