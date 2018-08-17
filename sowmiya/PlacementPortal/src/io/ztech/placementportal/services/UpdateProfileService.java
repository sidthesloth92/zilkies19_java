package io.ztech.placementportal.services;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.delegate.UpdateProfileDelegate;

public class UpdateProfileService {
     UpdateProfileDelegate updateDelegate=new UpdateProfileDelegate();
	public boolean updateProfileDetail(Profile profile, String category) {
		return updateDelegate.updateProfileDetail(profile, category);
	}

}
