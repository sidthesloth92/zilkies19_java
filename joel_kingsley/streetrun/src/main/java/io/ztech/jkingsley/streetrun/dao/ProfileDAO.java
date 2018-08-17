package io.ztech.jkingsley.streetrun.dao;

import java.util.ArrayList;

import io.ztech.jkingsley.streetrun.beans.Group;
import io.ztech.jkingsley.streetrun.beans.Profile;

public interface ProfileDAO {
	/*Account account;
	User user;*/
	
	public ArrayList<Profile> getAllProfiles();
	public ArrayList<Profile> getAllProfilesOfGroup(Group group);
	public Profile getProfileById(Long user_id);
	
	public boolean insertProfile(Profile profile);
	public boolean updateProfile(Profile profile);
	public boolean deleteProfile(Profile profile);
}
