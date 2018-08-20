package io.ztech.fitnessapplication.delegate;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserPhysicalDetails;
import io.ztech.fitnessapplication.dao.UserPhysicalDetailsDao;

public class UserPhysicalDetailsDelegate {
	public boolean addphysicalProfile(UserPhysicalDetails newphysicalProfile) {
		return new UserPhysicalDetailsDao().addProfile(newphysicalProfile);
	}

	public UserPhysicalDetails getphysicalProfile(UserAccount account) {
		return new UserPhysicalDetailsDao().getProfile(account);
	}

	public boolean updatephysicalProfile(UserPhysicalDetails physicalProfile) {
		return new UserPhysicalDetailsDao().updateProfile(physicalProfile);
	}

	public boolean setTarget(UserPhysicalDetails physicalProfile) {
		return new UserPhysicalDetailsDao().setTarget(physicalProfile);
	}

	public boolean trackWeight(UserPhysicalDetails physicalProfile) {
		return new UserPhysicalDetailsDao().trackWeight(physicalProfile);
	}

}
