package io.ztech.placementportal.services;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.delegate.BuildProfileDelegate;

public class BuildProfileService {
    BuildProfileDelegate profileDelegate=new BuildProfileDelegate();
	public boolean addToProfile(Profile profile, String category) {
	return profileDelegate.addToProfile(profile,category);	
	}

}
