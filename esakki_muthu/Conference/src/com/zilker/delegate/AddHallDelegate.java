package com.zilker.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.bean.HallData;
import com.zilker.dao.AddNewHall;
import com.zilker.dao.RequestsAndHallFacilities;
import com.zilker.service.HallService;

public class AddHallDelegate {
	
	AddNewHall addNewHall = new AddNewHall();
	
	public void addHallProcess(HallData hallData) throws SQLException {
		
		addNewHall.insertHall(hallData);
		
	}
	
	public ArrayList<HallData> listHallProcess() throws SQLException {
		
		RequestsAndHallFacilities requestsAndHallFacilities = new RequestsAndHallFacilities();
		
		return requestsAndHallFacilities.getHallsWithFacility();
		
	}
	
	public void display_Hall_List(HallData hallData,int sno) {
		
		HallService hallService =  new HallService();
		
		hallService.hallDisplayService(hallData,sno);
		
	}
	
	public ArrayList<String> getHallfacilities() {
		
		RequestsAndHallFacilities requestsAndHallFacilities = new RequestsAndHallFacilities();
		
		return requestsAndHallFacilities.getFacilitiyDetails();
		
	}

}
