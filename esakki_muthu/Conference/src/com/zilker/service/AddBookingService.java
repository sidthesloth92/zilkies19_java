package com.zilker.service;

import com.zilker.bean.ConferenceData;
import com.zilker.delegate.AddNewBookingDelegate;

public class AddBookingService {
	
	public int book_By_Id(ConferenceData conferenceData) {
		
		return new AddNewBookingDelegate().book_By_Id(conferenceData);
	}

}
