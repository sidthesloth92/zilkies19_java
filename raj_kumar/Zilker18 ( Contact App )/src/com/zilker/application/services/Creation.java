package com.zilker.application.services;

import java.sql.ResultSet;
import java.util.logging.Logger;

public class Creation {
	private static final Logger LOGGER = Logger.getLogger(Display.class.getName());
	public void addingAnother(ResultSet rs) {
		try {
			LOGGER.info("=========================");
			while(rs.next()) {
				LOGGER.info(rs.getInt("CONTACT_ID")+" ");
				LOGGER.info(rs.getString("FIRST_NAME"));
			}
			LOGGER.info("=========================");
		}catch(Exception e) {
			
		}
	}
}
