package io.ztech.fitnessapplication.service;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserPhysicalDetails;
import io.ztech.fitnessapplication.delegate.UserPhysicalDetailsDelegate;
import io.ztech.fitnessapplication.ui.EditUI;

public class UserPhysicalDetailsService {
	public boolean addphysicalProfile(UserPhysicalDetails newAccountphysicalProfile) {
		return new UserPhysicalDetailsDelegate().addphysicalProfile(newAccountphysicalProfile);
	}

	public UserPhysicalDetails getphysicalProfile(UserAccount account) {
		return new UserPhysicalDetailsDelegate().getphysicalProfile(account);
	}

	public boolean updatephysicalProfile(UserPhysicalDetails physicalProfile) {
		physicalProfile.setBmi(CalculateService.calculateBMI(physicalProfile));
		physicalProfile.setBmr(CalculateService.calculateBMR(physicalProfile));
		physicalProfile.setPlan(new EditUI().askTarget(physicalProfile));
		return new UserPhysicalDetailsDelegate().updatephysicalProfile(physicalProfile);
	}

	public boolean setTarget(UserPhysicalDetails physicalProfile) {
		return new UserPhysicalDetailsDelegate().setTarget(physicalProfile);
	}

	public boolean trackWeight(UserPhysicalDetails physicalProfile) {
		new UserPhysicalDetailsDelegate().trackWeight(physicalProfile);

		return new UserPhysicalDetailsService().updatephysicalProfile(physicalProfile);
	}

}
