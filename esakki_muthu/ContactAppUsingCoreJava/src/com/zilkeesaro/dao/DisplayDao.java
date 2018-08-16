package com.zilkeesaro.dao;

import com.zilkeesaro.beans.Details;
import com.zilkeesaro.constants.Strings;

public class DisplayDao {

	public void DisplaySortedValues(Details details) {
		
		System.out.println(Strings.NEW_LINE);

		System.out.println(details.getSno() + ")");

		System.out.format(Strings.STRING_SPACE, Strings.CID, details.getContact_id());

		System.out.println();

		System.out.format(Strings.STRING_SPACE, Strings.NAME, details.getFirst_name() + " " + details.getLast_name());

		System.out.println();
		
		System.out.format(Strings.STRING_SPACE, Strings.MOBILE_NUMBER, details.getMobile_number());
		
		System.out.println();
		
		System.out.format(Strings.STRING_SPACE, Strings.OFFICE_NUMBER,details.getOffice());
		
		System.out.println();
		
		System.out.format(Strings.STRING_SPACE, Strings.HOME_NUMBER, details.getHome());	
		
		System.out.println();
				
		System.out.format(Strings.STRING_SPACE, Strings.E_MAIL,details.getE_mail());
		
		System.out.println();
		
		
	}
	
}
