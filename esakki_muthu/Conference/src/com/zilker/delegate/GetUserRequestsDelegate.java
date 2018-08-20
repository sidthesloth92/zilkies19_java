package com.zilker.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.dao.GetUserRequests;

public class GetUserRequestsDelegate {

	
	public ArrayList<ConferenceData> getUserRequests(UserData userData) throws SQLException{
		
		return new GetUserRequests().getRequests(userData);
	}
}
