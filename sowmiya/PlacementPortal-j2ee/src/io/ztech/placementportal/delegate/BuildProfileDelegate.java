package io.ztech.placementportal.delegate;

import java.sql.SQLException;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dao.BuildProfileDao;

public class BuildProfileDelegate {
	BuildProfileDao buildDao = new BuildProfileDao();

	public boolean addToProfile(Profile profile, String category) throws SQLException {
		if (category.equals(ApplicationConstants.ACHIEVEMENT))
			return buildDao.addToProfile(profile, SqlConstants.ADDACHIEVEMENT);
		if (category.equals(ApplicationConstants.PROJECT))
			return buildDao.addToProfile(profile, SqlConstants.ADDPROJECT);
		if (category.equals(ApplicationConstants.CERTIFICATIONS))
			return buildDao.addToProfile(profile, SqlConstants.ADDCERTIFICATION);
		return false;

	}

}
