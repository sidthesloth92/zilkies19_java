package io.ztech.carstats.services;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.delegate.DeleteCarDelegate;

public class DeleteCarService {

	DeleteCarDelegate dcDelegate =new DeleteCarDelegate();
	public boolean deleteCar(Specification specification) {
		return dcDelegate.deleteCar(specification);
	}
}
