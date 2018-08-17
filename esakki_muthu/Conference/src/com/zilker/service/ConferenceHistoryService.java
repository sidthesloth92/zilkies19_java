package com.zilker.service;

import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.delegate.ConferenceHistoryDelegate;

public class ConferenceHistoryService {	

	public ArrayList<ConferenceData> getHistory(UserData userData) {
				
		return new ConferenceHistoryDelegate().getHistory(userData);
		
	}
	
}
