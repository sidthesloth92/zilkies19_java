package com.example.demo.delegate;

import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.doa.UpdateProfileDao;

public class UpdateProfileDelegate {
	private Logger log;
	
	public UpdateProfileDelegate() {
		super();
		log=Logger.getLogger("UpdateProfileDelegate.class");
	}

	public boolean updateProfileDetail(Profile profile, String category) throws SQLException {
		log.info("Entering updateProfileDetail()");
		UpdateProfileDao updateDao = new UpdateProfileDao();
		if (category.equals(ApplicationConstants.ACHIEVEMENT))
			return updateDao.addToProfile(profile, SqlConstants.UPDATEACHIEVEMENT);
		if (category.equals(ApplicationConstants.PROJECT))
			return updateDao.addToProfile(profile, SqlConstants.UPDATEPROJECT);
		if (category.equals(ApplicationConstants.CERTIFICATIONS))
			return updateDao.addToProfile(profile, SqlConstants.UPDATECERTIFICATION);
		log.info("Exiting updateProfileDetail()");
		return false;
		

	}

}
