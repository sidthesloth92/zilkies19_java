package com.zilker.delegate;

import java.util.ArrayList;

import com.zilker.bean.HallData;
import com.zilker.dao.HallWiseFacility;

public class HallWiseFacilityDelegate {
	
public ArrayList<HallData> displayFacility(ArrayList<Integer> facility_id ){
		
		ArrayList<HallData> hall_List = new ArrayList<HallData>(); 
		
		return new HallWiseFacility().getFacility(facility_id);
	}

}
