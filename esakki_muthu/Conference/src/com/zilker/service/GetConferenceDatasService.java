package com.zilker.service;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.delegate.FetchConfereneDetailsDelegate;

public class GetConferenceDatasService {
	
	FetchConfereneDetailsDelegate fetchConfereneDetailsDelegate = new FetchConfereneDetailsDelegate();
	
	public ConferenceData getDatas(int conference_id) {
		
		return fetchConfereneDetailsDelegate.getDatas(conference_id);
		
	}
	
	public int setAccept(UserData userData,int conference_id,int option) {
		
		return fetchConfereneDetailsDelegate.setAdminResponse(userData,conference_id,option);
	}

}
