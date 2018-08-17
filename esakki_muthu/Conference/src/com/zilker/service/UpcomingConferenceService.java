package com.zilker.service;

import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.delegate.ConferenceHistoryDelegate;
import com.zilker.delegate.UpcomingConferenceDelegate;

public class UpcomingConferenceService {
	
	public ArrayList<ConferenceData> getHistory(UserData userData) {
		
		return new UpcomingConferenceDelegate().getHistory(userData);
		
	}

}
