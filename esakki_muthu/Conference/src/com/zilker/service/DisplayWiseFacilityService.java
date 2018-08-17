package com.zilker.service;

import java.util.ArrayList;

import com.zilker.bean.HallData;
import com.zilker.delegate.HallWiseFacilityDelegate;

public class DisplayWiseFacilityService {

	
	public ArrayList<HallData> displayFacility(ArrayList<Integer> facility_id ){
		
		ArrayList<HallData> hall_List = new ArrayList<HallData>(); 
		
		
		return new HallWiseFacilityDelegate().displayFacility(facility_id);
	}
	
}
