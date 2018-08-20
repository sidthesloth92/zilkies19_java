package com.zilker.delegate;

import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.dao.GetConferenceHistory;
import com.zilker.dao.GetUpcomingConference;

public class UpcomingConferenceDelegate {

	
public ArrayList<ConferenceData> getHistory(UserData userData){
		
		return new GetUpcomingConference().getHistory(userData);
	}
}
