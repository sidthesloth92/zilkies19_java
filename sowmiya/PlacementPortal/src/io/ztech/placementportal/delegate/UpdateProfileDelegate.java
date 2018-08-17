package io.ztech.placementportal.delegate;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dao.UpdateProfileDao;

public class UpdateProfileDelegate {
     UpdateProfileDao updateDao=new UpdateProfileDao();
	public boolean updateProfileDetail(Profile profile, String category) {
		if (category.equals(ApplicationConstants.ACHIEVEMENT))
			return updateDao.addToProfile(profile, SqlConstants.UPDATEACHIEVEMENT);
		if (category.equals(ApplicationConstants.PROJECT))
			return updateDao.addToProfile(profile, SqlConstants.UPDATEPROJECT);
		if (category.equals(ApplicationConstants.CERTIFICATIONS))
			return updateDao.addToProfile(profile, SqlConstants.UPDATECERTIFICATION);
		return false;


	}

}
