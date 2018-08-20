package com.zilker.delegate;

import java.sql.SQLException;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.dao.FetchConferenceDetails;

public class FetchConfereneDetailsDelegate {
	
	public ConferenceData getDatas(int conference_id) throws SQLException {
		
		return new FetchConferenceDetails().getDetails(conference_id);
	}

	public int setAdminResponse(UserData userData,int conference_id,int option) throws SQLException {
		
		
		return new FetchConferenceDetails().setAcceptOrDecline(userData,conference_id,option);
	}
	
}
