package com.zilker.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.delegate.ConferenceHistoryDelegate;

public class ConferenceHistoryService {	

	public ArrayList<ConferenceData> getHistory(UserData userData) throws SQLException {
				
		return new ConferenceHistoryDelegate().getHistory(userData);
		
	}
	
}
