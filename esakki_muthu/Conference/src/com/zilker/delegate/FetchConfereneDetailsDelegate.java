package com.zilker.delegate;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.dao.FetchConferenceDetails;

public class FetchConfereneDetailsDelegate {
	
	public ConferenceData getDatas(int conference_id) {
		
		return new FetchConferenceDetails().getDetails(conference_id);
	}

	public int setAdminResponse(UserData userData,int conference_id,int option) {
		
		
		return new FetchConferenceDetails().setAcceptOrDecline(userData,conference_id,option);
	}
	
}
