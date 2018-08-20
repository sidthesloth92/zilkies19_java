package io.ztech.carstats.services;

import java.sql.SQLException;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.delegate.DeleteCarDelegate;

public class DeleteCarService {

	DeleteCarDelegate deleteCarDelegate =new DeleteCarDelegate();
	public boolean deleteCar(Specification specification) throws SQLException {
		return deleteCarDelegate.deleteCar(specification);
	}
}
