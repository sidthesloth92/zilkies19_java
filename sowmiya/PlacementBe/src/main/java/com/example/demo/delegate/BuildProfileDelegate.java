package com.example.demo.delegate;

import java.sql.SQLException;

import com.example.demo.beans.Profile;
import com.example.demo.constants.ApplicationConstants;
import com.example.demo.constants.SqlConstants;
import com.example.demo.dao.BuildProfileDao;

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
