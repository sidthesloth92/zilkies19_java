package io.ztech.autorate.services;

import java.sql.SQLException;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.delegate.EditCarDelegate;

public class EditCarService {
	EditCarDelegate editCarDelegate = new EditCarDelegate();
	
	public boolean editCar(Specification specification) throws SQLException {
		return editCarDelegate.editCar(specification);
	}
}
