package com.zilker.delegate;

import com.zilker.bean.ConferenceData;
import com.zilker.dao.AddNewBooking;

public class AddNewBookingDelegate {
	
	public int book_By_Id(ConferenceData conferenceData) {
		
		return new AddNewBooking().bookById(conferenceData);
	}

}
