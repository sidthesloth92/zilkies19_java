package com.zilker.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.dao.GetConferenceHistory;

public class ConferenceHistoryDelegate {

	public ArrayList<ConferenceData> getHistory(UserData userData) throws SQLException{
		
		return new GetConferenceHistory().getHistory(userData);
	}
	
}
